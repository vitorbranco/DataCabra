import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.BarEntry
import com.vitorxbranco.datacabra.data.ProducaoDiaria
import com.vitorxbranco.datacabra.data.ProducaoDiariaDao
import com.vitorxbranco.datacabra.presentation.viewmodels.ProducaoDiariaGraphicsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProducaoDiariaGraphicsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var producaoDiariaDao: ProducaoDiariaDao

    @Mock
    lateinit var observer: Observer<List<BarEntry>>

    private lateinit var producaoDiariaLiveData: MutableLiveData<List<ProducaoDiaria>>

    private lateinit var viewModel: ProducaoDiariaGraphicsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        producaoDiariaLiveData = MutableLiveData<List<ProducaoDiaria>>()
        `when`(producaoDiariaDao.getAll()).thenReturn(producaoDiariaLiveData)
        viewModel = ProducaoDiariaGraphicsViewModel(producaoDiariaDao)
        viewModel.barEntryLiveData.observeForever(observer)
    }

    @Test
    fun testBarEntryLiveData() {
        // Dados de exemplo
        val producaoDiariaList = listOf(
            ProducaoDiaria(
                "1",
                "1",
                "10",
                "1",
                "100",
                "1",
                "1"
            ),
            ProducaoDiaria(
                "2",
                "2",
                "20",
                "2",
                "200",
                "2",
                "2",
            )
        )

        // Mock do retorno do DAO
        val producaoDiariaLiveData = MutableLiveData<List<ProducaoDiaria>>()
        `when`(producaoDiariaDao.getAll()).thenReturn(producaoDiariaLiveData)

        producaoDiariaLiveData.value = producaoDiariaList

        // Chama o método que atualiza o LiveData de BarEntry
        viewModel.barEntryLiveData

        // Verifica se o valor do LiveData de BarEntry está correto
        val expectedBarEntryList = listOf(
            BarEntry(10f, 100f),
            BarEntry(20f, 200f)
        )
        assertEquals(expectedBarEntryList, viewModel.barEntryLiveData.value)

        // Verifica se o observer foi chamado com o valor correto
        viewModel.barEntryLiveData.observeForever(observer)
        viewModel.barEntryLiveData.value?.let {
            assertEquals(expectedBarEntryList, it)
        }
    }
}
