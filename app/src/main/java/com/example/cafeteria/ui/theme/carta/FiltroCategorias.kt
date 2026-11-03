package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.R

@Composable
fun FiltroCategorias(
    seleccionada: Categoria?, onSeleccionar: (Categoria?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            FilterChip(
                selected = seleccionada == null,
                onClick = { onSeleccionar(null) },
                label = { Text(stringResource(R.string.categoria_todas)) }
            )
        }
        items(Categoria.entries) { categoria ->
            FilterChip(
                selected = seleccionada == categoria,
                onClick = { onSeleccionar(categoria) },
                label = { Text(stringResource(categoria.nombre)) }
            )
        }
    }

}