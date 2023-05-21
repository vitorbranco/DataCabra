package com.vitorxbranco.datacabra.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitorxbranco.datacabra.R

/**
 * A simple [Fragment] subclass.
 * Use the [ControleLeiteiroGraphicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControleLeiteiroGraphicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controle_leiteiro_graphics, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ControleLeiteiroGraphicsFragment.
         */
        @JvmStatic
        fun newInstance() =
            ControleLeiteiroGraphicsFragment()
    }
}