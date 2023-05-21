package com.vitorxbranco.datacabra.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.presentation.fragments.ControleLeiteiroGraphicsFragment
import com.vitorxbranco.datacabra.presentation.fragments.ProducaoDiariaGraphicsFragment
import com.vitorxbranco.datacabra.presentation.viewmodels.ControleLeiteiroGraphicsViewModel
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel

class GraphicsActivity : AppCompatActivity() {

    private lateinit var producaoDiariaViewModel: ProducaoDiariaGraphicsViewModel
    private lateinit var controleLeiteiroViewModel: ControleLeiteiroGraphicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphics)

        producaoDiariaViewModel = ViewModelProvider(this, ProducaoDiariaGraphicsViewModel.getVMFactory(application))[ProducaoDiariaGraphicsViewModel::class.java]
        controleLeiteiroViewModel = ViewModelProvider(this, ControleLeiteiroGraphicsViewModel.getVMFactory(application))[ControleLeiteiroGraphicsViewModel::class.java]

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view_graphics)

        val producaoDiariaGraphicsFragment = ProducaoDiariaGraphicsFragment.newInstance()
        val controleLeiteiroGraphicsFragment = ControleLeiteiroGraphicsFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, producaoDiariaGraphicsFragment)
            setReorderingAllowed(true)
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragment = when(menuItem.itemId){
                R.id.item_producao_diaria -> producaoDiariaGraphicsFragment
                R.id.item_controle_leiteiro -> controleLeiteiroGraphicsFragment
                else -> producaoDiariaGraphicsFragment
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)
            }
            true
        }
    }
}