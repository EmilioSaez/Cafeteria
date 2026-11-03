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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafeteria.R
import kotlinx.coroutines.launch

@Composable
fun PantallaCarta(
    modifier: Modifier = Modifier,
    pedidoViewModel: PedidoViewModel = viewModel()
) {
    val estado = pedidoViewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val mensajeVaciado = stringResource(R.string.pedido_vaciado)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BarraSuperiorCarta(
                true,
                onVaciarPedido = {
                    pedidoViewModel.vaciarPedido()
                    scope.launch { snackbarHostState.showSnackbar(mensajeVaciado) }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BarraPedido(
                total = pedidoViewModel.total,
                unidades = pedidoViewModel.unidades,
                onConfirmar = { pedidoViewModel.confirmar() }
            )
        }
    ) { innerPadding ->
        ContenidoCarta(
            productos = pedidoViewModel.productosFiltrados,
            estado = estado,
            errorNombre = pedidoViewModel.errorNombre,
            onCantidadChange = pedidoViewModel::cambiarCantidad,
            onCategoriaChange = pedidoViewModel::seleccionarCategoria,
            onNombreChange = pedidoViewModel::cambiarNombre,
            onParaLlevarChange = pedidoViewModel::cambiarParaLlevar,
            onAvisarChange = pedidoViewModel::cambiarAvisar,
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (estado.mostrarConfirmacion) {
        DialogoConfirmacion(
            nombre = estado.nombre,
            esParaLlevar = estado.paraLlevar,
            avisarListo = estado.avisar,
            onDismiss = { pedidoViewModel.cancelarConfirmar() },
            onConfirm = {
                val nombre = estado.nombre
                pedidoViewModel.enviarPedido()
                scope.launch { snackbarHostState.showSnackbar("Pedido de $nombre enviado") }
            }
        )
    }
}