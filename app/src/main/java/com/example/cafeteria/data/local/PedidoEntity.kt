package com.example.cafeteria.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class PedidoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    @ColumnInfo(name = "para_llevar")
    val paraLlevar: Boolean,
    @ColumnInfo(name = "fecha_hora")
    val fechaHora: Long,
    val unidades: Int,
    val total: Double
)