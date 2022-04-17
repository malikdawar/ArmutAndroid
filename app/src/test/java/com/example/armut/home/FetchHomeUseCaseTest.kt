package com.example.armut.home

import com.example.armut.data.DataState
import com.example.armut.data.HomeDataSource
import com.example.armut.data.repository.home.HomeRepository
import com.example.armut.data.usecases.FetchHomeUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchHomeUseCaseTest {

    @MockK
    private lateinit var repository: HomeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given when the invoke in FetchHomeUseCase is called then it fetch the homeUiItems`() =
        runBlocking {
            // Given
            val sut = FetchHomeUseCase(repository)
            val givenHomeComponents = HomeDataSource.getHomeComponents()

            // When
            coEvery { repository.fetchHomeUiItems() }
                .returns(flowOf(DataState.success(givenHomeComponents)))

            // Invoke
            val homeUiItemsFlow = sut()

            // Then
            MatcherAssert.assertThat(homeUiItemsFlow, CoreMatchers.notNullValue())

            val homeUIItemsDataState = homeUiItemsFlow.first()
            MatcherAssert.assertThat(homeUIItemsDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                homeUIItemsDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java)
            )

            val homeModel = (homeUIItemsDataState as DataState.Success).data
            MatcherAssert.assertThat(homeModel, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(homeModel.allServices?.size, `is`(givenHomeComponents.allServices?.size))
            MatcherAssert.assertThat(homeModel.popular?.size, `is`(givenHomeComponents.popular?.size))
            MatcherAssert.assertThat(homeModel.posts?.size, `is`(givenHomeComponents.posts?.size))
        }
}
