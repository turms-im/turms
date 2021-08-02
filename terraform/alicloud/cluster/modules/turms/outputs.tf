output "instance_ids" {
  value = alicloud_instance.default[*].id
}

output "public_ip_list" {
  value = alicloud_instance.default[*].public_ip
}

output "private_ip_list" {
  value = alicloud_instance.default[*].private_ip
}

output "key_pair_list" {
  value = ([for pair in alicloud_ecs_key_pair.default : {
    id   = pair.id
    name = pair.key_pair_name
  }])
}

output "security_group_id" {
  value = alicloud_security_group.default[0].id
}