import {im} from "./proto-bundle";

export interface ParsedNotification {
    request_id?: number;
    code?: number;
    reason?: string;
    model?: any;
    relayed_request?: any; //TODO
    requester_id?: number;
}