locals {
  session_redis_instance_idx  = one([for idx, i in var.instances : idx if contains(i.associations, "session")])
  location_redis_instance_idx = one([for idx, i in var.instances : idx if contains(i.associations, "location")])
  ip_blocklist_redis_instance_idx = one([for idx, i in var.instances : idx if contains(i.associations, "ip_blocklist")])
  user_blocklist_redis_instance_idx = one([for idx, i in var.instances : idx if contains(i.associations, "user_blocklist")])

  session_redis_host  = try("${alicloud_kvstore_instance.default[local.session_redis_instance_idx].connection_domain}:6379", null)
  location_redis_host = try("${alicloud_kvstore_instance.default[local.location_redis_instance_idx].connection_domain}:6379", null)
  ip_blocklist_redis_host = try("${alicloud_kvstore_instance.default[local.ip_blocklist_redis_instance_idx].connection_domain}:6379", null)
  user_blocklist_redis_host = try("${alicloud_kvstore_instance.default[local.user_blocklist_redis_instance_idx].connection_domain}:6379", null)

  session_redis_account_name  = try(var.instances[local.session_redis_instance_idx].account_name, null)
  location_redis_account_name = try(var.instances[local.location_redis_instance_idx].account_name, null)
  ip_blocklist_redis_account_name = try(var.instances[local.ip_blocklist_redis_instance_idx].account_name, null)
  user_blocklist_redis_account_name = try(var.instances[local.user_blocklist_redis_instance_idx].account_name, null)

  session_redis_account_password  = try(var.instances[local.session_redis_instance_idx].account_password, null)
  location_redis_account_password = try(var.instances[local.location_redis_instance_idx].account_password, null)
  ip_blocklist_redis_account_password = try(var.instances[local.ip_blocklist_redis_instance_idx].account_password, null)
  user_blocklist_redis_account_password = try(var.instances[local.user_blocklist_redis_instance_idx].account_password, null)
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

output "ip_blocklist_redis_host" {
  value = local.ip_blocklist_redis_host
}

output "ip_blocklist_redis_account_name" {
  value = local.ip_blocklist_redis_account_name
}

output "ip_blocklist_redis_account_password" {
  value = local.ip_blocklist_redis_account_password
}

output "user_blocklist_redis_host" {
  value = local.user_blocklist_redis_host
}

output "user_blocklist_redis_account_name" {
  value = local.user_blocklist_redis_account_name
}

output "user_blocklist_redis_account_password" {
  value = local.user_blocklist_redis_account_password
}