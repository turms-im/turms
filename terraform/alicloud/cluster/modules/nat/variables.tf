variable "create_nat" {}

#=============== Nat Gateway

variable "nat_name" {}

variable "nat_vpc_id" {}

variable "nat_vswitch_id" {}

variable "nat_payment_type" {}

variable "nat_specification" {}

#=============== SNAT

variable "snat_entry_name" {}

variable "snat_source_vswitch_ids" {}

#=============== EIP

variable "eip_address_name" {}

variable "eip_tags" {}

# Charge
variable "eip_internet_charge_type" {}
variable "eip_payment_type" {}

# Network
variable "eip_bandwidth" {}
variable "eip_isp" {}