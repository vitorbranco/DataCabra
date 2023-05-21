package com.vitorxbranco.datacabra.presentation.fragments

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

        val tvProducaoDiaria = view.findViewById<TextView>(R.id.tvProducaoDiaria)

        producaoDiariaLiveData.observe(viewLifecycleOwner) { producaoDiariaList ->
            val stringBuilder = StringBuilder()
            for (producaoDiaria in producaoDiariaList) {
                stringBuilder.append(producaoDiaria.toString())
                stringBuilder.append("\n")
            }
            tvProducaoDiaria.text = stringBuilder.toString()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProducaoDiariaGraphicsFragment()
    }
}