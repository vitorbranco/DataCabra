package com.vitorxbranco.datacabra.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProducaoDiaria(
    @PrimaryKey(autoGenerate = false) val id: String,
    val totalDeAnimais: String,
    val primeiraOrdenha: String,
    val segundaOrdenha: String,
    val totalLitrosDia: String,
    val media: String,
    val data: String
)