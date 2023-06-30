package com.example.sapataria.screen.produto

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sapataria.nav.Screens
import com.example.sapataria.ui.theme.Purple200
import com.example.sapataria.ui.theme.Purple500

@Composable
fun PedidoMainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),

            ) {
            Text(text = "Pedidos", color = Purple500, fontSize = 20.sp)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(15f)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.ClienteGetDataScreen.route)
                }
            ) {
                Text(text = "Buscar pedido")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.PedidoAddDataScreen.route)
                }
            ) {
                Text(text = "Adicionar pedido")
            }
        }
    }
}
