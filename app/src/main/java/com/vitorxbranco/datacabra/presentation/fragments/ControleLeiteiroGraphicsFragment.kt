package com.vitorxbranco.datacabra.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.ControleLeiteiro
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.presentation.viewmodels.ControleLeiteiroGraphicsViewModel
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class ControleLeiteiroGraphicsFragment : Fragment() {

    private val controleLeiteiroViewModel : ControleLeiteiroGraphicsViewModel by lazy {
        ViewModelProvider(requireActivity())[ControleLeiteiroGraphicsViewModel::class.java]
    }
    private val controleLeiteiroLiveData: LiveData<List<ControleLeiteiro>> by lazy {
        controleLeiteiroViewModel.controleLeiteiroLiveData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_controle_leiteiro_graphics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lineChartControle = view.findViewById<LineChart>(R.id.lineChart_controle)

        controleLeiteiroLiveData.observe(viewLifecycleOwner) { controleLeiteiroList ->

            val entries = mutableListOf<Entry>()
            val labels = mutableListOf<String>()

            for ((index, controleLeiteiro) in controleLeiteiroList.withIndex()) {
                val nomeDoAnimal = controleLeiteiro.nome
                val del = controleLeiteiro.del.toFloat()

                val entry = Entry(index.toFloat(), del)
                entries.add(entry)
                labels.add(nomeDoAnimal)
            }

            val dataSet = LineDataSet(entries, "Controle Leiteiro")
            val lineData = LineData(dataSet)
            lineChartControle.data = lineData

            // Configuração do estilo do gráfico
            dataSet.color = Color.BLUE
            dataSet.valueTextColor = Color.BLACK

            val xAxis = lineChartControle.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            // Exibe o gráfico
            lineChartControle.invalidate()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ControleLeiteiroGraphicsFragment()
    }
}