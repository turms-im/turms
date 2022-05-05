locals {
  turms_gateway_open_ports       = [
    # WebSocket
    10510,
    # TCP
    11510
  ]
  turms_gateway_metrics_api_port = 9510
}

resource "alicloud_slb_load_balancer" "default" {
  count = var.create_slb ? 1 : 0

  load_balancer_name = var.name
  tags               = var.tags

  # Charge
  internet_charge_type = var.internet_charge_type
  payment_type         = var.payment_type

  # Zone
  master_zone_id = var.primary_zone_id
  slave_zone_id  = var.secondary_zone_id

  # Specification
  address_type       = "internet"
  load_balancer_spec = var.specification

  # Protection
  delete_protection = var.delete_protection
}

resource "alicloud_slb_listener" "default" {
  count = var.create_slb ? length(local.turms_gateway_open_ports) : 0

  load_balancer_id    = alicloud_slb_load_balancer.default[0].id
  frontend_port       = local.turms_gateway_open_ports[count.index]
  backend_port        = local.turms_gateway_open_ports[count.index]
  # Also use "tcp" instead of "http" for WebSocket because
  # turms doesn't need the features of HTTP load balancer and
  # TCP load balancer can reach a higher throughout
  protocol            = "tcp"
  bandwidth           = var.bandwidth
  established_timeout = var.established_timeout

  sticky_session      = "on"
  sticky_session_type = "insert"

  health_check              = "on"
  health_check_type         = "http"
  health_check_connect_port = local.turms_gateway_metrics_api_port
  health_check_http_code    = "http_2xx,http_3xx,http_4xx"
  health_check_uri          = "/health"
}

resource "alicloud_slb_backend_server" "default" {
  count            = var.create_slb ? 1 : 0
  load_balancer_id = alicloud_slb_load_balancer.default[0].id

  dynamic "backend_servers" {
    for_each = var.turms_gateway_instance_ids
    content {
      server_id = backend_servers.value
      weight    = 50
    }
  }
}