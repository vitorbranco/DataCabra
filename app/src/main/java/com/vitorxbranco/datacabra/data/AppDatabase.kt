package com.vitorxbranco.datacabra.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProducaoDiaria::class, ControleLeiteiro::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun producaoDiariaDao(): ProducaoDiariaDao
    abstract fun controleLeiteiroDao(): ControleLeiteiroDao
}