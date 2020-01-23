export interface ParsedNotification {
    request_id?: number;
    code?: number;
    reason?: string;
    model?: any;
    relayed_request?: any;
    requester_id?: number;
}
