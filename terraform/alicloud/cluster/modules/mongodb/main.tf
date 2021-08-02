# Don't use for_each because we need to find instances by index in outputs
resource "alicloud_mongodb_instance" "default" {
  count = var.create_mongodb ? length(var.instances) : 0

  name = var.instances[count.index].name
  tags = var.instances[count.index].tags

  # Network
  zone_id    = var.instances[count.index].zone_id
  vswitch_id = var.instances[count.index].vswitch_id

  # Charge
  instance_charge_type = var.instances[count.index].instance_charge_type
  period               = var.instances[count.index].period

  # Specification
  engine_version      = var.instances[count.index].engine_version
  db_instance_class   = var.instances[count.index].db_instance_class
  db_instance_storage = var.instances[count.index].db_instance_storage
  replication_factor  = var.instances[count.index].replication_factor

  # Account
  account_password = var.instances[count.index].account_password

  # Security
  security_ip_list  = var.instances[count.index].security_ip_list
  security_group_id = var.instances[count.index].security_group_id

  # Encryption
  kms_encrypted_password = var.instances[count.index].kms_encrypted_password
  kms_encryption_context = var.instances[count.index].kms_encryption_context
  tde_status             = var.instances[count.index].tde_status

  # Backup Policy
  backup_time   = var.instances[count.index].backup_time
  backup_period = var.instances[count.index].backup_period

  # Maintenance
  maintain_start_time = var.instances[count.index].maintain_start_time
  maintain_end_time   = var.instances[count.index].maintain_end_time
}

resource "alicloud_mongodb_sharding_instance" "default" {
  count = var.create_mongodb ? length(var.sharding_instances) : 0

  name = var.sharding_instances[count.index].name
  tags = var.sharding_instances[count.index].tags

  zone_id    = var.sharding_instances[count.index].zone_id
  vswitch_id = var.sharding_instances[count.index].vswitch_id

  instance_charge_type = var.sharding_instances[count.index].instance_charge_type
  period               = var.sharding_instances[count.index].period

  # Specification
  engine_version = var.sharding_instances[count.index].engine_version

  # Account
  account_password = var.sharding_instances[count.index].account_password

  # Security
  security_ip_list  = var.sharding_instances[count.index].security_ip_list
  security_group_id = var.sharding_instances[count.index].security_group_id

  # Encryption
  kms_encrypted_password = var.sharding_instances[count.index].kms_encrypted_password
  kms_encryption_context = var.sharding_instances[count.index].kms_encryption_context
  tde_status             = var.sharding_instances[count.index].tde_status

  # Backup Policy
  backup_time   = var.sharding_instances[count.index].backup_time
  backup_period = var.sharding_instances[count.index].backup_period

  dynamic "mongo_list" {
    for_each = {for idx, mongo in var.sharding_instances[count.index].mongo_list : idx => mongo}
    content {
      node_class = mongo_list.value.node_class
    }
  }

  dynamic "shard_list" {
    for_each = {for idx, shard in var.sharding_instances[count.index].shard_list : idx => shard}
    content {
      node_class        = shard_list.value.node_class
      node_storage      = shard_list.value.node_storage
      readonly_replicas = shard_list.value.readonly_replicas
    }
  }

}