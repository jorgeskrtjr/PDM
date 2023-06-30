package com.example.sapataria.screen.pedido

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
import com.example.sapataria.classes.Cliente
import com.example.sapataria.classes.Produto
import com.example.sapataria.nav.Screens
import com.example.sapataria.screen.ClienteItemComponent
import com.example.sapataria.ui.theme.Purple200
import com.example.sapataria.ui.theme.Purple500
import com.example.sapataria.ui.theme.Purple700
import com.example.sapataria.util.SharedViewModel

@Composable
fun ProdutoMainScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    val listaProdutos = sharedViewModel.listaProdutosStateFlow.collectAsState(initial = emptyList()).value

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
            Text(text = "Produtos ", color = Purple500, fontSize = 20.sp)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(15f)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.ProdutoGetDataScreen.route)
                }
            ) {
                Text(text = "Buscar produto")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp),
                onClick = {
                    navController.navigate(route = Screens.ProdutoAddDataScreen.route)
                }
            ) {
                Text(text = "Adicionar produto")
            }
        }

        LazyColumn(modifier = Modifier.weight(16f)) {
            items(listaProdutos) { produto ->
                ProdutoItemComponent(produto)
            }
        }
    }
}

@Composable
fun ProdutoItemComponent(produto: Produto) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp),
        border = BorderStroke(2.dp, Purple500),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text =  produto.descricao, fontSize = 16.sp)
            Text(text = "Quantidade: " + produto.quantidade.toString(), fontSize = 14.sp)
            Text(text = "Valor: R$" + produto.valor.toString(), fontSize = 14.sp)
        }
    }
}