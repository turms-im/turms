resource "alicloud_nat_gateway" "default" {
  count = var.create_nat ? 1 : 0

  vpc_id     = var.nat_vpc_id
  vswitch_id = var.nat_vswitch_id

  name          = var.nat_name
  specification = var.nat_specification
  payment_type  = var.nat_payment_type
  nat_type      = "Enhanced"
}

resource "alicloud_snat_entry" "default" {
  count = var.create_nat ? length(var.snat_source_vswitch_ids) : 0

  snat_entry_name   = "${var.snat_entry_name}-${count.index}"
  snat_table_id     = alicloud_nat_gateway.default[0].snat_table_ids
  snat_ip           = alicloud_eip_address.default[0].ip_address
  source_vswitch_id = var.snat_source_vswitch_ids[count.index]
}

resource "alicloud_eip_address" "default" {
  count = var.create_nat ? 1 : 0

  address_name         = var.eip_address_name
  bandwidth            = var.eip_bandwidth
  internet_charge_type = var.eip_internet_charge_type
  payment_type         = var.eip_payment_type
  isp                  = var.eip_isp
  tags                 = var.eip_tags
}

resource "alicloud_eip_association" "default" {
  count = var.create_nat ? 1 : 0

  instance_id   = alicloud_nat_gateway.default[0].id
  allocation_id = alicloud_eip_address.default[0].id
}