package com.example.sapataria.screen

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
import com.example.sapataria.classes.Fazenda
import com.example.sapataria.util.SharedViewModel

@Composable
fun FazendaGetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var id: String by remember { mutableStateOf("") }
    var nome: String by remember { mutableStateOf("") }
    var qtdFuncionarios: String by remember { mutableStateOf("") }

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
                    value = id,
                    onValueChange = {
                        id = it
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

                            sharedViewModel.recuperarDadosPeloID(
                                id = id,
                                nome = nome,
                                context = context
                            ) { Fazenda ->
                                nome = Fazenda.nome
                                qtdFuncionarios = Fazenda.qtdFuncionarios.toString()
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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = qtdFuncionarios,
                onValueChange = {
                    qtdFuncionarios = it
                },
                label = {
                    Text(text = "Quantidade de funcionários")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val Fazenda = Fazenda(
                        id = id.toInt(),
                        nome = nome,
                        valorDaPropiedade = 0.0,
                        qtdFuncionarios = 0
                    )

                    sharedViewModel.salvar(Fazenda = Fazenda, context = context)
                }
            ) {
                Text(text = "Salvar")
            }
            // delete Button
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    sharedViewModel.deletarDados(
                        id = id,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Deletar")
            }
        }
    }
}