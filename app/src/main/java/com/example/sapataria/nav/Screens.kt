package com.example.sapataria.nav

sealed class Screens(val route: String) {
    object MainScreen: Screens(route = "main_screen")

    object ClienteGetDataScreen: Screens(route = "cliente_get_data_screen")
    object ClienteAddDataScreen: Screens(route = "cliente_add_data_screen")
    object ClienteMainScreen: Screens(route = "cliente_data_screen")

    object ProdutoMainScreen: Screens(route = "produto_data_screen")
    object ProdutoAddDataScreen: Screens(route = "produto_add_data_screen")
    object ProdutoGetDataScreen: Screens(route = "produto_get_data_screen")

    object PedidoMainScreen: Screens(route = "pedidos_data_screen")
    object PedidoAddDataScreen: Screens(route = "pedidos_add_data_screen")



}