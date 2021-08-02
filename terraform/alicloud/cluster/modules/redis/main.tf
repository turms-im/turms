# Don't use for_each because we need to find instances by index in outputs
resource "alicloud_kvstore_instance" "default" {
  count = var.create_redis ? length(var.instances) : 0

  db_instance_name = var.instances[count.index].name
  tags             = var.instances[count.index].tags

  vswitch_id = var.instances[count.index].vswitch_id

  # Charge
  payment_type = var.instances[count.index].payment_type
  period       = var.instances[count.index].period

  # Specification
  engine_version = var.instances[count.index].engine_version
  instance_class = var.instances[count.index].instance_class
  instance_type  = "Redis"

  # Maintenance
  maintain_start_time = var.instances[count.index].maintain_start_time
  maintain_end_time   = var.instances[count.index].maintain_end_time

  security_ips = var.instances[count.index].security_ips
}

resource "alicloud_kvstore_account" "account" {
  count = var.create_redis ? length(var.instances) : 0

  instance_id       = alicloud_kvstore_instance.default[count.index].id
  account_name      = var.instances[count.index].account_name
  account_password  = var.instances[count.index].account_password
  account_privilege = var.instances[count.index].account_privilege
}