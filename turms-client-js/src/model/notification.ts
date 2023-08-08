import { ParsedRelayedRequest } from './parsed-relayed-request';

export default class Notification {
    public readonly timestamp: Date;
    public readonly requesterId: string;
    public readonly relayedRequest: ParsedRelayedRequest;

    constructor(timestamp: Date, requesterId: string, relayedRequest: ParsedRelayedRequest) {
        this.timestamp = timestamp;
        this.requesterId = requesterId;
        this.relayedRequest = relayedRequest;
    }
}