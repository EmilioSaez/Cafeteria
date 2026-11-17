package com.example.cafeteria.data

import kotlinx.serialization.Serializable

@Serializable
data class LineaTicket(
    val idProducto: Int,
    val cantidad: Int,
    val precioUnitario: Double
)

@Serializable
data class Ticket(
    val nombre: String,
    val paraLlevar: Boolean,
    val fechaHora: Long,
    val lineas: List<LineaTicket>,
    val total: Double
)