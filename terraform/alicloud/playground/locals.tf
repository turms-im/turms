locals {
  open_tcp_ports = [
    // SSH
    "22/22",
    // turms-admin
    "6510/6510",
    // turms: Metrics APIs + Admin APIs
    "8510/8510",
    // turms-gateway: Metrics APIs
    "9510/9510",
    // turms-gateway: WebSocket
    "10510/10510",
    // turms-gateway: TCP
    "11510/11510"
  ]
  open_udp_ports = [
    // turms-gateway: UDP
    "12510/12510"]
}