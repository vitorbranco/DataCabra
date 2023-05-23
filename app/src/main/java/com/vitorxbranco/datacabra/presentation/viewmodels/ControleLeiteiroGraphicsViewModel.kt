package com.vitorxbranco.datacabra.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitorxbranco.datacabra.DataCabraApplication
import com.vitorxbranco.datacabra.data.ControleLeiteiro
import com.vitorxbranco.datacabra.data.ControleLeiteiroDao

class ControleLeiteiroGraphicsViewModel(private val controleLeiteiroDao: ControleLeiteiroDao) :
    ViewModel() {

    val controleLeiteiroLiveData: LiveData<List<ControleLeiteiro>> = controleLeiteiroDao.getAll()

    companion object {

        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val databaseInstance = (application as DataCabraApplication).getAppDatabase()
            val dao = databaseInstance.controleLeiteiroDao()
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ControleLeiteiroGraphicsViewModel(dao) as T
                }
            }
        }
    }
}