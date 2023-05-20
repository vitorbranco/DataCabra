package com.vitorxbranco.datacabra.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ControleLeiteiroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(controleLeiteiro: ControleLeiteiro)

    @Query("select * from controle_leiteiro_tabela")
    fun getAll(): LiveData<List<ControleLeiteiro>>
}