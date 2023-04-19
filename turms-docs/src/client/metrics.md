# Metrics

Reference: [Observability System](https://turms-im.github.io/docs/server/module/observability#%E5%BA%A6%E9%87%8F)

## Network Connection Metrics

Each client of Turms will provide metrics related to the network connection. Developers can get the metrics through `turmsClient.driver.connectionMetrics`. This object contains the following data:

| Data point name     | Unit         | Meaning                                                      |
| ------------------- | ------------ | ------------------------------------------------------------ |
| addressResolverTime | milliseconds | The domain name resolution time. <br />turms-client-js does not provide this data |
| connectTime         | milliseconds | For non-turms-client-js clients, this data refers to the time spent in TCP handshake;<br />For turms-client-js clients, this data refers to the total time of domain name resolution, TCP handshake, TLS handshake, and establishment of WebSocket connection |
| tlsHandshakeTime    | milliseconds | TLS handshake time. <br />turms-client-js/swift does not provide this data |
| dataReceived        | bytes        | For non-turms-client-js clients, this data refers to the number of data bytes received by the TCP connection;<br />For turms-client-js clients, this data refers to the bytes of the binary frame received by the WebSocket connection |
| dataSent            | bytes        | For non-turms-client-js clients, this data refers to the number of data bytes sent by the TCP connection;<br />For turms-client-js clients, this data refers to the bytes of the binary frame received by the WebSocket connection |

## Business Request Metrics

TODO