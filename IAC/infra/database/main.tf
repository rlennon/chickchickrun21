resource "azurerm_postgresql_server" "CCR-DBSRV" {
  name                = var.dbsrvname
  location            = var.location
  resource_group_name = var.Rgname

  administrator_login          = var.sqlaadmin
  administrator_login_password = ""

  sku_name   = var.dbsku
  version    = var.dbversion
  storage_mb = var.dbsize

  backup_retention_days        = var.dbretention
  geo_redundant_backup_enabled = true
  auto_grow_enabled            = true

  public_network_access_enabled    = true
  ssl_enforcement_enabled          = true
  ssl_minimal_tls_version_enforced = "TLS1_2"
}


esource "azurerm_postgresql_database" "CCR-DB" {
  name                = var.dbname
  resource_group_name = VAR.Rgname
  server_name         = azurerm_postgresql_server.CCR-DBSRV.name
  charset             = var.Charset
  collation           = var.collation
}