package com.example.sapataria.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.sapataria.classes.Fazenda
import com.example.sapataria.nav.Screens
import com.example.sapataria.ui.theme.Purple500
import com.example.sapataria.util.SharedViewModel


@Composable
fun FazendaMainScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val listaFazendas = sharedViewModel.listaFazendasStateFlow.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),

        ) {
            Text(text = "Fazendas ", color = Purple500, fontSize = 20.sp)
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
                    navController.navigate(route = Screens.FazendaGetDataScreen.route)
                }
            ) {
                Text(text = "Buscar Fazenda")
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.FazendaAddDataScreen.route)
                }
            ) {
                Text(text = "Adicionar Fazenda")
            }
        }

        LazyColumn(modifier = Modifier.weight(16f)) {
            items(listaFazendas) { Fazenda ->
                FazendaItemComponent(Fazenda)
            }
        }

    }

}

@Composable
fun FazendaItemComponent(Fazenda: Fazenda) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp),
        border = BorderStroke(2.dp, Purple500),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = Fazenda.nome, fontSize = 16.sp)
            Text(text = "Nome: " + Fazenda.nome  + " ID: "+ Fazenda.id, fontSize = 14.sp)
            Text(text = "Valor: " + Fazenda.valorDaPropiedade  + " qtd: "+ Fazenda.qtdFuncionarios, fontSize = 12.sp)
        }
    }
}