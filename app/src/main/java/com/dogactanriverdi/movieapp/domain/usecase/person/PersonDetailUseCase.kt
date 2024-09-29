package com.dogactanriverdi.movieapp.domain.usecase.person

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.person.toPersonDetail
import com.dogactanriverdi.movieapp.domain.model.person.detail.PersonDetail
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PersonDetailUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(personId: Int, language: String): Flow<Resource<PersonDetail>> {
        return flow {
            try {
                val personDetail = personRepository.getPersonDetail(personId, language)

                personDetail.id?.let { id ->
                    if (id == -1) {
                        emit(Resource.Error(message = "A network error has occurred! Please try again."))
                    } else {
                        emit(Resource.Success(data = personDetail.toPersonDetail()))
                    }
                }
            } catch (e: IOException) {
                emit(Resource.Error(message = "No internet connection! Please check your internet connection."))
            } catch (e: HttpException) {
                when (e.code()) {
                    404 -> emit(Resource.Error(message = "No source found! (404)."))
                    500 -> emit(Resource.Error(message = "Server error! (500)."))
                    else -> emit(Resource.Error(message = "An error occurred: ${e.code()}"))
                }
            }
        }
    }
}