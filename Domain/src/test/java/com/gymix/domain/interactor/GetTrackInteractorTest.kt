package com.gymix.domain.interactor

import com.google.common.truth.Truth.assertThat
import com.gymix.domain.data.FakeTrackRepository
import com.gymix.domain.repository.MusicApiRepository
import com.gymix.domain.useCase.GetTrackUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTrackInteractorTest {

    private lateinit var useCase: GetTrackInteractor
    private lateinit var repository: FakeTrackRepository

    @Before
    fun setUp() {
        //create fake repository
        repository = FakeTrackRepository()

        //create useCase
        useCase = GetTrackInteractor(repository)
    }

    @Test
    fun `test get track response is successful`() = runBlocking {
        val response = useCase.invoke().data

        assertThat(response).isEqualTo(response)
    }

}