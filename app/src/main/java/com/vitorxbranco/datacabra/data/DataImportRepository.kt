package com.vitorxbranco.datacabra.data

import android.content.Context
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

class DataImportRepository(
    private val context: Context,
    private val producaoDiariaDao: ProducaoDiariaDao,
    private val controleLeiteiroDao: ControleLeiteiroDao
) {

    fun importDataFromProducaoCSV() {
        val bufferReader = context.assets.open("producao_diaria_tabela.csv").reader()
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader()
        )
        val listProducao = mutableListOf<ProducaoDiaria>()
        csvParser.forEach {
            it?.let {
                val producaoDiaria = ProducaoDiaria(
                    id = it.get(0),
                    totalDeAnimais = it.get(1),
                    primeiraOrdenha = it.get(2),
                    segundaOrdenha = it.get(3),
                    totalLitrosDia = it.get(4),
                    media = it.get(5),
                    data = it.get(6)
                )
                listProducao.add(producaoDiaria)
            }

            producaoDiariaDao.insertAll(listProducao)
        }
    }

    fun importDataFromControleCSV() {
        val bufferReader = context.assets.open("controle_leiteiro_tabela.csv").reader()
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader()
        )
        val listControle = mutableListOf<ControleLeiteiro>()
        csvParser.forEach {
            it?.let {
                val controleLeiteiro = ControleLeiteiro(
                    id = it.get(0),
                    microchip = it.get(1),
                    numeroDoAnimal = it.get(2),
                    nome = it.get(3),
                    dataDoParto = it.get(4),
                    baia = it.get(5),
                    primeiraOrdenha = it.get(6),
                    segundaOrdenha = it.get(7),
                    total = it.get(8),
                    dataDoControle = it.get(9),
                    del = it.get(10)
                )
                listControle.add(controleLeiteiro)
            }

            controleLeiteiroDao.insertAll(listControle)
        }
    }

}