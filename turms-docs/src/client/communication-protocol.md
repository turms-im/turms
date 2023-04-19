# Communication Protocol Used Between Client and Server

## Data Format

For general requests and responses:

* Client based on the pure TCP protocol: varint-encoded payload length + payload (Protobuf-encoded `TurmsNotification` or `TurmsRequest`).
* Client based on the WebSocket protocol: payload (Protobuf-encoded `TurmsNotification` or `TurmsRequest`). The byte length of the payload is transmitted through the underlying WebSocket frame.

For heartbeat requests:

* Client based on the pure TCP protocol: a byte array `[0]` with the length of one byte. The value `0` here actually means "the length of the payload is 0 with a length of one byte under varint encoding", that is, the payload is 0 bytes.
* Client based on the WebSocket protocol: a binary message with an empty body (0 bytes).

Supplement: The reasons why Turms does not implement heartbeat through WebSocket's PING/PONG are:

* The time interval for sending PING frames implemented by each browser's WebSocket is different.
* The upper layer code cannot control the behavior of PING/PONG, or even perceive the occurrence of the behavior.
* The heartbeat logic at the network level should not be coupled with the heartbeat at the application layer.

## XMPP

XMPP is an open instant messaging protocol based on XML.

Reasons why Turms itself does not use the XMPP protocol:

1. Very inefficient design. Mainly reflected in two aspects:
   1. The data format adopts the redundant and inefficient XML protocol, and its metadata is often larger than the actual transmitted data.
   2. In the design of XMPP, there are a lot of inefficient designs, such as converting the user avatar picture into Base64 text for transmission, or if the user modifies some personal information, the server needs to actively push the information to all the contacts in the contact list.
2. Poor scalability. Some articles say that XMPP is highly scalable, but this "extensible" is only relative to those protocols that are not scalable. The truly scalable protocol must be a self-developed protocol.

However, considering the following points, the Turms server plans to support the XMPP protocol in the future:

1. Most of the well-known XMPP open source server projects have old-fashioned technical architectures, outdated technology stacks, and poor performance. Although some open source XMPP servers will advertise their "Scalable" architecture, its scalability pales in comparison to Turms. In the true sense, Turms is a project that strives to achieve the ultimate in all aspects (including scalability) in terms of architecture, code implementation, database design, etc. Therefore, Turms can overwhelmingly defeat them in the field of medium and large IM.
2. Reuse rich XMPP client implementations on the Internet.

Of course, because XMPP itself has many inefficient designs, Turms will not support these designs in the future.