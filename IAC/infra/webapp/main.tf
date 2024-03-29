data "azurerm_key_vault_secret" "CCR21KV" {
  name         = "tokenstore"
  key_vault_id = "/subscriptions/c41a438a-5186-4798-a8aa-c1f1738903bf/resourceGroups/terraformRG/providers/Microsoft.KeyVault/vaults/ccr21kv"
}

resource "azurerm_app_service_plan" "appplan" {
  is_xenon                     = false
  kind                         = "linux"
  location                     = var.location
  maximum_elastic_worker_count = var.max_ewc_app
  name                         = "asp-${var.app_name}"
  per_site_scaling             = false
  reserved                     = true
  resource_group_name          = var.resource_group_name
  tags                         = {}

  sku {
    capacity = var.app_capacity
    size     = var.app_size
    tier     = var.app_tier
  }

  timeouts {}
}

resource "azurerm_app_service" "webapp" {
  app_service_plan_id     = azurerm_app_service_plan.appplan.id
  client_affinity_enabled = true
  client_cert_enabled     = false
  enabled                 = true
  https_only              = false
  location                = var.location
  name                    = var.app_name
  resource_group_name     = var.resource_group_name

  tags = {}

  auth_settings {
        additional_login_params        = {}
        allowed_external_redirect_urls = []
        enabled                        = true
        issuer                         = "https://sts.windows.net/1617e123-e7d5-4f4e-9678-f24957cc153a/v2.0"
        runtime_version                = "~1"
        token_refresh_extension_hours  = 0
        token_store_enabled            = true
        unauthenticated_client_action  = "RedirectToLoginPage"

        active_directory {
            allowed_audiences = []
            client_id         = "a1e59cfe-6967-4400-8391-adce0cb84eed"
        }
    }
  
    app_settings = {
    "MICROSOFT_PROVIDER_AUTHENTICATION_SECRET" = "${data.azurerm_key_vault_secret.CCR21KV.value}"
  }

  site_config {
    always_on = true
    default_documents = [
      "Default.htm",
      "Default.html",
      "Default.asp",
      "index.htm",
      "index.html",
      "iisstart.htm",
      "default.aspx",
      "index.php",
      "hostingstart.html",
    ]
    dotnet_framework_version    = "v4.0"
    ftps_state                  = "AllAllowed"
    http2_enabled               = false
    ip_restriction              = []
    linux_fx_version            = "TOMCAT|8.5-jre8"
    local_mysql_enabled         = false
    managed_pipeline_mode       = "Integrated"
    min_tls_version             = "1.2"
    number_of_workers           = 1
    remote_debugging_enabled    = false
    scm_ip_restriction          = []
    scm_type                    = "None"
    scm_use_main_ip_restriction = false
    use_32_bit_worker_process   = true
    websockets_enabled          = false
  }

  timeouts {}
}

resource "azurerm_app_service_slot" "CCR-webapp" {
  name                = "${var.app_name}-slot"
  location            = var.location
  resource_group_name = var.resource_group_name
  app_service_plan_id = azurerm_app_service_plan.appplan.id
  app_service_name    = azurerm_app_service.webapp.name
}
