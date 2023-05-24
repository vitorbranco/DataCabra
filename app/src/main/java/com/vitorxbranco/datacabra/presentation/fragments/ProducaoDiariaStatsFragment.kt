package com.vitorxbranco.datacabra.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.BarEntry
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.ControleLeiteiro
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class ProducaoDiariaStatsFragment : Fragment() {

    private lateinit var tvTesteProducaoDiaria: TextView

    private val producaoDiariaViewModel : ProducaoDiariaGraphicsViewModel by lazy {
        ViewModelProvider(requireActivity())[ProducaoDiariaGraphicsViewModel::class.java]
    }
    private val barEntryLiveData: MutableLiveData<List<BarEntry>> by lazy {
        producaoDiariaViewModel.barEntryLiveData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_producao_diaria_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTesteProducaoDiaria = view.findViewById(R.id.tv_teste_producao_diaria)
        producaoDiariaViewModel.updateBarEntryLiveData()

        barEntryLiveData.observe(viewLifecycleOwner) { controleLeiteiroList ->
            calcStatsAndUpdateText(controleLeiteiroList)
        }

    }

    fun calcStatsAndUpdateText(barEntryList: List<BarEntry>) {
        if (barEntryList.isNotEmpty()) {
            var maxPrimeiraOrdenha: Float = barEntryList[0].x
            var minPrimeiraOrdenha: Float = barEntryList[0].x
            var maxTotalLitrosDia: Float = barEntryList[0].y.toFloat()
            var minTotalLitrosDia: Float = barEntryList[0].y

            for (barEntry in barEntryList) {
                if (barEntry.x > maxPrimeiraOrdenha) {
                    maxPrimeiraOrdenha = barEntry.x
                }
                if (barEntry.x < minPrimeiraOrdenha) {
                    minPrimeiraOrdenha = barEntry.x
                }
                if (barEntry.y > maxTotalLitrosDia) {
                    maxTotalLitrosDia = barEntry.y
                }
                if (barEntry.y < minTotalLitrosDia) {
                    minTotalLitrosDia = barEntry.y
                }
            }

            val texto = "Máximo 1ªOrdenha: $maxPrimeiraOrdenha\nMínimo 1ªOrdenha: $minPrimeiraOrdenha\nMáximo Total de Litros por Dia: $maxTotalLitrosDia\nMínimo Total de Litros por Dia: $minTotalLitrosDia"
            tvTesteProducaoDiaria.text = texto

        } else {
            println("A Lista 'Producao Diaria' está vazia.")
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProducaoDiariaStatsFragment()
    }
}