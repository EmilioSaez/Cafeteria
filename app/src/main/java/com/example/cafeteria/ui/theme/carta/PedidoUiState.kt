package com.example.cafeteria.ui.theme.carta

import com.example.cafeteria.data.Categoria

data class PedidoUiState(
    val cantidades: Map<Int, Int> = emptyMap(),
    val nombre: String = "",
    val paraLlevar: Boolean = false,
    val avisar: Boolean = true,
    val intentoConfirmar: Boolean = false,
    val mostrarConfirmacion: Boolean = false,
    val categoria: Categoria? = null
)