package com.example.cafeteria.ui.theme.carta

import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.Producto
import com.example.cafeteria.data.Ticket

data class PedidoUiState(
    val cantidades: Map<Int, Int> = emptyMap(),
    val nombre: String = "",
    val paraLlevar: Boolean = false,
    val avisar: Boolean = true,
    val intentoConfirmar: Boolean = false,
    val mostrarConfirmacion: Boolean = false,
    val categoria: Categoria? = null,
    val ultimoTicket: Ticket? = null,
    val mostrarTicket: Boolean = false,
    val pedidosCliente: Int? = null

)

data class LineaPedido(val producto: Producto, val cantidad: Int)
