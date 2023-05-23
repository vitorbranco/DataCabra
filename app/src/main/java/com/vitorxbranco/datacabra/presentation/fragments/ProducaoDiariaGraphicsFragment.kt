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
import com.github.mikephil.charting.charts.BarChart
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

    private val barEntryLiveData: LiveData<List<BarEntry>> by lazy {
        producaoDiariaViewModel.barEntryLiveData
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

        val barChartProducao = view.findViewById<BarChart>(R.id.barChart_producao)

        barEntryLiveData.observe(viewLifecycleOwner) { barEntryList ->
            val entriesLine = mutableListOf<Entry>()
            val entriesBar = barEntryList.toMutableList()
            val labels = mutableListOf<String>()

            for ((index, barEntry) in barEntryList.withIndex()) {
                val data = barEntry.data as? ProducaoDiaria
                if (data != null){
                    val primeiraOrdenha = barEntry.y

                    val entryLine = Entry(index.toFloat(), primeiraOrdenha)
                    entriesLine.add(entryLine)

                    labels.add(data.data)
                }
            }

            val dataSetBar = BarDataSet(entriesBar, "Total de litros por dia")

            dataSetBar.color = Color.GREEN
            dataSetBar.valueTextColor = Color.BLACK

            val data = BarData(dataSetBar)

            barChartProducao.data = data
            barChartProducao.invalidate()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProducaoDiariaGraphicsFragment()
    }
}