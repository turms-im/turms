output "vpc_id" {
  value = alicloud_vpc.default[0].id
}

output "vpc_cidr_block" {
  value = alicloud_vpc.default[0].cidr_block
}

output "vswitch_ids" {
  value = alicloud_vswitch.default[*].id
}
