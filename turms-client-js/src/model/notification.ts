import { ParsedRelayedRequest } from './parsed-relayed-request';

export default class Notification {
    public readonly timestamp: Date;
    public readonly relayedRequest: ParsedRelayedRequest;

    constructor(timestamp: Date, relayedRequest: ParsedRelayedRequest) {
        this.timestamp = timestamp;
        this.relayedRequest = relayedRequest;
    }
}