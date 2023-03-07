terraform {
  required_version = ">= 1.3.0"
  required_providers {
    alicloud = {
      source  = "aliyun/alicloud"
      version = "~> 1.200.0"
    }
  }
}

#https://github.com/aliyun/terraform-provider-alicloud/blob/master/website/docs/index.html.markdown
provider "alicloud" {
  region     = var.zone
  access_key = var.access_key
  secret_key = var.secret_key
}

locals {
  open_tcp_ports = [
    # SSH
    "22/22",
    # turms-admin
    "6510/6510",
    # turms-service: Metrics APIs + Admin APIs
    "8510/8510",
    # turms-gateway: Metrics APIs + Admin APIs
    "9510/9510",
    # turms-gateway: WebSocket
    "10510/10510",
    # turms-gateway: TCP
    "11510/11510"
  ]
  open_udp_ports = [
    # turms-gateway: UDP
  "12510/12510"]
}

#=============== Network

data "alicloud_zones" "default" {
  available_instance_type     = var.instance_type
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

#=============== Security

resource "alicloud_security_group" "playground" {
  vpc_id              = alicloud_vpc.playground.id
  name                = "sg-playground"
  inner_access_policy = "Accept"
}

resource "alicloud_security_group_rule" "tcp" {
  count = length(local.open_tcp_ports)

  security_group_id = alicloud_security_group.playground.id
  type              = "ingress"
  ip_protocol       = "tcp"
  cidr_ip           = "0.0.0.0/0"
  port_range        = local.open_tcp_ports[count.index]
}

resource "alicloud_security_group_rule" "udp" {
  count = length(local.open_udp_ports)

  security_group_id = alicloud_security_group.playground.id
  type              = "ingress"
  ip_protocol       = "udp"
  cidr_ip           = "0.0.0.0/0"
  port_range        = local.open_udp_ports[count.index]
}

#=============== Instance

data "alicloud_images" "default" {
  most_recent = true
  name_regex  = var.image_name_regex
}

resource "alicloud_instance" "standalone" {
  # Basic Configurations
  instance_type = var.instance_type
  image_id      = data.alicloud_images.default.ids[0]

  spot_price_limit = var.spot_price_limit
  spot_strategy    = "SpotWithPriceLimit"

  system_disk_size = var.disk_size

  # Networking
  internet_max_bandwidth_out = var.max_band_out
  security_groups            = [alicloud_security_group.playground.id]
  vswitch_id                 = alicloud_vswitch.playground.id

  # System Configurations
  instance_name = "turms-playground"
  host_name     = "turms-playground"
  password      = var.ecs_root_password

  auto_release_time = var.auto_release_time

  user_data = templatefile("${path.module}/../../common/playground/tpl/user-data.sh", {
    ENV : var.env
    USE_CHINA_MIRROR : var.use_china_mirror
  })

  tags = var.tags

}