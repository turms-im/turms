class TcpMetrics {
  int? connectTime;
  int? tlsHandshakeTime;
  int? addressResolverTime;

  int dataReceived = 0;
  int dataSent = 0;
}
