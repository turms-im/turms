export default interface ClientOptions {
    wsUrl?: string,
    connectionTimeout?: number,
    requestTimeout?: number,
    minRequestInterval?: number,
    heartbeatInterval?: number,
    storageServerUrl?: string,
    useSharedContext?: boolean
}