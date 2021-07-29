# Configure the Azure provider
provider "azurerm" {
  # The "feature" block is required for AzureRM provider 2.x. 
  # If you are using version 1.x, the "features" block is not allowed.
  version = "~>2.0"
  features {}
}

# Swap the production slot and the staging slot
resource "azurerm_app_service_active_slot" "slotDemoActiveSlot" {
  resource_group_name   = var.resource_group_name
  app_service_name      = var.app_name
  app_service_slot_name = "${var.app_name}-slot"
}