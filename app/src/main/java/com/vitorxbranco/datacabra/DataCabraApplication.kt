package com.vitorxbranco.datacabra

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.vitorxbranco.datacabra.data.AppDatabase
import com.vitorxbranco.datacabra.data.DataImportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataCabraApplication : Application() {

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "datacabra-database"
        ).fallbackToDestructiveMigration().build()

        val producaoDiariaDao = database.producaoDiariaDao()
        val controleLeiteiroDao = database.controleLeiteiroDao()

        val dataImportRepository = DataImportRepository(this, producaoDiariaDao, controleLeiteiroDao)

        CoroutineScope(Dispatchers.IO).launch {
            dataImportRepository.importDataFromProducaoCSV()
            dataImportRepository.importDataFromControleCSV()
        }
    }

    fun getAppDatabase(): AppDatabase{
        return database
    }
}