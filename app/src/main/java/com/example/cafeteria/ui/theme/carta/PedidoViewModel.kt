package com.example.cafeteria.ui.theme.carta

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.Producto
import com.example.cafeteria.objects.DatosCarta

class PedidoViewModel : ViewModel() {

    val productos: List<Producto> = DatosCarta.productos

    var uiState by mutableStateOf(PedidoUiState())
        private set

    val productosFiltrados: List<Producto>
        get() = uiState.categoria?.let { cat -> productos.filter { it.categoria == cat } } ?: productos

    val unidades: Int
        get() = uiState.cantidades.values.sum()

    val total: Double
        get() = productos.sumOf { (uiState.cantidades[it.id] ?: 0) * it.precio }

    val errorNombre: Boolean
        get() = uiState.intentoConfirmar && uiState.nombre.isBlank()

    fun cambiarCantidad(idProducto: Int, cantidad: Int) {
        uiState = uiState.copy(cantidades = uiState.cantidades + (idProducto to cantidad))
    }

    fun vaciarPedido() {
        uiState = uiState.copy(cantidades = emptyMap())
    }

    fun cambiarNombre(nombre: String) {
        uiState = uiState.copy(nombre = nombre)   // sustituye, no concatena
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
        uiState = uiState.copy(
            cantidades = emptyMap(),
            mostrarConfirmacion = false,
            intentoConfirmar = false
        )
    }

    init { Log.d("Pedido VM", "PedidoViewModel creado") }

    override fun onCleared() {
        super.onCleared()
        Log.d("Pedido VM", "PedidoViewModel destruido")
    }
}