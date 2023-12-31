package com.example.sapataria.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sapataria.util.SharedViewModel
import com.example.sapataria.classes.Fazenda

@Composable
fun FazendaAddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var id: String by remember { mutableStateOf("") }
    var nome: String by remember { mutableStateOf("") }
    var valor: String by remember { mutableStateOf("") }
    var quantidade: String by remember { mutableStateOf("") }


    val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // back button
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
        // add data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // userID
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = id,
                onValueChange = {
                    id = it
                },

                label = {
                    Text(text = "UserID")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nome,
                onValueChange = {
                    nome = it
                },
                        label = {
                    Text(text = "Name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
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
                    Text(text = "Valor da fazenda")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantidade,
                onValueChange = {
                    quantidade = it
                },
                label = {
                    Text(text = "Quantidade de funcionários")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val fazenda = Fazenda(
                        id = id.toInt(),
                        nome = nome,
                        valorDaPropiedade = valor.toDouble(),
                        qtdFuncionarios = quantidade.toInt()
                    )

                    sharedViewModel.salvar(Fazenda = fazenda, context = context)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}