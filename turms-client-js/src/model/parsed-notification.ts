import {im} from "./proto-bundle";

export interface ParsedNotification {
    requestId?: string;
    code?: number;
    reason?: string;
    data?: any;
    relayedRequest?: any; //TODO
    requesterId?: string;
}