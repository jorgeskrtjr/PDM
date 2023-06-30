package com.example.sapataria.classes


data class Produto(val produtoId: Int, val descricao: String, val valor: Double, val quantidade: Int) {
    companion object {
        private var nextId = 2

        fun getNextId(): Int {
            return nextId++
        }
    }

    constructor() : this(getNextId(), "", 0.0, 0)

    override fun toString(): String {
        return "Produto(produtoId=$produtoId, descricao='$descricao', valor=$valor, quantidade=$quantidade)"
    }
}