package com.example.cafeteria.ui.theme.carta

import android.content.res.Resources
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cafeteria.R
import com.example.cafeteria.data.Producto
import com.example.cafeteria.ui.theme.CafeteriaTheme

@Composable
fun ProductoCard(
    modifier: Modifier = Modifier,
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = Modifier
            .background(
                Color.LightGray
            )
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(producto.imagen),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(producto.descripcion)
        )
        Column() {
            Text(
                text = stringResource(producto.nombre),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(producto.descripcion),
                style = MaterialTheme.typography.bodySmall
            )
        }


        Text(
            text = stringResource(R.string.precio, producto.precio),


            )
    }
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primaryContainer
            )
            .fillMaxWidth()
    ) {


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
