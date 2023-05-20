package com.vitorxbranco.datacabra.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProducaoDiaria::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun producaoDiariaDao(): ProducaoDiariaDao
    abstract fun controleLeiteiroDao(): ControleLeiteiroDao
}