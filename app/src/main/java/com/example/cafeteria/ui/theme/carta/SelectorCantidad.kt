package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cafeteria.R

@Composable
fun SelectorCantidad(
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { onCantidadChange(cantidad - 1) }, enabled = cantidad > 0) {
            Icon(
                painter = painterResource(R.drawable.remove_image),
                contentDescription = stringResource(R.string.delete_one)
            )
        }
        Text(cantidad.toString())
        IconButton(onClick = { onCantidadChange(cantidad + 1) }) {
            Icon(
                painter = painterResource(R.drawable.add_image),
                contentDescription = stringResource(R.string.add_one)
            )
        }
    }
}
