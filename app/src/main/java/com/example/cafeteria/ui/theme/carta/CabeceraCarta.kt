package com.example.cafeteria.ui.theme.carta

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cafeteria.R


@Composable
fun CabeceraCarta(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium
        )
        Row(horizontalArrangement = Arrangement.Center) {
            Box() {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "fondo"
                )

                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo"
                )
            }



        }
        Text(
            text = stringResource(R.string.subtitulo_carta , 12 , 4)
        )

    }
}

@Preview (locale = "es")
@Composable
fun PreviewEsCabezeraCarta() {
    CabeceraCarta()
}

@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkCabezeraCarta() {
    CabeceraCarta()
}

@Preview (locale = "en")
@Composable
fun PreviewEnCabezeraCarta() {
    CabeceraCarta()
}