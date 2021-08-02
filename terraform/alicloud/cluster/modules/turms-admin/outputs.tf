output "instance_id" {
  value = alicloud_instance.default[0].id
}

output "public_ip" {
  value = alicloud_instance.default[0].public_ip
}

output "private_ip" {
  value = alicloud_instance.default[0].private_ip
}

output "key_pair" {
  value = {
    id   = alicloud_ecs_key_pair.default[0].id
    name = alicloud_ecs_key_pair.default[0].key_pair_name
  }
}

output "security_group_id" {
  value = alicloud_security_group.default[0].id
}