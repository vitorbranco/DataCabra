package com.vitorxbranco.datacabra.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitorxbranco.datacabra.DataCabraApplication
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.data.ProducaoDiariaDao

class ProducaoDiariaGraphicsViewModel(private val producaoDiariaDao: ProducaoDiariaDao) : ViewModel() {

    val producaoDiariaLiveData: LiveData<List<ProducaoDiaria>> = producaoDiariaDao.getAll()

    companion object {

        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val databaseInstance = (application as DataCabraApplication).getAppDatabase()
            val dao = databaseInstance.producaoDiariaDao()
            return object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProducaoDiariaGraphicsViewModel(dao) as T
                }
            }
        }
    }

}