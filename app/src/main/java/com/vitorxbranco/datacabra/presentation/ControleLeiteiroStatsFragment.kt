package com.vitorxbranco.datacabra.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitorxbranco.datacabra.R

/**
 * A simple [Fragment] subclass.
 * Use the [ControleLeiteiroStatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControleLeiteiroStatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controle_leiteiro_stats, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ControleLeiteiroStatsFragment.
         */
        @JvmStatic
        fun newInstance() =
            ControleLeiteiroStatsFragment()
    }
}