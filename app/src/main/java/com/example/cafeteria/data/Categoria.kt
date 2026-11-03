package com.example.cafeteria.data

import androidx.annotation.StringRes
import com.example.cafeteria.R

enum class Categoria(@StringRes val nombre: Int) {
    BEBIDA(R.string.categoria_bebida),
    COMIDA(R.string.categoria_comida),
    BOLLERIA(R.string.categoria_bolleria)
}
