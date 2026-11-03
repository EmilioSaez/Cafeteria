package com.example.cafeteria.ui.theme.navegacion

import kotlinx.serialization.Serializable
@Serializable
data object Carta
@Serializable
data class Detalle(val idProducto: Int)
@Serializable
data object Resumen
@Serializable
data class PedidoEnviado(val nombre: String)