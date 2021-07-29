data "azurerm_key_vault_secret" "CCR21KV" {
  name         = "bdadmin"
  key_vault_id = "/subscriptions/c41a438a-5186-4798-a8aa-c1f1738903bf/resourceGroups/terraformRG/providers/Microsoft.KeyVault/vaults/ccr21kv"
}

resource "azurerm_mysql_server" "mysqlserver" {
  administrator_login = var.administrator_login
  administrator_login_password = "${data.azurerm_key_vault_secret.CCR21KV.value}"
  auto_grow_enabled                 = false
  backup_retention_days             = 7
  create_mode                       = "Default"
  geo_redundant_backup_enabled      = false
  infrastructure_encryption_enabled = false
  location                          = "westeurope"
  name                              = var.db_srvname
  public_network_access_enabled     = true
  resource_group_name               = var.resource_group_name
  sku_name                          = var.dbsku
  ssl_enforcement_enabled           = true
  ssl_minimal_tls_version_enforced  = "TLS1_2"
  storage_mb                        = 51200
  tags                              = {}
  version                           = "8.0"

  timeouts {}
}

resource "azurerm_mysql_database" "CCR21BD" {
  name                = var.dbname
  resource_group_name = var.resource_group_name
  server_name         = azurerm_mysql_server.mysqlserver.name
  charset             = var.dbcharset
  collation           = var.dbcollation
}
