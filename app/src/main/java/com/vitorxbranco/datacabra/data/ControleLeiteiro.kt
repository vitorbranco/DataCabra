package com.vitorxbranco.datacabra.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "controle_leiteiro_tabela")
data class ControleLeiteiro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id2: String,
    val microchip: Long,
    val numeroDoAnimal: Int,
    val nome: String,
    val dataDoParto: String,
    val baia: Int,
    val primeiraOrdenha: Float,
    val segundaOrdenha: Float,
    val total: Float,
    val dataDoControle: String,
    val del: Int,
    val obs: String
)