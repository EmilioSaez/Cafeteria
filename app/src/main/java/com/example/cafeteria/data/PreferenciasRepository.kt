package com.example.cafeteria.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencias")

data class PreferenciasCliente(
    val nombre: String = "",
    val paraLlevar: Boolean = false)

class PreferenciasRepository(private val dataStore: DataStore<Preferences>) {
    private object Claves {
        val NOMBRE = stringPreferencesKey("nombre_cliente")
        val PARA_LLEVAR = booleanPreferencesKey("para_llevar")
    }
    val preferencias: Flow<PreferenciasCliente> = dataStore.data.map { prefs ->
        PreferenciasCliente(
            nombre = prefs[Claves.NOMBRE] ?: "",
            paraLlevar = prefs[Claves.PARA_LLEVAR] ?: false
        )
    }
    suspend fun guardar(nombre: String, paraLlevar: Boolean) {
        dataStore.edit { prefs ->
            prefs[Claves.NOMBRE] = nombre
            prefs[Claves.PARA_LLEVAR] = paraLlevar
        }
    }
    suspend fun borrar() {
        dataStore.edit { prefs -> prefs.clear() }
    }
}