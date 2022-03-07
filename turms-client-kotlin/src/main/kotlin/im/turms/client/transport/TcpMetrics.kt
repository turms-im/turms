package im.turms.client.transport

data class TcpMetrics(
    var connectTime: Int? = null,
    var tlsHandshakeTime: Int? = null,
    var addressResolverTime: Int? = null,

    var dataReceived: Long = 0,
    var dataSent: Long = 0
)
