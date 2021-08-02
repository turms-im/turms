output "slb_address" {
  value = alicloud_slb_load_balancer.default[0].address
}