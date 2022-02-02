variable "create_turms_service" {}

variable "instance_count" {}

# Info

variable "host_name" {}

variable "ecs_name" {}

variable "ecs_tags" {}

# Charge

variable "internet_charge_type" {}

variable "period" {}

# Specification

variable "image_name_regex" {}

variable "instance_type" {}

# Network

variable "vpc_id" {}

variable "vswitch_id" {}

variable "max_bandwidth_out" {}

variable "nic_type" {}

# Disk

variable "disk_category" {}

variable "disk_size" {}

# Security

variable "security_group_name" {}

variable "security_group_tags" {}

variable "key_pair_name" {}

variable "key_pair_tags" {}

# Protection

variable "delete_protection" {}

# Turms

variable "turms_service_profile" {}

variable "turms_service_jvm_options" {}

# MongoDB

variable "config_mongodb_hosts" {}

variable "config_mongodb_account_password" {}

variable "admin_mongodb_hosts" {}

variable "admin_mongodb_account_password" {}

variable "user_mongodb_hosts" {}

variable "user_mongodb_account_password" {}

variable "group_mongodb_hosts" {}

variable "group_mongodb_account_password" {}

variable "conversation_mongodb_hosts" {}

variable "conversation_mongodb_account_password" {}

variable "message_mongodb_hosts" {}

variable "message_mongodb_account_password" {}

# Redis

variable "session_redis_host" {}

variable "session_redis_account_name" {}

variable "session_redis_account_password" {}

variable "location_redis_host" {}

variable "location_redis_account_name" {}

variable "location_redis_account_password" {}

variable "ip_blocklist_redis_host" {}

variable "ip_blocklist_redis_account_name" {}

variable "ip_blocklist_redis_account_password" {}

variable "user_blocklist_redis_host" {}

variable "user_blocklist_redis_account_name" {}

variable "user_blocklist_redis_account_password" {}