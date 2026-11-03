package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cafeteria.R

@Composable
fun BarraPedido(
    total: Double,
    unidades: Int,
    onConfirmar: () -> Unit, modifier: Modifier = Modifier
) {
    BottomAppBar() {
        Row() {
            Text(
                text = stringResource(R.string.unidades_pedido , unidades)
            )
            Text(
                text = stringResource(R.string.total_pedido, total)
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {onConfirmar()} , enabled = unidades > 0, ) {
                Text(text = stringResource(R.string.confirmar))
            }
        }
    }

}