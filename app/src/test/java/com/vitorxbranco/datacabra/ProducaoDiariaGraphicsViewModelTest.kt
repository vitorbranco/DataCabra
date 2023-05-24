package com.vitorxbranco.datacabra

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.mikephil.charting.data.BarEntry
import com.vitorxbranco.datacabra.CoroutineTestRule
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.data.ProducaoDiariaDao
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProducaoDiariaGraphicsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var producaoDiariaDao: ProducaoDiariaDao

    private lateinit var viewModel: ProducaoDiariaGraphicsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProducaoDiariaGraphicsViewModel(producaoDiariaDao, coroutineRule.testDispatcher)
    }

    @Test
    fun `updateBarEntryLiveDataTest`() {
        val producaoList = listOf(
            ProducaoDiaria("1", "1", "1","1","1","1","1"),
            ProducaoDiaria("2", "2", "2","2","2","2","2")
        )
        val expectedBarEntries = arrayListOf(
            BarEntry(1f, 1f),
            BarEntry(2f, 2f)
        )

        Mockito.`when`(producaoDiariaDao.getAll()).thenReturn(producaoList)

        viewModel.updateBarEntryLiveData()


        Assert.assertNotNull(viewModel.barEntryLiveData.value)
//        Assert.assertEquals(expectedBarEntries, viewModel.barEntryLiveData.value)
    }
}
