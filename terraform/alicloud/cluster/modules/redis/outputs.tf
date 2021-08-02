locals {
  session_redis_instance_idx  = one([for idx, i in var.instances : idx if contains(i.associations, "session")])
  location_redis_instance_idx = one([for idx, i in var.instances : idx if contains(i.associations, "location")])

  session_redis_host  = try("${alicloud_kvstore_instance.default[local.session_redis_instance_idx].connection_domain}:6379", null)
  location_redis_host = try("${alicloud_kvstore_instance.default[local.location_redis_instance_idx].connection_domain}:6379", null)

  session_redis_account_name  = try(var.instances[local.session_redis_instance_idx].account_name, null)
  location_redis_account_name = try(var.instances[local.location_redis_instance_idx].account_name, null)

  session_redis_account_password  = try(var.instances[local.session_redis_instance_idx].account_password, null)
  location_redis_account_password = try(var.instances[local.location_redis_instance_idx].account_password, null)
}

output "session_redis_host" {
  value = local.session_redis_host
}

output "session_redis_account_name" {
  value = local.session_redis_account_name
}

output "session_redis_account_password" {
  value = local.session_redis_account_password
}

output "location_redis_host" {
  value = local.location_redis_host
}

output "location_redis_account_name" {
  value = local.location_redis_account_name
}

output "location_redis_account_password" {
  value = local.location_redis_account_password
}