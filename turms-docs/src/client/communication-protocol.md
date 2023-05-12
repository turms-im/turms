# Communication Protocol Used Between Client and Server

## Data Format

For general requests and responses:

* Client based on the pure TCP protocol: varint-encoded payload length + payload (Protobuf-encoded `TurmsNotification` or `TurmsRequest`).
* Client based on the WebSocket protocol: payload (Protobuf-encoded `TurmsNotification` or `TurmsRequest`). The byte length of the payload is transmitted through the underlying WebSocket frame.

For heartbeat requests:

* Client based on the pure TCP protocol: a byte array `[0]` with the length of one byte. The value `0` here actually means "the length of the payload is 0 with a length of one byte under varint encoding", that is, the payload is 0 bytes.
* Client based on the WebSocket protocol: a binary message with an empty body (0 bytes).

Note: The reasons why Turms does not implement heartbeat through WebSocket's PING/PONG are:

* The time interval for sending PING frames implemented by each browser's WebSocket is different.
* The upper layer code cannot control the behavior of PING/PONG, or even perceive the occurrence of the behavior.
* The heartbeat logic at the network level should not be coupled with the heartbeat at the application layer.