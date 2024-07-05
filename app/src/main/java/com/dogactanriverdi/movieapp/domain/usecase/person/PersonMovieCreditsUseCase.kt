package com.dogactanriverdi.movieapp.domain.usecase.person

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.person.toPersonDetail
import com.dogactanriverdi.movieapp.data.source.remote.mapper.person.toPersonMovieCredits
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCredits
import com.dogactanriverdi.movieapp.domain.model.person.detail.PersonDetail
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonMovieCreditsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Int,
        language: String
    ): Flow<Resource<PersonMovieCredits>> {
        return flow {
            try {
                val personMovieCredits = personRepository.getPersonMovieCredits(personId, language)

                personMovieCredits.id?.let { id ->
                    if (id == -1) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = personMovieCredits.toPersonMovieCredits()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}