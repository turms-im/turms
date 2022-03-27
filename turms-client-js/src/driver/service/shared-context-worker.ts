import { Notification, NotificationType, Request, RequestType, Response } from './shared-context-service';

type PortId = number;
type ContextId = string;

type PortContext = {
    port: MessagePort,
    portId?: PortId
    contextId?: ContextId,
};

type SharedContextInfo = {
    ports: Record<PortId, MessagePort>,
    ws?: WebSocket,

    loggingInPortId?: PortId,

    // shared user info
    loggedInUserInfo?: Record<string, unknown>,
    isSessionOpen?: boolean,
    sessionId?: string;
    serverId?: string;
    lastRequestDate?: number;
};

type RequestHandler = {
    shouldHaveBoundContextId?: boolean,
    handler: (context: PortContext, request: Request, info?: SharedContextInfo) => any
};

export default function (): void {
    const pendingPorts: Record<PortId, MessagePort> = {};
    const contexts: Record<ContextId, SharedContextInfo> = {};

    function notify(target: MessagePort | SharedContextInfo, {
        type,
        data
    }: Notification): void {
        if (target instanceof MessagePort) {
            target.postMessage({
                type,
                data
            } as Notification);
        } else {
            for (const port of Object.values(target.ports)) {
                port.postMessage({
                    type,
                    data
                } as Notification);
            }
        }
    }

    function notifySharedStates(port: MessagePort, info: SharedContextInfo): void {
        if (info.loggedInUserInfo) {
            notify(port, {
                type: NotificationType.UPDATE_LOGGED_IN_USER_INFO,
                data: info.loggedInUserInfo
            });
        }
        if (info.isSessionOpen != null) {
            notify(port, {
                type: NotificationType.UPDATE_IS_SESSION_OPEN,
                data: info.isSessionOpen
            });
        }
        if (info.sessionId) {
            notify(port, {
                type: NotificationType.UPDATE_SESSION_ID,
                data: info.sessionId
            });
        }
        if (info.serverId) {
            notify(port, {
                type: NotificationType.UPDATE_SERVER_ID,
                data: info.serverId
            });
        }
        if (info.lastRequestDate) {
            notify(port, {
                type: NotificationType.UPDATE_LAST_REQUEST_DATE,
                data: info.lastRequestDate
            });
        }
    }

    function response(port: MessagePort, res: Response): void {
        port.postMessage(res);
    }

    function isSame(value1: any, value2: any): boolean {
        return value1 === value2 || JSON.stringify(value1) === JSON.stringify(value2);
    }

    function throwIfFalsy(value: any, name: string): void {
        if (value == null || value?.length === 0) {
            throw new Error(`The param ${name} should not be falsy`);
        }
    }

    function createWs(info: SharedContextInfo, url: string): WebSocket {
        const ws = new WebSocket(url);
        ws.binaryType = 'arraybuffer';
        ws.onopen = (): void => notify(info, {
            type: NotificationType.WEBSOCKET_CONNECTED
        });
        ws.onclose = (e: CloseEvent): void => notify(info, {
            type: NotificationType.WEBSOCKET_CLOSED,
            data: {
                code: e.code,
                reason: e.reason
            }
        });
        ws.onmessage = (e): void => notify(info, {
            type: NotificationType.WEBSOCKET_MESSAGE_RECEIVED,
            data: e.data
        });
        return ws;
    }

    let portSeq = 1;

    const requestHandlers: Record<RequestType, RequestHandler> = {
        [RequestType.REGISTER]: {
            handler: context => {
                const id = portSeq++;
                context.portId = id;
                pendingPorts[id] = context.port;
                return id;
            }
        },
        [RequestType.UNREGISTER]: {
            handler: context => {
                if (!context.portId) {
                    return;
                }
                if (!context.contextId) {
                    delete pendingPorts[context.portId];
                    return;
                }
                const info = contexts[context.contextId];
                const ports = info?.ports;
                if (!ports) {
                    return;
                }
                delete ports[context.portId];
                if (!Object.keys(ports).length) {
                    delete context[context.contextId];
                    if (!Object.keys(contexts).length) {
                        info.ws?.close();
                    }
                }
            }
        },
        [RequestType.REBIND_CONTEXT_ID]: {
            handler: (context, request) => {
                const userId = request.data?.userId;
                const deviceType = request.data?.deviceType;
                throwIfFalsy(userId, 'userId');
                throwIfFalsy(deviceType, 'deviceType');
                const newContextId = `${userId}:${deviceType}`;
                const portId = context.portId;
                if (pendingPorts[portId]) {
                    delete pendingPorts[portId];
                } else if (context.contextId) {
                    if (context.contextId === newContextId) {
                        return;
                    }
                    delete contexts[context.contextId].ports[portId];
                }
                context.contextId = newContextId;
                const port = context.port;
                const info = contexts[newContextId];
                if (info) {
                    info.ports[portId] = port;
                    notifySharedStates(port, info);
                } else {
                    contexts[newContextId] = {
                        ports: [port]
                    };
                }
            }
        },
        [RequestType.UPDATE_LOGGED_IN_USER_INFO]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (isSame(request.data, info.loggedInUserInfo)) {
                    return;
                }
                info.loggedInUserInfo = request.data;
                notify(info, {
                    type: NotificationType.UPDATE_LOGGED_IN_USER_INFO,
                    data: info.loggedInUserInfo
                });
            }
        },
        [RequestType.REQUEST_LOGIN]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (!info.loggingInPortId) {
                    info.loggingInPortId = context.portId;
                    return true;
                }
                return false;
            }
        },
        [RequestType.FINISH_LOGIN_REQUEST]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (info.loggingInPortId === context.portId) {
                    info.loggingInPortId = null;
                }
            }
        },
        [RequestType.UPDATE_IS_SESSION_OPEN]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (isSame(request.data, info.isSessionOpen)) {
                    return;
                }
                info.isSessionOpen = request.data;
                notify(context.port, {
                    type: NotificationType.UPDATE_IS_SESSION_OPEN,
                    data: info.isSessionOpen
                });
            }
        },
        [RequestType.UPDATE_SESSION_ID]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (isSame(request.data, info.sessionId)) {
                    return;
                }
                info.sessionId = request.data;
                notify(context.port, {
                    type: NotificationType.UPDATE_SESSION_ID,
                    data: info.sessionId
                });
            }
        },
        [RequestType.UPDATE_SERVER_ID]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (isSame(request.data, info.serverId)) {
                    return;
                }
                info.serverId = request.data;
                notify(context.port, {
                    type: NotificationType.UPDATE_SERVER_ID,
                    data: info.serverId
                });
            }
        },
        [RequestType.UPDATE_LAST_REQUEST_DATE]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                if (isSame(request.data, info.lastRequestDate)) {
                    return;
                }
                info.lastRequestDate = Math.max(request.data, info.lastRequestDate);
                notify(context.port, {
                    type: NotificationType.UPDATE_LAST_REQUEST_DATE,
                    data: info.lastRequestDate
                });
            }
        },
        [RequestType.CONNECT]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                throwIfFalsy(request.data, 'url');
                const ws = info.ws;
                if (ws?.readyState === WebSocket.OPEN) {
                    return true;
                }
                if (!ws || ws.readyState === WebSocket.CLOSED || ws.readyState === WebSocket.CLOSING) {
                    notify(context.port, {
                        type: NotificationType.WEBSOCKET_CONNECTING
                    });
                    info.ws = createWs(info, request.data);
                }
            }
        },
        [RequestType.SEND_DATA]: {
            shouldHaveBoundContextId: true,
            handler: (context, request, info) => {
                throwIfFalsy(request.data, 'data');
                const ws = info.ws;
                if (ws?.readyState === WebSocket.OPEN) {
                    ws.send(request.data);
                } else {
                    throw new Error('Failed to send data because the WebSocket connection is closed');
                }
            }
        }
    };

    // use "onconnect" instead of addEventListener('connect') to refer to
    // the implementation in "chrome://inspect/#workers" easily when debugging
    // @ts-ignore
    (this as SharedWorkerGlobalScope).onconnect = (e: MessageEvent): void => {
        const port: MessagePort = e.ports[0];
        const portContext: PortContext = {
            port
        };
        port.onmessage = (ev): void => {
            const request = ev.data as Request;
            const handler: RequestHandler = requestHandlers[request.type];
            if (!handler) {
                response(port, {
                    requestId: request.id,
                    error: new Error('Unknown request: ' + JSON.stringify(request))
                });
                return;
            }
            const info = portContext.contextId && contexts[portContext.contextId];
            if (handler.shouldHaveBoundContextId && !info) {
                response(port, {
                    requestId: request.id,
                    error: new Error(`The request type ${request.type} can only be requested after the context ID is bound`)
                });
                return;
            }
            let data;
            try {
                data = handler.handler(portContext, request, info);
            } catch (error) {
                response(port, {
                    requestId: request.id,
                    error: error as Error
                });
                return;
            }
            response(port, {
                requestId: request.id,
                data
            });
        };
        port.start();
    };
}