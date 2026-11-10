package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cafeteria.R
import com.example.cafeteria.data.Producto
import com.example.cafeteria.objects.DatosCarta
import com.example.cafeteria.ui.theme.CafeteriaTheme

@Composable
fun PantallaDetalle(
    idProducto: Int,
    pedidoViewModel: PedidoViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Si el producto no existe, no se dibuja nada
    val producto = pedidoViewModel.buscarProducto(idProducto) ?: return
    val cantidad = pedidoViewModel.uiState.cantidades[idProducto] ?: 0

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BarraConVolver(
                titulo = stringResource(producto.nombre),
                onVolver = onVolver
            )
        }
    ) { innerPadding ->
        ContenidoDetalle(
            producto = producto,
            cantidad = cantidad,
            onCantidadChange = { nueva -> pedidoViewModel.cambiarCantidad(idProducto, nueva) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContenidoDetalle(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(producto.imagen),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = stringResource(producto.nombre),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(producto.categoria.nombre),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = stringResource(producto.descripcion),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.precio, producto.precio),
            style = MaterialTheme.typography.titleLarge
        )
        SelectorCantidad(cantidad = cantidad, onCantidadChange = onCantidadChange)
    }
}

@Preview(showBackground = true)
@Composable
fun ContenidoDetallePreview() {
    CafeteriaTheme {
        ContenidoDetalle(
            producto = DatosCarta.productos.first(),
            cantidad = 2,
            onCantidadChange = {}
        )
    }
}
