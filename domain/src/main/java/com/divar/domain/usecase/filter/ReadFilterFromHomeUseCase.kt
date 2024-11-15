package com.divar.domain.usecase.filter

import com.divar.domain.repository.filter.FilterRepository
import javax.inject.Inject

class ReadFilterFromHomeUseCase @Inject constructor(
    private val repo: FilterRepository
) {
    suspend operator fun invoke() = repo.readFilterFromHome()
}