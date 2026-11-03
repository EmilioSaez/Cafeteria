package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.Producto

@Composable
fun ContenidoCarta(
    productos: List<Producto>,
    estado: PedidoUiState,
    errorNombre: Boolean,
    onCantidadChange: (Int, Int) -> Unit,
    onCategoriaChange: (Categoria?) -> Unit,
    onNombreChange: (String) -> Unit,
    onParaLlevarChange: () -> Unit,
    onAvisarChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            CabeceraCarta()
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            FiltroCategorias(
                seleccionada = estado.categoria,
                onSeleccionar = onCategoriaChange
            )
        }

        items(productos, key = { it.id }) { producto ->
            ProductoCard(
                producto = producto,
                cantidad = estado.cantidades[producto.id] ?: 0,
                onCantidadChange = { nueva -> onCantidadChange(producto.id, nueva) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            DatosPedido(
                nombrePedido = estado.nombre,
                onNombreChange = onNombreChange,
                mostrarError = errorNombre,
                esParaLlevar = estado.paraLlevar,
                onValueChangeSwitch = { onParaLlevarChange() },
                avisarListo = estado.avisar,
                onValueChangeCheckbox = { onAvisarChange() }
            )
        }
    }
}