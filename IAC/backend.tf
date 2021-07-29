terraform {
  backend "azurerm" {
    storage_account_name = "lyitterrastate"
    container_name       = "lyitcontainer"
    key                  = "ccr21.tfstate"
    access_key = "e1ya49FGlCSzKj7K1civ32f+TqHD7+w/hpw2F5tPUsfS/LOV/swm+cqMxEWAqiB6Wqjyd/To6EDanhypOqQ3SA=="
  }
}
