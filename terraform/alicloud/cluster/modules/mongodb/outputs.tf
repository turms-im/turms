locals {
  config_mongodb_instance_idx                = one([for idx, i in var.instances : idx if contains(i.associations, "config")])
  config_mongodb_sharding_instance_idx       = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "config")])
  admin_mongodb_instance_idx                 = one([for idx, i in var.instances : idx if contains(i.associations, "admin")])
  admin_mongodb_sharding_instance_idx        = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "admin")])
  user_mongodb_sharding_instance_idx         = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "user")])
  group_mongodb_sharding_instance_idx        = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "group")])
  conversation_mongodb_sharding_instance_idx = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "conversation")])
  message_mongodb_sharding_instance_idx      = one([for idx, i in var.sharding_instances : idx if contains(i.associations, "message")])

  # Add "1" at the end of the ID of MongoDB instance to connect to the primary node
  config_mongodb_hosts       = (local.config_mongodb_instance_idx == null
  ? [for idx, i in alicloud_mongodb_sharding_instance.default[local.config_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]
  : ["${alicloud_mongodb_instance.default[local.config_mongodb_instance_idx].id}1.mongodb.rds.aliyuncs.com:3717"])
  admin_mongodb_hosts        = (local.admin_mongodb_instance_idx == null
  ? [for idx, i in alicloud_mongodb_sharding_instance.default[local.admin_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]
  : ["${alicloud_mongodb_instance.default[local.admin_mongodb_instance_idx].id}1.mongodb.rds.aliyuncs.com:3717"])
  user_mongodb_hosts         = [for idx, i in alicloud_mongodb_sharding_instance.default[local.user_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]
  group_mongodb_hosts        = [for idx, i in alicloud_mongodb_sharding_instance.default[local.group_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]
  conversation_mongodb_hosts = [for idx, i in alicloud_mongodb_sharding_instance.default[local.conversation_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]
  message_mongodb_hosts      = [for idx, i in alicloud_mongodb_sharding_instance.default[local.message_mongodb_sharding_instance_idx].mongo_list : "${i.connect_string}:${i.port}"]

  config_mongodb_account_password       = try(var.instances[local.config_mongodb_instance_idx].account_password, var.sharding_instances[local.config_mongodb_sharding_instance_idx].account_password, null)
  admin_mongodb_account_password        = try(var.instances[local.admin_mongodb_instance_idx].account_password, var.sharding_instances[local.admin_mongodb_sharding_instance_idx].account_password, null)
  user_mongodb_account_password         = try(var.sharding_instances[local.user_mongodb_sharding_instance_idx].account_password, null)
  group_mongodb_account_password        = try(var.sharding_instances[local.group_mongodb_sharding_instance_idx].account_password, null)
  conversation_mongodb_account_password = try(var.sharding_instances[local.conversation_mongodb_sharding_instance_idx].account_password, null)
  message_mongodb_account_password      = try(var.sharding_instances[local.message_mongodb_sharding_instance_idx].account_password, null)
}

output "config_mongodb_hosts" {
  value = local.config_mongodb_hosts
}

output "config_mongodb_account_password" {
  value = local.config_mongodb_account_password
}

output "admin_mongodb_hosts" {
  value = local.admin_mongodb_hosts
}

output "admin_mongodb_account_password" {
  value = local.admin_mongodb_account_password
}

output "user_mongodb_hosts" {
  value = local.user_mongodb_hosts
}

output "user_mongodb_account_password" {
  value = local.user_mongodb_account_password
}

output "group_mongodb_hosts" {
  value = local.group_mongodb_hosts
}

output "group_mongodb_account_password" {
  value = local.group_mongodb_account_password
}

output "conversation_mongodb_hosts" {
  value = local.conversation_mongodb_hosts
}

output "conversation_mongodb_account_password" {
  value = local.conversation_mongodb_account_password
}

output "message_mongodb_hosts" {
  value = local.message_mongodb_hosts
}

output "message_mongodb_account_password" {
  value = local.message_mongodb_account_password
}