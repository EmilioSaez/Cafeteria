package com.example.cafeteria.ui.theme.carta

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cafeteria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraConVolver(
    titulo: String,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(titulo) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onVolver) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.volver)
                )
            }
        }
    )
}
