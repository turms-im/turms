export interface ParsedNotification {
    requestId?: string;
    code?: number;
    reason?: string;
    data?: any;
    relayedRequest?: ParsedRelayedRequest;
    requesterId?: string;
    closeStatus?: number;
}
export interface ParsedRelayedRequest {
}
