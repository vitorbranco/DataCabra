package com.vitorxbranco.datacabra.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitorxbranco.datacabra.R

class GraphicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphics)

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