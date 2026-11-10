package com.example.cafeteria.ui.theme.carta

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cafeteria.R

/** Barra inferior genérica: sirve en la carta ("Ver pedido") y en el resumen ("Confirmar"). */
@Composable
fun BarraPedido(
    total: Double,
    unidades: Int,
    @StringRes textoBoton: Int,
    onBotonClick: () -> Unit,
    modifier: Modifier = Modifier,
    botonHabilitado: Boolean = true
) {
    BottomAppBar(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = stringResource(R.string.unidades_pedido, unidades))
                Text(text = stringResource(R.string.total_pedido, total))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onBotonClick, enabled = botonHabilitado) {
                Text(text = stringResource(textoBoton))
            }
        }
    }
}
