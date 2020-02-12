export interface ParsedNotification {
    requestId?: number;
    code?: number;
    reason?: string;
    data?: any;
    relayedRequest?: any;
    requesterId?: number;
}
