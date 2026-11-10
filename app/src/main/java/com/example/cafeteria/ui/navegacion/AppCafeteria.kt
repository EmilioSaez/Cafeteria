package com.example.cafeteria.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cafeteria.ui.theme.carta.PantallaCarta
import com.example.cafeteria.ui.theme.carta.PantallaDetalle
import com.example.cafeteria.ui.theme.carta.PantallaPedidoEnviado
import com.example.cafeteria.ui.theme.carta.PantallaResumen
import com.example.cafeteria.ui.theme.carta.PedidoViewModel

@Composable
fun AppCafeteria() {
    val navController = rememberNavController()
    // UNA sola vez, fuera del NavHost: lo comparten todas las pantallas
    val pedidoViewModel: PedidoViewModel = viewModel()

    NavHost(navController = navController, startDestination = Carta) {
        composable<Carta> {
            PantallaCarta(
                pedidoViewModel = pedidoViewModel,
                onProductoClick = { id -> navController.navigate(Detalle(id)) },
                onVerPedido = {
                    navController.navigate(Resumen) { launchSingleTop = true }
                }
            )
        }
        composable<Detalle> { entrada ->
            val ruta: Detalle = entrada.toRoute()
            PantallaDetalle(
                idProducto = ruta.idProducto,
                pedidoViewModel = pedidoViewModel,
                onVolver = { navController.navigateUp() }
            )
        }
        composable<Resumen> {
            PantallaResumen(
                pedidoViewModel = pedidoViewModel,
                onVolver = { navController.navigateUp() },
                onPedidoEnviado = { nombre ->
                    navController.navigate(PedidoEnviado(nombre)) {
                        popUpTo<Carta>()
                    }
                }
            )
        }
        composable<PedidoEnviado> { entrada ->
            val ruta: PedidoEnviado = entrada.toRoute()
            PantallaPedidoEnviado(
                nombre = ruta.nombre,
                onVolverCarta = { navController.popBackStack() }
            )
        }
    }
}
