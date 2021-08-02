output "nat_eip_address" {
  value = alicloud_eip_address.default[0].ip_address
}