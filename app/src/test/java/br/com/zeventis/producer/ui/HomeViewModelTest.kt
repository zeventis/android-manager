package br.com.zeventis.producer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.zeventis.producer.TestCoroutineRule
import br.com.zeventis.producer.domain.model.home.HomeEventsModel
import br.com.zeventis.producer.domain.usecase.HomeUseCase
import br.com.zeventis.producer.presentation.ui.home.HomeViewEvents
import br.com.zeventis.producer.presentation.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private var useCase = mockk<HomeUseCase>(relaxed = true)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun getEventsEmptySuccess() = testCoroutineRule.runBlockingTest {
        val response = arrayListOf<HomeEventsModel>() //TODO Colocar a lista de eventos

        coEvery { useCase.execute() } returns response
        viewModel.getEvents()
        Assert.assertEquals(viewModel.viewEvents.value, HomeViewEvents.OnGetEventsEmpty())
    }
}