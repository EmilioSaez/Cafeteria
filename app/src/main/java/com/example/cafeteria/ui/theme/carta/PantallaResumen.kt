package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cafeteria.R
import com.example.cafeteria.objects.DatosCarta
import com.example.cafeteria.ui.theme.CafeteriaTheme

@Composable
fun PantallaResumen(
    pedidoViewModel: PedidoViewModel,
    onVolver: () -> Unit,
    onPedidoEnviado: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val estado = pedidoViewModel.uiState
    val lineas = pedidoViewModel.lineasPedido

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BarraConVolver(
                titulo = stringResource(R.string.titulo_resumen),
                onVolver = onVolver
            )
        },
        bottomBar = {
            BarraPedido(
                total = pedidoViewModel.total,
                unidades = pedidoViewModel.unidades,
                textoBoton = R.string.confirmar,
                onBotonClick = pedidoViewModel::confirmar,
                botonHabilitado = lineas.isNotEmpty()
            )
        }
    ) { innerPadding ->
        ContenidoResumen(
            lineas = lineas,
            nombre = estado.nombre,
            errorNombre = pedidoViewModel.errorNombre,
            paraLlevar = estado.paraLlevar,
            avisar = estado.avisar,
            onNombreChange = pedidoViewModel::cambiarNombre,
            onParaLlevarChange = { pedidoViewModel.cambiarParaLlevar() },
            onAvisarChange = { pedidoViewModel.cambiarAvisar() },
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (estado.mostrarConfirmacion) {
        DialogoConfirmacion(
            nombre = estado.nombre,
            esParaLlevar = estado.paraLlevar,
            avisarListo = estado.avisar,
            onDismiss = pedidoViewModel::cancelarConfirmar,
            onConfirm = {
                val nombre = estado.nombre
                pedidoViewModel.enviarPedido()
                onPedidoEnviado(nombre)
            }
        )
    }
}

@Composable
fun ContenidoResumen(
    lineas: List<LineaPedido>,
    nombre: String,
    errorNombre: Boolean,
    paraLlevar: Boolean,
    avisar: Boolean,
    onNombreChange: (String) -> Unit,
    onParaLlevarChange: () -> Unit,
    onAvisarChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        if (lineas.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.pedido_vacio),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        } else {
            items(lineas, key = { it.producto.id }) { linea ->
                FilaPedido(linea)
                HorizontalDivider()
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            DatosPedido(
                nombrePedido = nombre,
                onNombreChange = onNombreChange,
                mostrarError = errorNombre,
                esParaLlevar = paraLlevar,
                onValueChangeSwitch = { onParaLlevarChange() },
                avisarListo = avisar,
                onValueChangeCheckbox = { onAvisarChange() }
            )
        }
    }
}

@Composable
fun FilaPedido(linea: LineaPedido, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = stringResource(
                R.string.linea_pedido,
                linea.cantidad,
                stringResource(linea.producto.nombre)
            ),
            modifier = Modifier.weight(1f)
        )
        Text(text = stringResource(R.string.precio, linea.cantidad * linea.producto.precio))
    }
}

@Preview(showBackground = true)
@Composable
fun ContenidoResumenPreview() {
    CafeteriaTheme {
        ContenidoResumen(
            lineas = listOf(
                LineaPedido(DatosCarta.productos[0], 2),
                LineaPedido(DatosCarta.productos[2], 1)
            ),
            nombre = "Ana",
            errorNombre = false,
            paraLlevar = true,
            avisar = true,
            onNombreChange = {},
            onParaLlevarChange = {},
            onAvisarChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContenidoResumenVacioPreview() {
    CafeteriaTheme {
        ContenidoResumen(
            lineas = emptyList(),
            nombre = "",
            errorNombre = false,
            paraLlevar = false,
            avisar = true,
            onNombreChange = {},
            onParaLlevarChange = {},
            onAvisarChange = {}
        )
    }
}
