output "vpc" {
  value = (var.create_vpc ? {
    id          = module.vpc.vpc_id
    cidr_block  = module.vpc.vpc_cidr_block
    vswitch_ids = module.vpc.vswitch_ids
  } : null)
}

output "nat_eip_address" {
  value = var.create_nat ? module.natgateway.nat_eip_address : null
}

output "slb_address" {
  value = var.create_slb ? module.slb.slb_address : null
}

output "mongodb" {
  value = (var.create_mongodb ? {
    config_mongodb_hosts       = module.mongodb.config_mongodb_hosts
    admin_mongodb_hosts        = module.mongodb.admin_mongodb_hosts
    user_mongodb_hosts         = module.mongodb.user_mongodb_hosts
    group_mongodb_hosts        = module.mongodb.group_mongodb_hosts
    conversation_mongodb_hosts = module.mongodb.conversation_mongodb_hosts
    message_mongodb_hosts      = module.mongodb.message_mongodb_hosts
  } : null)
}

output "redis" {
  value = (var.create_redis ? {
    session_redis_host        = module.redis.session_redis_host
    location_redis_host       = module.redis.location_redis_host
    ip_blocklist_redis_host   = module.redis.ip_blocklist_redis_host
    user_blocklist_redis_host = module.redis.user_blocklist_redis_host
  } : null)
}

output "turms_gateway" {
  value = (var.create_turms_gateway ? {
    instance_ids    = module.turms_gateway.instance_ids
    public_ip_list  = module.turms_gateway.public_ip_list
    private_ip_list = module.turms_gateway.private_ip_list
  } : null)
}

output "turms_service" {
  value = (var.create_turms_service ? {
    instance_ids    = module.turms_service.instance_ids
    public_ip_list  = module.turms_service.public_ip_list
    private_ip_list = module.turms_service.private_ip_list
  } : null)
}

output "turms_admin" {
  value = (var.create_turms_admin ? {
    instance_id     = module.turms_admin.instance_id
    public_ip       = module.turms_admin.public_ip
    private_ip_list = module.turms_admin.private_ip
  } : null)
}