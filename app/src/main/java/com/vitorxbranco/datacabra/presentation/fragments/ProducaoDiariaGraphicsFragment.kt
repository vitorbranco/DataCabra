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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class ProducaoDiariaGraphicsFragment : Fragment() {

    private val producaoDiariaViewModel : ProducaoDiariaGraphicsViewModel by lazy {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_producao_diaria_graphics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lineChartProducao = view.findViewById<LineChart>(R.id.lineChart_producao)

        producaoDiariaLiveData.observe(viewLifecycleOwner) { producaoDiariaList ->

            val entries = mutableListOf<Entry>()
            val labels = mutableListOf<String>()

            for ((index, producaoDiaria) in producaoDiariaList.withIndex()) {
                val data = producaoDiaria.data
                val totalLitrosDia = producaoDiaria.totalLitrosDia.toFloat()

                val entry = Entry(index.toFloat(), totalLitrosDia)
                entries.add(entry)
                labels.add(data)
            }

            val dataSet = LineDataSet(entries, "Produção Diária")
            val lineData = LineData(dataSet)
            lineChartProducao.data = lineData

            // Configuração do estilo do gráfico
            dataSet.color = Color.RED
            dataSet.valueTextColor = Color.BLACK

            val xAxis = lineChartProducao.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            // Exibe o gráfico
            lineChartProducao.invalidate()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProducaoDiariaGraphicsFragment()
    }
}