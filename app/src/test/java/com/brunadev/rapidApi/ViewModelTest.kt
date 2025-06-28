package com.brunadev.rapidApi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunadev.rapidApi.model.Welcome
import com.brunadev.rapidApi.repository.Repository
import com.brunadev.rapidApi.presenter.Main.MainViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var mockListStateObserver: Observer<Welcome?>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(mockRepository)
        viewModel.listState.observeForever(mockListStateObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test init should fetch event then update null state`() {
        testScope.runBlockingTest {
            // Given
            val mockApiResponse = null
            `when`(mockRepository.getDataAPI()).thenReturn(mockApiResponse)

            // When
            viewModel.init()

            // Then
            assert(viewModel.listState.value == mockApiResponse)
        }
    }

    @Test
    fun `test init should fetch events then update list state`() {
        testScope.runBlockingTest {
            // Given
            val mockApiResponse = mockk<Welcome>()
            `when`(mockRepository.getDataAPI()).thenReturn(mockApiResponse)

            // When
            viewModel.init()

            // Then
            assert(viewModel.listState.value == mockApiResponse)
        }
    }
}
