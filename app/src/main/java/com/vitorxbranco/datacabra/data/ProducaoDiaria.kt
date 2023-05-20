package com.vitorxbranco.datacabra.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producao_diaria_tabela")
data class ProducaoDiaria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id2: String,
    val totalDeAnimais: Int,
    val primeiraOrdenha: Float,
    val segundaOrdenha: Float,
    val totalLitrosDia: Float,
    val media: Float,
    val data: String
)