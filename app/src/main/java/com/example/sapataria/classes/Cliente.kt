package com.example.sapataria.classes

data class Cliente (var id: Int, val nome: String, val telefone: String){

    companion object {
        private var nextId = 1

        fun getNextId(): Int {
            return nextId++
        }
    }

    constructor() : this(0, "", "")

    override fun toString(): String {
        return "Cliente(clienteId=$id, nome='$nome', telefone='$telefone')"
    }

}