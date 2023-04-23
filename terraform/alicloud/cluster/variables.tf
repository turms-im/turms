#=============== Provider

variable "region" {
  type    = string
  default = "cn-hangzhou"
}

variable "access_key" {
  # https://ram.console.aliyun.com/manage/ak
  type    = string
  default = null
}

variable "secret_key" {
  type    = string
  default = null
}

variable "profile" {
  description = "The profile name as set in the shared credentials file. If not set, it will be sourced from the ALICLOUD_PROFILE environment variable."
  default     = "default"
}

#=============== Resource Management

variable "create_vpc" {
  type    = bool
  default = true
}

variable "create_nat" {
  type    = bool
  default = true
}

variable "create_slb" {
  type    = bool
  default = true
}

variable "create_mongodb" {
  type    = bool
  default = true
}

variable "create_redis" {
  type    = bool
  default = true
}

variable "create_turms_gateway" {
  type    = bool
  default = true
}

variable "create_turms_service" {
  type    = bool
  default = true
}

variable "create_turms_admin" {
  type    = bool
  default = true
}

#=============== VPC

variable "vpc_name" {
  type    = string
  default = "vpc-turms"
}

variable "vpc_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "vpc_cidr" {
  type    = string
  default = "172.16.0.0/12"
}

variable "vswitch_name" {
  type    = string
  default = "vsw-turms"
}

variable "vswitch_tags" {
  type = map(string)
  default = {
    Group : "turms"
    Type : "cluster"
  }
}

variable "vswitch_zone_cidr_list" {
  type = list(object({
    zone_id = string
    cidr    = string
  }))
  default = [{
    zone_id : "cn-hangzhou-e"
    cidr : "172.16.1.0/21"
    }, {
    zone_id : "cn-hangzhou-h"
    cidr : "172.16.2.0/21"
  }]
}

variable "fallback_vpc_id" {
  type    = string
  default = null
}

variable "fallback_vpc_cidr_block" {
  type    = string
  default = null
}

variable "fallback_vswitch_id" {
  type    = string
  default = null
}

#=============== NAT Gateway

variable "nat_name" {
  type    = string
  default = "nat-turms"
}

variable "nat_payment_type" {
  type    = string
  default = "PayAsYouGo"
}

variable "nat_specification" {
  type    = string
  default = "Small"
}

variable "snat_entry_name" {
  type    = string
  default = "snat-turms"
}

#=============== NAT Gateway - EIP

variable "nat_eip_address_name" {
  type    = string
  default = "eip-turms"
}

variable "nat_eip_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "nat_eip_internet_charge_type" {
  type    = string
  default = "PayByTraffic"
}

variable "nat_eip_payment_type" {
  type    = string
  default = "PayAsYouGo"
}

variable "nat_eip_bandwidth" {
  type    = number
  default = 2
}

variable "nat_eip_isp" {
  type    = string
  default = "BGP"
}

#=============== SLB

variable "slb_name" {
  type    = string
  default = "slb-turms"
}

variable "slb_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "slb_internet_charge_type" {
  type    = string
  default = "PayByTraffic"
}

variable "slb_payment_type" {
  type    = string
  default = "PayAsYouGo"
}

variable "slb_specification" {
  type    = string
  default = "slb.s1.small"
}

variable "slb_bandwidth" {
  type    = number
  default = 1000
}

variable "slb_established_timeout" {
  type    = number
  default = 900
}

variable "slb_delete_protection" {
  type    = string
  default = "off"
}

variable "turms_gateway_instance_ids" {
  type    = list(string)
  default = null
}

#=============== MongoDB

variable "mongodb_instances" {
  type = list(object({
    associations = optional(set(string))

    name = optional(string)
    tags = optional(map(string))

    zone_id    = optional(string)
    vswitch_id = optional(string)

    instance_charge_type = optional(string)
    period               = optional(number)

    engine_version      = optional(string)
    db_instance_class   = optional(string)
    db_instance_storage = optional(string)
    replication_factor  = optional(number)

    account_password = optional(string)

    security_ip_list  = optional(set(string))
    security_group_id = optional(string)

    kms_encrypted_password = optional(string)
    kms_encryption_context = optional(map(string))
    tde_status             = optional(string)

    backup_period = optional(set(string))
    backup_time   = optional(string)

    maintain_start_time = optional(string)
    maintain_end_time   = optional(string)
  }))
  default = []
  validation {
    condition = alltrue([for association in var.mongodb_instances[*].associations : contains(["config", "admin",
    "user", "group", "conversation", "message"], association) if association != null])
    error_message = "Allowed values for input_parameter are \"config\", \"admin\", \"user\", \"group\", \"conversation\", or \"message\"."
  }
}

variable "mongodb_sharding_instances" {
  type = list(object({
    associations = optional(set(string))

    name = optional(string)
    tags = optional(map(string))

    zone_id    = optional(string)
    vswitch_id = optional(string)

    instance_charge_type = optional(string)
    period               = optional(number)

    security_ip_list  = optional(set(string))
    security_group_id = optional(string)

    engine_version = optional(string)

    account_password = optional(string)

    kms_encrypted_password = optional(string)
    kms_encryption_context = optional(map(string))
    tde_status             = optional(string)

    backup_period = optional(set(string))
    backup_time   = optional(string)

    mongo_list = optional(list(object({
      node_class = string
    })))

    shard_list = optional(list(object({
      node_class        = string
      node_storage      = number
      readonly_replicas = number
    })))
  }))
  default = [{}]
  validation {
    condition = alltrue([for association in var.mongodb_sharding_instances[*].associations : contains(["config",
    "admin", "user", "group", "conversation", "message"], association) if association != null])
    error_message = "Allowed values for input_parameter are \"config\", \"admin\", \"user\", \"group\", \"conversation\", or \"message\"."
  }
}

locals {
  mongodb_instances = [for val in var.mongodb_instances : merge(val, {
    associations = ["config", "admin"]

    name = "mongo-turms"
    tags = {
      Group = "turms"
      Type  = "cluster"
    }

    zone_id    = local.multi_zone_id
    vswitch_id = var.create_vpc ? module.vpc.vswitch_ids[0] : var.fallback_vswitch_id

    instance_charge_type = "PostPaid"

    security_ip_list = [var.create_vpc ? module.vpc.vpc_cidr_block : var.fallback_vpc_cidr_block]

    engine_version      = "4.2"
    db_instance_class   = "dds.mongo.mid"
    db_instance_storage = 10

    account_password = "Turms_9510"
  }, { for k, v in val : k => v if v != null })]

  mongodb_sharding_instances = [for val in var.mongodb_sharding_instances : merge(val, {
    associations = ["config", "admin", "user", "group", "conversation", "message"]

    name = "mongo-sharding-turms"
    tags = {
      Group = "turms"
      Type  = "cluster"
    }

    zone_id    = local.multi_zone_id
    vswitch_id = var.create_vpc ? module.vpc.vswitch_ids[0] : var.fallback_vswitch_id

    instance_charge_type = "PostPaid"

    security_ip_list = [var.create_vpc ? module.vpc.vpc_cidr_block : var.fallback_vpc_cidr_block]

    engine_version = "4.2"

    account_password = "Turms_9510"

    mongo_list = [{
      node_class = "dds.mongos.mid"
      }, {
      node_class = "dds.mongos.mid"
    }]
    shard_list = [{
      node_class        = "dds.shard.mid"
      node_storage      = 10
      readonly_replicas = 0
      }, {
      node_class        = "dds.shard.mid"
      node_storage      = 10
      readonly_replicas = 0
    }]
  }, { for k, v in val : k => v if v != null })]
}

#=============== Redis

variable "redis_instances" {
  type = list(object({
    associations = optional(set(string))

    name = optional(string)
    tags = optional(map(string))

    vswitch_id = optional(string)

    payment_type = optional(string)
    period       = optional(number)

    instance_class = optional(string)
    instance_type  = optional(string)
    engine_version = optional(string)

    maintain_start_time = optional(string)
    maintain_end_time   = optional(string)

    security_ips = optional(set(string))

    account_name      = optional(string)
    account_password  = optional(string)
    account_privilege = optional(string)
  }))

  default = [{}]
  validation {
    condition = alltrue([for association in var.redis_instances[*].associations : contains(["session",
    "location", "ip_blocklist", "user_blocklist"], association) if association != null])
    error_message = "Allowed values for input_parameter are \"session\", \"location\", \"ip_blocklist\" or \"user_blocklist\"."
  }
}

locals {
  redis_instances = [for val in var.redis_instances : merge(val, {
    associations : ["session", "location", "ip_blocklist", "user_blocklist"]

    name = "redis-turms"
    tags = {
      Group = "turms"
      Type  = "cluster"
    }

    vswitch_id = var.create_vpc ? module.vpc.vswitch_ids[0] : var.fallback_vswitch_id

    payment_type = "PostPaid"

    engine_version = "6.0"
    instance_class = "redis.master.micro.default"

    security_ip_list = [var.create_vpc ? module.vpc.vpc_cidr_block : var.fallback_vpc_cidr_block]

    account_name      = "turms"
    account_password  = "Turms_9510"
    account_privilege = "RoleReadWrite"
  }, { for k, v in val : k => v if v != null })]
}

#=============== ECS - Turms Gateway

variable "turms_gateway_instance_count" {
  type    = number
  default = 1
}

# Info

variable "turms_gateway_host_name" {
  type    = string
  default = "turms"
}

variable "turms_gateway_ecs_name" {
  type    = string
  default = "turms-gateway"
}

variable "turms_gateway_ecs_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Charge

variable "turms_gateway_internet_charge_type" {
  type    = string
  default = "PayByTraffic"
}

variable "turms_gateway_period" {
  type    = number
  default = null
}

# Specification

variable "turms_gateway_image_name_regex" {
  type    = string
  default = "^ubuntu_20.*64"
}

variable "turms_gateway_instance_type" {
  type    = string
  default = "ecs.t5-lc1m1.small"
}

# Network

variable "turms_gateway_max_bandwidth_out" {
  type    = number
  default = 0
}

variable "turms_gateway_nic_type" {
  type    = string
  default = "intranet"
}

# Disk

variable "turms_gateway_disk_category" {
  type    = string
  default = "cloud_efficiency"
}

variable "turms_gateway_disk_size" {
  type    = number
  default = 20
}

# Security

variable "turms_gateway_security_group_name" {
  type    = string
  default = "sg-turms-gateway"
}

variable "turms_gateway_security_group_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "turms_gateway_key_pair_name" {
  type    = string
  default = "kp-turms-gateway"
}

variable "turms_gateway_key_pair_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Protection

variable "turms_gateway_delete_protection" {
  type    = bool
  default = false
}

# Turms Gateway

variable "turms_gateway_profile" {
  type    = string
  default = "prod"
}

variable "turms_gateway_jvm_options" {
  type    = string
  default = ""
}

#=============== ECS - Turms Service

variable "turms_service_instance_count" {
  type    = number
  default = 1
}

# Info

variable "turms_service_host_name" {
  type    = string
  default = "turms"
}

variable "turms_service_ecs_name" {
  type    = string
  default = "turms"
}

variable "turms_service_ecs_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Charge

variable "turms_service_internet_charge_type" {
  type    = string
  default = "PayByTraffic"
}

variable "turms_service_period" {
  type    = number
  default = null
}

# Specification

variable "turms_service_image_name_regex" {
  type    = string
  default = "^ubuntu_20.*64"
}

variable "turms_service_instance_type" {
  type    = string
  default = "ecs.t5-lc1m1.small"
}

# Network

variable "turms_service_max_bandwidth_out" {
  type    = number
  default = 0
}

variable "turms_service_nic_type" {
  type    = string
  default = "intranet"
}

# Disk

variable "turms_service_disk_category" {
  type    = string
  default = "cloud_efficiency"
}

variable "turms_service_disk_size" {
  type    = number
  default = 20
}

# Security

variable "turms_service_security_group_name" {
  type    = string
  default = "sg-turms"
}

variable "turms_service_security_group_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "turms_service_key_pair_name" {
  type    = string
  default = "kp-turms"
}

variable "turms_service_key_pair_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Protection

variable "turms_service_delete_protection" {
  type    = bool
  default = false
}

# Turms

variable "turms_service_profile" {
  type    = string
  default = "prod"
}

variable "turms_service_jvm_options" {
  type    = string
  default = ""
}

#=============== ECS - Turms Admin

variable "turms_admin_instance_count" {
  type    = number
  default = 1
}

# Info

variable "turms_admin_host_name" {
  type    = string
  default = "turms-admin"
}

variable "turms_admin_ecs_name" {
  type    = string
  default = "turms-admin"
}

variable "turms_admin_ecs_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Charge

variable "turms_admin_internet_charge_type" {
  type    = string
  default = "PayByTraffic"
}

variable "turms_admin_period" {
  type    = number
  default = null
}

# Specification

variable "turms_admin_image_name_regex" {
  type    = string
  default = "^ubuntu_20.*64"
}

variable "turms_admin_instance_type" {
  type    = string
  default = "ecs.t5-lc1m1.small"
}

# Network

variable "turms_admin_max_bandwidth_out" {
  type    = number
  default = 0
}

variable "turms_admin_nic_type" {
  type    = string
  default = "intranet"
}

# Disk

variable "turms_admin_disk_category" {
  type    = string
  default = "cloud_efficiency"
}

variable "turms_admin_disk_size" {
  type    = number
  default = 20
}

# Security

variable "turms_admin_security_group_name" {
  type    = string
  default = "sg-turms-admin"
}

variable "turms_admin_security_group_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

variable "turms_admin_key_pair_name" {
  type    = string
  default = "kp-turms-admin"
}

variable "turms_admin_key_pair_tags" {
  type = map(string)
  default = {
    Group = "turms"
    Type  = "cluster"
  }
}

# Protection

variable "turms_admin_delete_protection" {
  type    = bool
  default = false
}