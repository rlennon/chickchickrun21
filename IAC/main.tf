resource "azurerm_resource_group" "CCR" {
  name     = var.resource_group_name
  location = var.location
}

resource "time_sleep" "wait_30_seconds" {
  depends_on      = [azurerm_resource_group.CCR]
  create_duration = "30s"
}

module "database" {
  source = "./infra/database"

  depends_on = [azurerm_resource_group.CCR, time_sleep.wait_30_seconds]

  resource_group_name = var.resource_group_name

  location = var.location

  administrator_login = var.administrator_login

  db_srvname = var.db_srvname

  dbsku = var.dbsku

  dbname = var.dbname

  dbcharset = var.dbcharset

  dbcollation = var.dbcollation
}

module "webapp" {
  source = "./infra/webapp"

  depends_on = [azurerm_resource_group.CCR, time_sleep.wait_30_seconds]

  resource_group_name = var.resource_group_name

  location = var.location

  max_ewc_app = var.max_ewc_app

  app_capacity = var.app_capacity

  app_size = var.app_size

  app_tier = var.app_tier

  app_name = var.app_name


}