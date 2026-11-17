package com.example.cafeteria.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

private const val TAG = "TicketRepo"

class TicketRepository(directorio: File) {

    private val fichero = File(directorio, "ultimo_ticket.json")

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    suspend fun guardar(ticket: Ticket) {
        withContext(Dispatchers.IO) {
            fichero.writeText(json.encodeToString(ticket))
        }
    }

    suspend fun cargar(): Ticket? = withContext(Dispatchers.IO) {
        if (!fichero.exists()) return@withContext null
        try {
            json.decodeFromString<Ticket>(fichero.readText())
        } catch (e: SerializationException) {
            Log.w(TAG, "El ticket guardado está dañado, se ignora", e)
            null
        } catch (e: IOException) {
            Log.w(TAG, "No se ha podido leer el ticket", e)
            null
        }
    }

    suspend fun borrar() {
        withContext(Dispatchers.IO) {
            fichero.delete()
        }
    }
}