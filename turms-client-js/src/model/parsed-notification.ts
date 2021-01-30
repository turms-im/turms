export interface ParsedNotification {
    requestId?: string;
    code?: number;
    reason?: string;
    data?: any;
    relayedRequest?: ParsedRelayedRequest; //TODO
    requesterId?: string;
    closeStatus?: number;
}

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface ParsedRelayedRequest {
}