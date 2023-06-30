package com.example.sapataria.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sapataria.classes.Cliente
import com.example.sapataria.nav.Screens
import com.example.sapataria.ui.theme.Purple500
import com.example.sapataria.util.SharedViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Shape
import com.example.sapataria.ui.theme.Purple200


@Composable
fun ClienteMainScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val listaClientes = sharedViewModel.listaClientesStateFlow.collectAsState(initial = emptyList()).value

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
            Text(text = "Clientes ", color = Purple500, fontSize = 20.sp)
        }


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(12f)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.ClienteGetDataScreen.route)
                }
            ) {
                Text(text = "Buscar cliente")
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.ClienteAddDataScreen.route)
                }
            ) {
                Text(text = "Adicionar cliente")
            }
        }

        LazyColumn(modifier = Modifier.weight(16f)) {
            items(listaClientes) { cliente ->
                ClienteItemComponent(cliente)
            }
        }

    }

}

@Composable
fun ClienteItemComponent(cliente: Cliente) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp),
        border = BorderStroke(2.dp, Purple500),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cliente.nome, fontSize = 16.sp)
            Text(text = "Telefone: " + cliente.telefone  + " ID: "+ cliente.id, fontSize = 14.sp)
        }
    }
}