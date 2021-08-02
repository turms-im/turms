locals {
  open_ports = [
    # SSH
    "22/22",
    # HTTP to turms-admin
    "6510/6510"
  ]
}

#=============== Security

resource "alicloud_security_group" "default" {
  count = var.create_turms_admin ? 1 : 0

  name                = var.security_group_name
  vpc_id              = var.vpc_id
  inner_access_policy = "Accept"
  tags                = var.security_group_tags
}

resource "alicloud_security_group_rule" "default" {
  count = var.create_turms_admin ? length(local.open_ports) : 0

  type              = "ingress"
  ip_protocol       = "tcp"
  nic_type          = var.nic_type
  policy            = "accept"
  port_range        = local.open_ports[count.index]
  security_group_id = alicloud_security_group.default[0].id
  cidr_ip           = "0.0.0.0/0"
}

#=============== ECS

data "alicloud_images" "default" {
  name_regex = var.image_name_regex
}

resource "alicloud_instance" "default" {
  count = var.create_turms_admin ? 1 : 0

  host_name     = var.host_name
  instance_name = var.ecs_name
  tags          = var.ecs_tags

  # Charge
  internet_charge_type = var.internet_charge_type
  period               = var.period

  # Specification
  image_id      = data.alicloud_images.default.images[0].id
  instance_type = var.instance_type

  # Network
  vswitch_id                 = var.vswitch_id
  internet_max_bandwidth_out = var.max_bandwidth_out

  # Disk
  system_disk_category = var.disk_category
  system_disk_size     = var.disk_size

  # Security
  security_groups = [alicloud_security_group.default[0].id]

  # Protection
  deletion_protection = var.delete_protection

  # TODO: replace with image
  user_data = templatefile("${path.root}/../../common/cluster/tpl/user-data-turms-admin.sh", {})
}

resource "alicloud_ecs_key_pair" "default" {
  count = var.create_turms_admin ? 1 : 0

  key_pair_name = var.key_pair_name
  tags          = var.key_pair_tags
}

resource "alicloud_ecs_key_pair_attachment" "default" {
  count = var.create_turms_admin ? 1 : 0

  key_pair_name = alicloud_ecs_key_pair.default[0].key_pair_name
  instance_ids  = [alicloud_instance.default[0].id]
}