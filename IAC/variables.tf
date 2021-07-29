variable "resource_group_name" {
  default = "CCR21"
}

variable "location" {
  default = "westeurope"
}

variable "max_ewc_app" {
  default = "1"
}

variable "app_capacity" {
  default = "1"
}

variable "app_size" {
  default = "P1v2"
}

variable "app_tier" {
  default = "PremiumV2"
}

variable "app_name" {
  default = "ccr21-webapp"
}


variable "administrator_login" {
  default = "sa4mysql"
}

variable "db_srvname" {
  default = "ccr21mysqlsrv"
}

variable "dbsku" {
  default = "B_Gen5_2"
}

variable "dbname" {
  default = "CCR21BD"
}

variable "dbcharset" {
  default = "utf8"
}
variable "dbcollation" {
  default = "utf8_unicode_ci"

}