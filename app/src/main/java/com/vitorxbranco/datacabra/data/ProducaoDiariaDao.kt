package com.vitorxbranco.datacabra.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProducaoDiariaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(producaoDiariaList: List<ProducaoDiaria>)

    @Query("select * from producaodiaria")
    fun getAll(): List<ProducaoDiaria>

}