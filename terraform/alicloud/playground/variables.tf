# Credentials

//variable "access_key" {
//  //  https://ram.console.aliyun.com/manage/ak
//  type    = string
//  default = ""
//}
//
//variable "secret_key" {
//  type    = string
//  default = ""
//}

# Instance

variable "zone" {
  type    = string
  default = "cn-shenzhen"
}

variable "image_id" {
  type    = string
  default = "ubuntu_20_04_x64_20G_alibase_20210623.vhd"
}

variable "instance_type" {
  type    = string
  // 0.0472RMB/Hour in Shenzhen
  default = "ecs.t6-c1m2.large"
}

variable "spot_price_limit" {
  type    = number
  default = 0.25
}

variable "max_band_out" {
  type    = number
  default = 1
}

variable "ecs_root_password" {
  type    = string
  default = "Turms.9510"
}

# Turms

variable "env" {
  type    = string
  default = "dev"
}

# Mirror

variable "use_china_mirror" {
  type    = bool
  default = true
}