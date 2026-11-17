package com.example.cafeteria.ui.theme.carta

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.LineaTicket
import com.example.cafeteria.data.PreferenciasRepository
import com.example.cafeteria.data.Producto
import com.example.cafeteria.data.Ticket
import com.example.cafeteria.CafeteriaApp
import com.example.cafeteria.data.HistorialRepository
import com.example.cafeteria.data.TicketRepository
import com.example.cafeteria.data.dataStore
import com.example.cafeteria.objects.DatosCarta
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private const val TAG = "PedidoVM"

class PedidoViewModel(application: Application) : AndroidViewModel(application) {


    private val preferenciasRepository = PreferenciasRepository(application.dataStore)
    private val ticketRepository = TicketRepository(application.filesDir)
    private val historialRepository = HistorialRepository(
        (application as CafeteriaApp).database.pedidoDao()
    )
    val productos: List<Producto> = DatosCarta.productos


    var uiState by mutableStateOf(PedidoUiState())
        private set

    init {
        Log.d(TAG, "PedidoViewModel creado")
        viewModelScope.launch {
            val guardadas = preferenciasRepository.preferencias.first()
            val ticket = ticketRepository.cargar()
            Log.d(TAG, "Cargado: preferencias=$guardadas, ticket=$ticket")
            uiState = uiState.copy(
                nombre = guardadas.nombre,
                paraLlevar = guardadas.paraLlevar,
                ultimoTicket = ticket
            )
        }
    }

    val lineasPedido: List<LineaPedido>
        get() = productos.mapNotNull { producto ->
            val cantidad = uiState.cantidades[producto.id] ?: 0
            if (cantidad > 0) LineaPedido(producto, cantidad) else null
        }

    val productosFiltrados: List<Producto>
        get() = uiState.categoria?.let { cat -> productos.filter { it.categoria == cat } } ?: productos

    val unidades: Int
        get() = uiState.cantidades.values.sum()

    val total: Double
        get() = productos.sumOf { (uiState.cantidades[it.id] ?: 0) * it.precio }

    val errorNombre: Boolean
        get() = uiState.intentoConfirmar && uiState.nombre.isBlank()


    fun buscarProducto(id: Int): Producto? = productos.find { it.id == id }

    fun cambiarCantidad(idProducto: Int, cantidad: Int) {
        uiState = uiState.copy(cantidades = uiState.cantidades + (idProducto to cantidad))
    }

    fun vaciarPedido() {
        uiState = uiState.copy(cantidades = emptyMap())
    }

    fun cambiarNombre(nombre: String) {
        uiState = uiState.copy(nombre = nombre)
    }

    fun cambiarParaLlevar() {
        uiState = uiState.copy(paraLlevar = !uiState.paraLlevar)
    }

    fun cambiarAvisar() {
        uiState = uiState.copy(avisar = !uiState.avisar)
    }

    fun seleccionarCategoria(categoria: Categoria?) {
        uiState = uiState.copy(categoria = categoria)
    }

    fun confirmar() {
        uiState = uiState.copy(
            intentoConfirmar = true,
            mostrarConfirmacion = uiState.nombre.isNotBlank()
        )
    }

    fun cancelarConfirmar() {
        uiState = uiState.copy(mostrarConfirmacion = false)
    }

    fun enviarPedido() {
        val nombre = uiState.nombre
        val paraLlevar = uiState.paraLlevar

        val ticket = Ticket(
            nombre = nombre,
            paraLlevar = paraLlevar,
            fechaHora = System.currentTimeMillis(),
            lineas = lineasPedido.map { linea ->
                LineaTicket(
                    idProducto = linea.producto.id,
                    cantidad = linea.cantidad,
                    precioUnitario = linea.producto.precio
                )
            },
            total = total
        )

        uiState = uiState.copy(
            cantidades = emptyMap(),
            mostrarConfirmacion = false,
            intentoConfirmar = false,
            ultimoTicket = ticket
        )

        viewModelScope.launch {
            preferenciasRepository.guardar(nombre, paraLlevar)
            ticketRepository.guardar(ticket)
            val id = historialRepository.registrar(ticket)
            val totalPedidos = historialRepository.contarTodos()
            Log.d(TAG, "Pedido registrado con id=$id. Pedidos en total: $totalPedidos")
        }
    }

    fun verTicket() {
        uiState = uiState.copy(mostrarTicket = true, pedidosCliente = null)
        val ticket = uiState.ultimoTicket ?: return
        viewModelScope.launch {
            val pedidos = historialRepository.contarPedidos(ticket.nombre)
            uiState = uiState.copy(pedidosCliente = pedidos)
        }
    }

    fun cerrarTicket() {
        uiState = uiState.copy(mostrarTicket = false)
    }

    fun olvidarDatos() {
        uiState = uiState.copy(
            cantidades = emptyMap(),
            mostrarConfirmacion = false,
            intentoConfirmar = false,
            ultimoTicket = null,
            mostrarTicket = false,
            pedidosCliente = null
        )
        viewModelScope.launch {
            preferenciasRepository.borrar()
            ticketRepository.borrar()
            historialRepository.borrarTodo()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "PedidoViewModel destruido")
    }
}