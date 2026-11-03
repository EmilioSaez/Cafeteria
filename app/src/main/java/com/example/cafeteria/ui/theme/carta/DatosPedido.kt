package com.example.cafeteria.ui.theme.carta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.cafeteria.R

@Composable
fun DatosPedido(
    nombrePedido: String,
    onNombreChange: (String) -> Unit,
    mostrarError: Boolean,
    esParaLlevar: Boolean = true,
    onValueChangeSwitch: (Boolean) -> Unit,
    avisarListo: Boolean = true,
    onValueChangeCheckbox: (Boolean) -> Unit
) {
    Column {
        Card {
            OutlinedTextField(
                value = nombrePedido,
                onValueChange = onNombreChange,
                label = { Text(stringResource(R.string.nombre_pedido)) },
                isError = mostrarError,
                supportingText = { if (mostrarError) {
                    Text(stringResource(R.string.error_nombre))
                } },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
        }
        Row {
            Text(text = stringResource(R.string.para_llevar))
            Switch(
                checked = esParaLlevar,
                onCheckedChange = onValueChangeSwitch
            )
        }
        Row {
            Text(text = stringResource(R.string.avisar_listo))
            Checkbox(
                checked = avisarListo,

                onCheckedChange = onValueChangeCheckbox
            )
        }
    }
}