package com.example.cafeteria.objects

import com.example.cafeteria.R
import com.example.cafeteria.data.Categoria
import com.example.cafeteria.data.Producto


object DatosCarta {
    val productos = listOf<Producto>(
        Producto(111,R.string.producto_ZumN,R.string.desc_ZumN,R.drawable.zumo,12.3, Categoria.BOLLERIA),
        Producto(123214,R.string.producto_BocCho,R.string.desc_BocCho,R.drawable.bocadillo_chorizo,12.3, Categoria.COMIDA),
        Producto(5554,R.string.producto_Crua,R.string.desc_Crua,R.drawable.cruasan,2.2 , Categoria.COMIDA),
        Producto(12312,R.string.producto_CfL,R.string.desc_CfL,R.drawable.coffee_and_milk,4.0 , Categoria.BEBIDA)
    )
}