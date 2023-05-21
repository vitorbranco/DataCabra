package com.vitorxbranco.datacabra.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class ProducaoDiariaGraphicsFragment : Fragment() {

    private val producaoDiariaViewModel: ProducaoDiariaGraphicsViewModel by lazy {
        ViewModelProvider(requireActivity())[ProducaoDiariaGraphicsViewModel::class.java]
    }
    private val producaoDiariaLiveData: LiveData<List<ProducaoDiaria>> by lazy {
        producaoDiariaViewModel.producaoDiariaLiveData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_producao_diaria_graphics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val combinedChartProducao = view.findViewById<CombinedChart>(R.id.combinedChart_producao)

        producaoDiariaLiveData.observe(viewLifecycleOwner) { producaoDiariaList ->
            val entriesLine = mutableListOf<Entry>()
            val entriesBar = mutableListOf<BarEntry>()
            val labels = mutableListOf<String>()

            for ((index, producaoDiaria) in producaoDiariaList.withIndex()) {
                val data = producaoDiaria.data
                val totalLitrosDia = producaoDiaria.totalLitrosDia.toFloat()
                val primeiraOrdenha = producaoDiaria.primeiraOrdenha.toFloat()

                val entryLine = Entry(index.toFloat(), primeiraOrdenha)
                entriesLine.add(entryLine)

                val entryBar = BarEntry(index.toFloat(), totalLitrosDia)
                entriesBar.add(entryBar)

                labels.add(data)

            }

            val dataSetLine = LineDataSet(entriesLine, "Primeira Ordenha")
            val dataSetBar = BarDataSet(entriesBar, "Total de litros por dia")

            val data = CombinedData()
            data.setData(LineData(dataSetLine))
            data.setData(BarData(dataSetBar))

            combinedChartProducao.data = data

            // Configuração do estilo do gráfico de linhas
            dataSetLine.color = Color.RED
            dataSetLine.valueTextColor = Color.BLACK

            // Configuração do estilo do gráfico de barras
            dataSetBar.color = Color.GREEN
            dataSetBar.valueTextColor = Color.BLACK

            //Define o eixo X
            val xAxis = combinedChartProducao.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            // Exibe o gráfico
            combinedChartProducao.invalidate()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProducaoDiariaGraphicsFragment()
    }
}