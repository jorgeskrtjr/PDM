package com.example.sapataria.classes

data class Fazenda (var id: Int, val nome: String, val valorDaPropiedade: Double, val qtdFuncionarios: Int){

    companion object {
        private var nextId = 1

        fun getNextId(): Int {
            return nextId++
        }
    }

    constructor() : this(0, "", 0.0, 0)

    override fun toString(): String {
        return "Cliente(clienteId=$id, nome='$nome', telefone='$valorDaPropiedade', funcionarios='$qtdFuncionarios')"
    }

}