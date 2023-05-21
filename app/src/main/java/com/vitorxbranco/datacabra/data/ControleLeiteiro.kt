package com.vitorxbranco.datacabra.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ControleLeiteiro(
    @PrimaryKey(autoGenerate = false) val id: String,
    val microchip: String,
    val numeroDoAnimal: String,
    val nome: String,
    val dataDoParto: String,
    val baia: String,
    val primeiraOrdenha: String,
    val segundaOrdenha: String,
    val total: String,
    val dataDoControle: String,
    val del: String,
)