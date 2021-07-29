output "public_ip" {
  value = alicloud_instance.standalone.public_ip
}

output "login_name" {
  value = "root"
}