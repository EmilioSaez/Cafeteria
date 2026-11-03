package com.example.cafeteria.ui.theme.carta

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogoConfirmacion(
    nombre: String,
    esParaLlevar: Boolean,
    avisarListo: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val tipoConsumo = if (esParaLlevar) "Para llevar" else "Para tomar aquí"
    val textoAviso = if (avisarListo) "\nTe avisaremos cuando esté listo" else ""

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Enviar el pedido?") },
        text = {
            Text("Resumen del pedido a nombre de $nombre.\n$tipoConsumo$textoAviso")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Enviar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}