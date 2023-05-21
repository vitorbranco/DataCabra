package com.vitorxbranco.datacabra.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ControleLeiteiroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(controleLeiteiroList: List<ControleLeiteiro>)

    @Query("select * from controleleiteiro")
    fun getAll(): List<ControleLeiteiro>
}