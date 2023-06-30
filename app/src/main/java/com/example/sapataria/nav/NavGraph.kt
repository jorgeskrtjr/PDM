package com.example.sapataria

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sapataria.nav.Screens
import com.example.sapataria.screen.*
import com.example.sapataria.screen.pedido.PedidoAddDataScreen
import com.example.sapataria.screen.pedido.ProdutoAddDataScreen
import com.example.sapataria.screen.pedido.ProdutoMainScreen
import com.example.sapataria.screen.produto.PedidoMainScreen
import com.example.sapataria.screen.produto.ProdutoGetDataScreen
import com.example.sapataria.util.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {

        composable(
            route = Screens.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
            )
        }

        //PRODUTOS

        composable(
            route = Screens.ProdutoMainScreen.route
        ) {
          ProdutoMainScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
          )
        }

        composable(
            route = Screens.ProdutoAddDataScreen.route
        ) {
            ProdutoAddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screens.ProdutoGetDataScreen.route
        ) {
            ProdutoGetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        //PEDIDOS

        composable(
            route = Screens.PedidoMainScreen.route
        ) {
            PedidoMainScreen(
                navController = navController,
            )
        }

        composable(
            route = Screens.PedidoAddDataScreen.route
        ) {
            PedidoAddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }


        // CLIENTES

        composable(
            route = Screens.ClienteMainScreen.route
        ) {
           ClienteMainScreen(
               navController = navController,
               sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screens.ClienteGetDataScreen.route
        ) {
            ClienteGetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screens.ClienteAddDataScreen.route
        ) {
            ClienteAddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}