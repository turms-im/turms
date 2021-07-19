# Network

data "alicloud_zones" "default" {
  available_instance_type = var.instance_type
  available_resource_creation = "VSwitch"
}

resource "alicloud_vpc" "playground" {
  vpc_name   = "vpc-turms-playground"
  cidr_block = "172.16.0.0/12"
}

resource "alicloud_vswitch" "playground" {
  vswitch_name = "vsw-turms-playground"
  vpc_id       = alicloud_vpc.playground.id
  cidr_block   = "172.16.0.0/21"
  zone_id      = data.alicloud_zones.default.zones[0].id
}

# Security

resource "alicloud_security_group" "playground" {
  vpc_id              = alicloud_vpc.playground.id
  name                = "sg-playground"
  inner_access_policy = "Accept"
}

resource "alicloud_security_group_rule" "tcp" {
  security_group_id = alicloud_security_group.playground.id
  type              = "ingress"
  ip_protocol       = "tcp"
  cidr_ip           = "0.0.0.0/0"
  port_range        = local.open_tcp_ports[count.index]
  count             = length(local.open_tcp_ports)
}

resource "alicloud_security_group_rule" "udp" {
  security_group_id = alicloud_security_group.playground.id
  type              = "ingress"
  ip_protocol       = "udp"
  cidr_ip           = "0.0.0.0/0"
  port_range        = local.open_udp_ports[count.index]
  count             = length(local.open_udp_ports)
}

# Instance

resource "alicloud_instance" "standalone" {
  // Basic Configurations
  instance_type    = var.instance_type
  image_id         = var.image_id
  spot_price_limit = var.spot_price_limit
  spot_strategy    = "SpotWithPriceLimit"
  system_disk_size = 20

  // Networking
  internet_max_bandwidth_out = var.max_band_out
  security_groups            = [alicloud_security_group.playground.id]
  vswitch_id                 = alicloud_vswitch.playground.id

  // System Configurations
  instance_name = "turms-playground"
  host_name     = "turms-playground"
  password      = var.ecs_root_password

  user_data = templatefile("${path.module}/../../common/playground/tpl/user-data.sh", {
    ENV: var.env,
    USE_CHINA_MIRROR: var.use_china_mirror
  })

  tags = {
    group: "turms"
    type:  "standalone"
  }

}