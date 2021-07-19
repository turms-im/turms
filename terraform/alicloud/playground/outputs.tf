output "public_ip" {
  description = "Public IP address of ECS instance"
  value       = alicloud_instance.standalone.public_ip
}

output "user_login" {
  description = "Login name for ECS instance"
  value       = "root"
}