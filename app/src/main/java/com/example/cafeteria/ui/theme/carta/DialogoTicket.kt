package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cafeteria.R
import com.example.cafeteria.data.LineaTicket
import com.example.cafeteria.data.Producto
import com.example.cafeteria.data.Ticket
import com.example.cafeteria.objects.DatosCarta
import com.example.cafeteria.ui.theme.CafeteriaTheme
import java.text.DateFormat
import java.util.Date

@Composable
fun DialogoTicket(
    ticket: Ticket,
    pedidosCliente: Int?,
    buscarProducto: (Int) -> Producto?,
    onCerrar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fecha = DateFormat
        .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        .format(Date(ticket.fechaHora))

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCerrar,
        title = { Text(stringResource(R.string.titulo_ticket)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(fecha, style = MaterialTheme.typography.bodySmall)
                Text(
                    "${ticket.nombre} · " + stringResource(
                        if (ticket.paraLlevar) R.string.ticket_para_llevar
                        else R.string.ticket_para_tomar
                    )
                )
                HorizontalDivider()
                ticket.lineas.forEach { linea ->
                    val producto = buscarProducto(linea.idProducto)
                    val nombreProducto = if (producto != null) {
                        stringResource(producto.nombre)
                    } else {
                        stringResource(R.string.producto_desconocido, linea.idProducto)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(stringResource(R.string.linea_pedido, linea.cantidad, nombreProducto))
                        Text(stringResource(R.string.precio, linea.cantidad * linea.precioUnitario))
                    }
                }
                HorizontalDivider()
                Text(
                    stringResource(R.string.total_pedido, ticket.total),
                    fontWeight = FontWeight.Bold
                )
                if (pedidosCliente != null) {
                    Text(stringResource(R.string.pedidos_cliente, ticket.nombre, pedidosCliente))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onCerrar) {
                Text(stringResource(R.string.cerrar))
            }
        }
    )
}

@Preview
@Composable
fun DialogoTicketPreview() {
    val ticket = Ticket(
        nombre = "Ana",
        paraLlevar = true,
        fechaHora = 1761742800000,
        lineas = listOf(
            LineaTicket(idProducto = 12312, cantidad = 2, precioUnitario = 4.0),
            LineaTicket(idProducto = 5554, cantidad = 1, precioUnitario = 2.2),
            LineaTicket(idProducto = 999, cantidad = 1, precioUnitario = 1.5)
        ),
        total = 11.7
    )
    CafeteriaTheme {
        DialogoTicket(
            ticket = ticket,
            pedidosCliente = 3,
            buscarProducto = { id -> DatosCarta.productos.find { it.id == id } },
            onCerrar = {}
        )
    }
}