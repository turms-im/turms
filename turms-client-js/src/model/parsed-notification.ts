import {im} from "./proto-bundle";

export interface ParsedNotification {
    requestId?: number;
    code?: number;
    reason?: string;
    data?: any;
    relayedRequest?: any; //TODO
    requesterId?: number;
}