package com.example.sapataria.screen.pedido

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sapataria.classes.Pedido
import com.example.sapataria.util.SharedViewModel

@Composable
fun PedidoAddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var idCliente: String by remember { mutableStateOf("") }
    var idProduto: String by remember { mutableStateOf("") }
    var quantidadeEstoque: String by remember { mutableStateOf("") }
    var descricao: String by remember { mutableStateOf("") }
    var valor: String by remember { mutableStateOf("0.0") }
    var nome: String by remember { mutableStateOf("") }
    var quantidade: String by remember { mutableStateOf("0.0") }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
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
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                value = idCliente,
                onValueChange = {
                    idCliente = it
                },
                label = {
                    Text(text = "id")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Button(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(100.dp),
                onClick = {
                    sharedViewModel.recuperarDados(
                        id = idCliente,
                        context = context
                    ) { cliente ->
                        nome = cliente.nome
                    }
                }
            ) {
                Text(text = "Buscar")
            }
        }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text(text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = idProduto,
                    onValueChange = {
                        idProduto = it
                    },
                    label = {
                        Text(text = "Id do produto")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = {
                        sharedViewModel.recuperarDadosProduto(
                            produtoId = idProduto,
                            context = context
                        ) { produto ->
                            descricao = produto.descricao
                            valor = produto.valor.toString()
                            quantidadeEstoque = produto.quantidade.toString()
                        }
                    }
                ) {
                    Text(text = "Buscar")
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descricao,
                onValueChange = {
                    descricao = it
                },
                label = {
                    Text(text = "Descricao")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantidadeEstoque,
                onValueChange = {
                    quantidadeEstoque = it
                },
                label = {
                    Text(text = "Em estoque")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )

            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = valor,
                onValueChange = {
                    valor = it
                },
                label = {
                    Text(text = "Valor")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = valor,
                onValueChange = {
                    valor = it
                },
                label = {
                    Text(text = "Quantidade")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Text(text= "Total:  "+ "${quantidade.toInt() * valor.toDouble()}")

            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val pedido = Pedido(
                        idPedido =  Pedido.getNextId(),
                        idCliente = idCliente.toInt(),
                        idProduto = idProduto.toInt(),
                        total = quantidade.toInt() * valor.toDouble()
                    )

                    sharedViewModel.salvarPedido(pedido = pedido, context = context)
                }
            ) {
                Text(text = "Concluir pedido")
            }
        }
    }
}