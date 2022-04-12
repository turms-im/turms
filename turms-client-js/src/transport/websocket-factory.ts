import SharedContextService from '../driver/service/shared-context-service';
import WebSocketClient, { EventListener } from './websocket-client';
import PlainWebSocketClient from './plain-websocket-client';
import WorkerWebSocketClient from './worker-websocket-client';

export default class WebSocketFactory {

    static create(url: string, listener: EventListener, sharedContextService?: SharedContextService): WebSocketClient {
        if (sharedContextService) {
            return new WorkerWebSocketClient(url, listener, sharedContextService);
        }
        return new PlainWebSocketClient(url, listener);
    }

}