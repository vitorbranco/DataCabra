package com.vitorxbranco.datacabra.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.presentation.fragments.ControleLeiteiroStatsFragment
import com.vitorxbranco.datacabra.presentation.fragments.ProducaoDiariaStatsFragment

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view_stats)

        val producaoDiariaStatsFragment = ProducaoDiariaStatsFragment.newInstance()
        val controleLeiteiroStatsFragment = ControleLeiteiroStatsFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, producaoDiariaStatsFragment)
            setReorderingAllowed(true)
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragment = when(menuItem.itemId){
                R.id.item_producao_diaria -> producaoDiariaStatsFragment
                R.id.item_controle_leiteiro -> controleLeiteiroStatsFragment
                else -> producaoDiariaStatsFragment
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)
            }
            true
        }
    }
}