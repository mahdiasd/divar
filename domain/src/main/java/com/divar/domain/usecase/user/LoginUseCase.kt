package com.divar.domain.usecase.user

import com.divar.domain.model.DataResult
import com.divar.domain.model.parameter.Parameter
import com.divar.domain.model.user.User
import com.divar.domain.repository.parameter.ParameterRepository
import com.divar.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(mobile:String, password: String): Flow<DataResult<User>> {
        return repo.login(mobile, password)
    }
}