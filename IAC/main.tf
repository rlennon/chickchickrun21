resource "azurerm_resource_group" "CCR" {
  name     = "CCR-RG"
  location = "West Europe"
}

module "database" {
  source = "./IAC/infra/database"

}

module "webapp" {
  source = "./IAC/infra/webapp"

}