export default interface ClientOptions {
    url?: string,
    connectionTimeout?: number,
    requestTimeout?: number,
    minRequestsInterval?: number,
    storageServerUrl?: string,
    httpUrl?: string,
    queryReasonWhenLoginFailed?: boolean,
    queryReasonWhenDisconnected?: boolean
}
