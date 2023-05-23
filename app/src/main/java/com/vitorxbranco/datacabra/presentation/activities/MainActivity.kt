package com.vitorxbranco.datacabra.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.vitorxbranco.datacabra.DataCabraApplication
import com.vitorxbranco.datacabra.R
import com.vitorxbranco.datacabra.data.AppDatabase
import com.vitorxbranco.datacabra.data.ControleLeiteiroDao
import com.vitorxbranco.datacabra.data.DataImportRepository
import com.vitorxbranco.datacabra.data.ProducaoDiariaDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardViewStats = findViewById<CardView>(R.id.cv_stats)
        val cardViewGraphics = findViewById<CardView>(R.id.cv_graphics)

        cardViewStats.setOnClickListener {
            openStatsActivity()
        }

        cardViewGraphics.setOnClickListener {
            openGraphicsActivity()
        }
    }

    private fun openStatsActivity() {
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)
    }

    private fun openGraphicsActivity() {
        val intent = Intent(this, GraphicsActivity::class.java)
        startActivity(intent)
    }
}