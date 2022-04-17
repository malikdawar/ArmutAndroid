package com.example.armut.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.armut.data.DataState
import com.example.armut.data.HomeDataSource
import com.example.armut.data.model.Post
import com.example.armut.data.model.Service
import com.example.armut.data.usecases.FetchHomeUseCase
import com.example.armut.ui.home.ContentState
import com.example.armut.ui.home.HomeUiState
import com.example.armut.ui.home.HomeViewModel
import com.example.armut.utils.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    // Subject under test
    private lateinit var sut: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @MockK
    lateinit var fetchHomeUseCase: FetchHomeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, homeUiItems are fetched`() = runBlocking {
        // Given
        val givenHomeModel = HomeDataSource.getHomeComponents()
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val allServiceObserver = mockk<Observer<List<Service>>>(relaxed = true)
        val popularServiceObserver = mockk<Observer<List<Service>>>(relaxed = true)
        val postServiceObserver = mockk<Observer<List<Post>>>(relaxed = true)

        // When
        coEvery { fetchHomeUseCase.invoke() }
            .returns(flowOf(DataState.success(givenHomeModel)))

        // Invoke
        sut = HomeViewModel(fetchHomeUseCase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.allServicesLiveData.observeForever(allServiceObserver)
        sut.popularServicesLiveData.observeForever(popularServiceObserver)
        sut.blogPostsLiveData.observeForever(postServiceObserver)

        // Then
        coVerify(exactly = 1) { fetchHomeUseCase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { allServiceObserver.onChanged(match { it.size == givenHomeModel.allServices?.size }) }
        verify { popularServiceObserver.onChanged(match { it.size == givenHomeModel.popular?.size }) }
        verify { postServiceObserver.onChanged(match { it.size == givenHomeModel.posts?.size }) }
    }
}
