package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cafeteria.R
import com.example.cafeteria.data.Producto

@Composable
fun ProductoCard(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(producto.imagen),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(producto.descripcion)
            )
            Column {
                Text(
                    text = stringResource(producto.nombre),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(producto.descripcion),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(text = stringResource(R.string.precio, producto.precio))
        }
        // Selector de cantidad alineado a la derecha
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
        ) {
            SelectorCantidad(cantidad = cantidad, onCantidadChange = onCantidadChange)
        }
    }
}
