package com.example.sapataria

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sapataria.nav.Screens
import com.example.sapataria.screen.*
import com.example.sapataria.util.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.FazendaMainScreen.route
    ) {

        composable(
            route = Screens.FazendaMainScreen.route
        ) {
           FazendaMainScreen(
               navController = navController,
               sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screens.FazendaGetDataScreen.route
        ) {
            FazendaGetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screens.FazendaAddDataScreen.route
        ) {
            FazendaAddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}