package com.example.cafeteria.data

import com.example.cafeteria.data.local.PedidoDao
import com.example.cafeteria.data.local.PedidoEntity

class HistorialRepository(private val pedidoDao: PedidoDao) {

    suspend fun registrar(ticket: Ticket): Long =
        pedidoDao.insertar(
            PedidoEntity(
                nombre = ticket.nombre,
                paraLlevar = ticket.paraLlevar,
                fechaHora = ticket.fechaHora,
                unidades = ticket.lineas.sumOf { it.cantidad },
                total = ticket.total
            )
        )

    suspend fun contarPedidos(nombre: String): Int =
        pedidoDao.contarPorNombre(nombre)

    suspend fun contarTodos(): Int =
        pedidoDao.contarTodos()

    suspend fun borrarTodo() =
        pedidoDao.borrarTodos()
}