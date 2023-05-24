package com.vitorxbranco.datacabra.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.github.mikephil.charting.data.BarEntry
import com.vitorxbranco.datacabra.DataCabraApplication
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.data.ProducaoDiariaDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProducaoDiariaGraphicsViewModel(
    private val producaoDiariaDao: ProducaoDiariaDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val barEntryLiveData: MutableLiveData<List<BarEntry>> = MutableLiveData<List<BarEntry>>()
    private val barEntryList: ArrayList<BarEntry> = arrayListOf()

    fun updateBarEntryLiveData() {
        viewModelScope.launch(dispatcher) {
            val producaoList = producaoDiariaDao.getAll()
            convertProducaoToBarEntry(producaoList)
            barEntryLiveData.postValue(barEntryList)
        }
    }

    private fun convertProducaoToBarEntry(list: List<ProducaoDiaria>) {
        list.forEach { parametro ->
            val barEntry = BarEntry(parametro.primeiraOrdenha.toFloat(), parametro.totalLitrosDia.toFloat())
            barEntryList.add(barEntry)
        }
    }


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