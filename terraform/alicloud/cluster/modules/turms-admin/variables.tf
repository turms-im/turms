variable "create_turms_admin" {}

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