package com.example.sapataria.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sapataria.classes.Cliente
import com.example.sapataria.classes.Pedido
import com.example.sapataria.classes.Produto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(): ViewModel() {

    private val _listaClientes = mutableStateListOf<Cliente>()
    private val _listaClientesStateFlow = MutableStateFlow<List<Cliente>>(emptyList())
    val listaClientesStateFlow: StateFlow<List<Cliente>> = _listaClientesStateFlow

    private val _listaProdutos = mutableStateListOf<Produto>()
    private val _listaProdutosStateFlow = MutableStateFlow<List<Produto>>(emptyList())
    val listaProdutosStateFlow: StateFlow<List<Produto>> = _listaProdutosStateFlow

    init {
        recuperarListaClientesTeste()
        recuperarListaProdutos()
    }

    fun salvar(cliente: Cliente, context: Context)
    = CoroutineScope(Dispatchers.IO)
        .launch{
            val fireStoreRef = Firebase.firestore
                .collection("clientes")
                .document(cliente.id.toString())

            try {

                fireStoreRef.set(cliente).addOnSuccessListener {
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }

        }

    fun salvarProduto(produto: Produto, context: Context)
            = CoroutineScope(Dispatchers.IO)
        .launch{
            val fireStoreRef = Firebase.firestore
                .collection("produtos")
                .document(produto.produtoId.toString())

            try {

                fireStoreRef.set(produto).addOnSuccessListener {
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }

        }

    fun recuperarDados(
        id: String,
        context: Context,
        cliente: (Cliente) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("clientes")
            .document(id)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val Cliente = it.toObject<Cliente>()!!
                        cliente(Cliente)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun recuperarDadosProduto(
        produtoId: String,
        context: Context,
        produto: (Produto) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("produtos")
            .document(produtoId)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val Produto = it.toObject<Produto>()!!
                        produto(Produto)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun deletarDados(
        id: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("clientes")
            .document(id)


        if(fireStoreRef.id != null) {

            try {
                fireStoreRef.delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Excluído com sucesso", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }else{

            Toast.makeText(context, "Usuário não consta no sistema", Toast.LENGTH_LONG).show()

        }
    }

    fun recuperarListaClientes() = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore

        fireStoreRef.collection("clientes")
            .get()
            .addOnSuccessListener { documents ->

                val listaClientes = mutableListOf<Cliente>()

                for (document in documents) {
                    listaClientes.add(document.toObject())
                    Log.d("testando", "${document.id} => ${document.data}")
                }

                Log.w("testando", "$listaClientes")

            }
            .addOnFailureListener { exception ->
                Log.w("testando", "Error getting documents: ", exception)
            }

    }

    private fun recuperarListaClientesTeste() {
        val collectionRef = Firebase.firestore.collection("clientes")

        collectionRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("testando", "Erro ao recuperar a lista de clientes: $exception")
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                val listaClientes = querySnapshot.documents.mapNotNull { document ->
                    document.toObject<Cliente>()
                }
                _listaClientes.clear()
                _listaClientes.addAll(listaClientes)
                _listaClientesStateFlow.value = listaClientes
            }
        }
    }


    private fun recuperarListaProdutos() {

        val collectionRef = Firebase.firestore.collection("produtos")

        collectionRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("testando", "Erro ao recuperar a lista de produtos: $exception")
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                val listaProdutos = querySnapshot.documents.mapNotNull { document ->
                    document.toObject<Produto>()
                }
                _listaProdutos.clear()
                _listaProdutos.addAll(listaProdutos)
                _listaProdutosStateFlow.value = listaProdutos
            }
        }
    }

    fun salvarPedido(pedido: Pedido, context: Context) {

        val fireStoreRefClientes = Firebase.firestore
            .collection("clientes")
            .document(pedido.idCliente.toString())

        val fireStoreRefProdutos = Firebase.firestore
            .collection("produtos")
            .document(pedido.idProduto.toString())

        val fireStoreRef = Firebase.firestore
            .collection("pedidos")
            .document(pedido.idPedido.toString())

        try {
            fireStoreRefClientes.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        Toast.makeText(context, "passou o cliente", Toast.LENGTH_SHORT).show()
                        fireStoreRefClientes.get() .addOnSuccessListener {
                            if(it.exists()){
                                fireStoreRef.set(pedido).addOnSuccessListener {
                                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(context, "Esse produto não foi cadastrado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Esse cliente não foi cadastrado", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }


}