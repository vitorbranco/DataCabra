package com.vitorxbranco.datacabra.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProducaoDiariaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producaoDiaria: ProducaoDiaria)

    @Query("select * from producao_diaria_tabela")
    fun getAll(): LiveData<List<ProducaoDiaria>>

}