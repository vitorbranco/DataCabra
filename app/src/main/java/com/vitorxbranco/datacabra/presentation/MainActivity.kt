package com.vitorxbranco.datacabra.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.vitorxbranco.datacabra.R

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