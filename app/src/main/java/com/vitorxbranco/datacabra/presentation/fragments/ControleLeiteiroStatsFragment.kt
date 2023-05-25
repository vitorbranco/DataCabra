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
import com.vitorxbranco.datacabra.presentation.viewmodels.ControleLeiteiroGraphicsViewModel

class ControleLeiteiroStatsFragment : Fragment() {

    private lateinit var tvTesteControleLeiteiro: TextView

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
        return inflater.inflate(R.layout.fragment_controle_leiteiro_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTesteControleLeiteiro = view.findViewById(R.id.tv_teste_controle_leiteiro)

        controleLeiteiroLiveData.observe(viewLifecycleOwner) { controleLeiteiroList ->
            calcStatsAndUpdateText(controleLeiteiroList)
        }

    }

    fun calcStatsAndUpdateText(controleLeiteiroList: List<ControleLeiteiro>) {
        if (controleLeiteiroList.isNotEmpty()) {
            var maxTotal: Float = controleLeiteiroList[0].total.toFloat()
            var minTotal: Float = controleLeiteiroList[0].total.toFloat()
            var maxDel: Float = controleLeiteiroList[0].del.toFloat()
            var minDel: Float = controleLeiteiroList[0].del.toFloat()

            for (controleLeiteiro in controleLeiteiroList) {
                if (controleLeiteiro.total.toFloat() > maxTotal) {
                    maxTotal = controleLeiteiro.total.toFloat()
                }
                if (controleLeiteiro.total.toFloat() < minTotal) {
                    minTotal = controleLeiteiro.total.toFloat()
                }
                if (controleLeiteiro.del.toFloat() > maxDel) {
                    maxDel = controleLeiteiro.del.toFloat()
                }
                if (controleLeiteiro.del.toFloat() < minDel) {
                    minDel = controleLeiteiro.del.toFloat()
                }
            }

            val texto = "Máximo Total: $maxTotal\n \nMínimo Total: $minTotal\n \nMáximo DEL: $maxDel\n \nMínimo DEL: $minDel"
            tvTesteControleLeiteiro.text = texto

        } else {
            println("A Lista 'Controle Leiteiro' está vazia.")
        }
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            ControleLeiteiroStatsFragment()
    }
}