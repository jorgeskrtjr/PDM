package com.example.sapataria.classes

data class Pedido(val idPedido: Int, val idCliente: Int, val idProduto: Int, val total: Double) {

    constructor() : this(0, 0, 0, 0.0)

    companion object {
        private var nextId = 2

        fun getNextId(): Int {
            return nextId++
        }
    }

}