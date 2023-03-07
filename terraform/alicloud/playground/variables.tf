# Credentials

variable "access_key" {
  # https://ram.console.aliyun.com/manage/ak
  type    = string
  default = null
}

variable "secret_key" {
  type    = string
  default = null
}

# Instance

variable "zone" {
  type    = string
  default = "cn-shenzhen"
}

variable "image_name_regex" {
  type    = string
  default = "^ubuntu_20.*64"
}

variable "instance_type" {
  type = string
  // 0.0472RMB/Hour in Shenzhen
  default = "ecs.t6-c1m2.large"
}

variable "spot_price_limit" {
  type    = number
  default = 0.25
}

variable "auto_release_time" {
  type = string
  # yyyy-MM-ddTHH:mm:ssZ
  # The time follows the ISO 8601 standard and is a UTC time
  # To get a current UTC time: date -u +"%Y-%m-%dT%H:%M:%SZ"
  default = null
}

variable "tags" {
  type = map(string)
  default = {
    Group : "turms"
    Type : "standalone"
  }
}

# Instance - Disk

variable "disk_size" {
  type    = number
  default = 20
}

# Instance - Network

variable "max_band_out" {
  type    = number
  default = 1
}

# Instance - System

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