package com.example.sapataria.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sapataria.classes.Fazenda
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class SharedViewModel(): ViewModel() {



    fun salvar(Fazenda: Fazenda, context: Context)
    = CoroutineScope(Dispatchers.IO)
        .launch{
            val fireStoreRef = Firebase.firestore
                .collection("Fazendas")
                .document(Fazenda.id.toString())

            try {

                fireStoreRef.set(Fazenda).addOnSuccessListener {
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }

        }


    fun recuperarDadosPeloID(
        id: String,
        nome: String,
        context: Context,
        fazenda: (Fazenda) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (nome.isNotEmpty()) {
            val firestoreRef = Firebase.firestore.collection("fazendas")

            try {
                firestoreRef.whereEqualTo("nome", nome).get().addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val dadosFazenda = querySnapshot.documents[0].toObject<Fazenda>()!!
                        fazenda(dadosFazenda)
                    } else {
                        Toast.makeText(context, "Fazenda não encontrada", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            val fireStoreRef = Firebase.firestore
                .collection("Fazendas")
                .document(id)

            try {
                fireStoreRef.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val fazenda = documentSnapshot.toObject<Fazenda>()!!
                        fazenda(fazenda)
                    } else {
                        Toast.makeText(context, "Fazenda não encontrada", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun deletarDados(
        id: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("Fazendas")
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

    private val _listaFazendas = mutableStateListOf<Fazenda>()
    private val _listaFazendasStateFlow = MutableStateFlow<List<Fazenda>>(emptyList())
    val listaFazendasStateFlow: StateFlow<List<Fazenda>> = _listaFazendasStateFlow


    init {
        recuperarListaFazendasTeste()
    }


    private fun recuperarListaFazendasTeste() {
        val collectionRef = Firebase.firestore.collection("Fazendas")

        collectionRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("testando", "Erro ao recuperar a lista de Fazendas: $exception")
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                val listaFazendas = querySnapshot.documents.mapNotNull { document ->
                    document.toObject<Fazenda>()
                }
                _listaFazendas.clear()
                _listaFazendas.addAll(listaFazendas)
                _listaFazendasStateFlow.value = listaFazendas
            }
        }
    }





}