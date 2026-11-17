package com.example.cafeteria.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PedidoDao {

    @Insert
    suspend fun insertar(pedido: PedidoEntity): Long

    @Query("SELECT COUNT(*) FROM pedidos WHERE nombre = :nombre")
    suspend fun contarPorNombre(nombre: String): Int

    @Query("SELECT COUNT(*) FROM pedidos")
    suspend fun contarTodos(): Int

    @Query("DELETE FROM pedidos")
    suspend fun borrarTodos()
}