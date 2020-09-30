export default interface ClientOptions {
    wsUrl?: string;
    connectionTimeout?: number;
    requestTimeout?: number;
    minRequestInterval?: number;
    heartbeatInterval?: number;
    ackMessageInterval?: number;
    storageServerUrl?: string;
    httpUrl?: string;
    queryReasonWhenLoginFailed?: boolean;
    queryReasonWhenDisconnected?: boolean;
    storePassword?: boolean;
}
