package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.Producto
import com.example.cafeteria.objects.DatosCarta
import com.example.cafeteria.ui.theme.CafeteriaTheme

@Composable
fun ContenidoCarta(
    productos: List<Producto>,
    cantidades: Map<Int, Int>,
    categoriaSeleccionada: Categoria?,
    onCantidadChange: (Int, Int) -> Unit,
    onCategoriaChange: (Categoria?) -> Unit,
    onProductoClick: (Int) -> Unit,
    modifier: Modifier = Modifier
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
                seleccionada = categoriaSeleccionada,
                onSeleccionar = onCategoriaChange
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(productos, key = { it.id }) { producto ->
            ProductoCard(
                producto = producto,
                cantidad = cantidades[producto.id] ?: 0,
                onCantidadChange = { nueva -> onCantidadChange(producto.id, nueva) },
                onClick = { onProductoClick(producto.id) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContenidoCartaPreview() {
    CafeteriaTheme {
        ContenidoCarta(
            productos = DatosCarta.productos,
            cantidades = mapOf(DatosCarta.productos.first().id to 2),
            categoriaSeleccionada = null,
            onCantidadChange = { _, _ -> },
            onCategoriaChange = {},
            onProductoClick = {}
        )
    }
}
