package com.vitorxbranco.datacabra.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.ControleLeiteiro
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class ProducaoDiariaStatsFragment : Fragment() {

    private lateinit var tvTesteProducaoDiaria: TextView

    private val producaoDiariaViewModel : ProducaoDiariaGraphicsViewModel by lazy {
        ViewModelProvider(requireActivity())[ProducaoDiariaGraphicsViewModel::class.java]
    }
    private val producaoDiariaLiveData: LiveData<List<ProducaoDiaria>> by lazy {
        producaoDiariaViewModel.producaoDiariaLiveData
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

        producaoDiariaLiveData.observe(viewLifecycleOwner) { controleLeiteiroList ->
            calcStatsAndUpdateText(controleLeiteiroList)
        }

    }

    fun calcStatsAndUpdateText(producaoDiariaList: List<ProducaoDiaria>) {
        if (producaoDiariaList.isNotEmpty()) {
            var maxPrimeiraOrdenha: Float = producaoDiariaList[0].primeiraOrdenha.toFloat()
            var minPrimeiraOrdenha: Float = producaoDiariaList[0].primeiraOrdenha.toFloat()
            var maxSegundaOrdenha: Float = producaoDiariaList[0].segundaOrdenha.toFloat()
            var minSegundaOrdenha: Float = producaoDiariaList[0].segundaOrdenha.toFloat()

            for (producaoDiaria in producaoDiariaList) {
                if (producaoDiaria.primeiraOrdenha.toFloat() > maxPrimeiraOrdenha) {
                    maxPrimeiraOrdenha = producaoDiaria.primeiraOrdenha.toFloat()
                }
                if (producaoDiaria.primeiraOrdenha.toFloat() < minPrimeiraOrdenha) {
                    minPrimeiraOrdenha = producaoDiaria.primeiraOrdenha.toFloat()
                }
                if (producaoDiaria.segundaOrdenha.toFloat() > maxSegundaOrdenha) {
                    maxSegundaOrdenha = producaoDiaria.segundaOrdenha.toFloat()
                }
                if (producaoDiaria.segundaOrdenha.toFloat() < minSegundaOrdenha) {
                    minSegundaOrdenha = producaoDiaria.segundaOrdenha.toFloat()
                }
            }

            val texto = "Máximo 1ªOrdenha: $maxPrimeiraOrdenha\nMínimo 1ªOrdenha: $minPrimeiraOrdenha\nMáximo 2ªOrdenha: $maxSegundaOrdenha\nMínimo 2ªOrdenha: $minSegundaOrdenha"
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