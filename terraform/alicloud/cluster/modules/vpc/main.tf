resource "alicloud_vpc" "default" {
  count = var.create_vpc ? 1 : 0

  vpc_name   = var.vpc_name
  tags       = var.vpc_tags
  cidr_block = var.vpc_cidr
}

resource "alicloud_vswitch" "default" {
  count = var.create_vpc ? length(var.vswitch_zone_cidr_list) : 0

  vswitch_name = "${var.vswitch_name}-${count.index + 1}"
  tags         = var.vswitch_tags
  vpc_id       = alicloud_vpc.default[0].id
  zone_id      = var.vswitch_zone_cidr_list[count.index].zone_id
  cidr_block   = var.vswitch_zone_cidr_list[count.index].cidr
}