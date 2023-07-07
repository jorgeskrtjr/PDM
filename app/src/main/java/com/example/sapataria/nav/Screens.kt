package com.example.sapataria.nav

sealed class Screens(val route: String) {

    object FazendaGetDataScreen: Screens(route = "fazenda_get_data_screen")
    object FazendaAddDataScreen: Screens(route = "fazenda_add_data_screen")
    object FazendaMainScreen: Screens(route = "fazenda_data_screen")



}