package com.example.cafeteria.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Producto(
    val id : Int,
    @StringRes val nombre : Int,
    @StringRes val descripcion: Int,
    @DrawableRes val imagen: Int,
    val precio : Double,
    val categoria: Categoria

)
