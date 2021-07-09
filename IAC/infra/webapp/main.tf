resource "azurerm_app_service_plan" "CCR-app" {
  name                = var.webappname
  location            = var.location
  resource_group_name = var.resource_group_name

  sku {
    tier = "Production"
    size = "p1v2"
  }
}

resource "azurerm_app_service" "CCR-webapp" {
  name                = var.webappname
  location            = var.location
  resource_group_name = var.resource_group_name
  app_service_plan_id = azurerm_app_service_plan.CCR-app.id
}

resource "azurerm_app_service_slot" "CCR-webapp" {
  name                = "${var.webappname}-slot"
  location            = var.location
  resource_group_name = var.resource_group_name
  app_service_plan_id = azurerm_app_service_plan.CCR-app.id
  app_service_name    = azurerm_app_service.CR-webapp.name
}