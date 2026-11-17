package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cafeteria.R
import kotlinx.coroutines.launch

@Composable
fun PantallaCarta(
    pedidoViewModel: PedidoViewModel,
    onProductoClick: (Int) -> Unit,
    onVerPedido: () -> Unit,
    modifier: Modifier = Modifier
) {
    val estado = pedidoViewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val mensajeVaciado = stringResource(R.string.pedido_vaciado)
    val datosOlvidados = stringResource(R.string.datos_olvidados)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BarraSuperiorCarta(
                hayPedido = pedidoViewModel.unidades > 0,
                hayTicket = estado.ultimoTicket != null,
                onVaciarPedido = {
                    pedidoViewModel.vaciarPedido()
                    scope.launch { snackbarHostState.showSnackbar(mensajeVaciado) }
                },
                onVerTicket = pedidoViewModel::verTicket,
                onOlvidarDatos = {
                    pedidoViewModel.olvidarDatos()
                    scope.launch { snackbarHostState.showSnackbar(datosOlvidados) }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BarraPedido(
                total = pedidoViewModel.total,
                unidades = pedidoViewModel.unidades,
                textoBoton = R.string.ver_pedido,
                onBotonClick = onVerPedido
            )
        }
    ) { innerPadding ->
        ContenidoCarta(
            productos = pedidoViewModel.productosFiltrados,
            cantidades = estado.cantidades,
            categoriaSeleccionada = estado.categoria,
            onCantidadChange = pedidoViewModel::cambiarCantidad,
            onCategoriaChange = pedidoViewModel::seleccionarCategoria,
            onProductoClick = onProductoClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
    val ticket = estado.ultimoTicket
    if (estado.mostrarTicket && ticket != null) {
        DialogoTicket(
            ticket = ticket,
            buscarProducto = pedidoViewModel::buscarProducto,
            onCerrar = pedidoViewModel::cerrarTicket,
            pedidosCliente = 2 ,
        )
    }
}
