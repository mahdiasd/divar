package com.divar.data.di.repository

import com.divar.data.repository.ads.AdsSummaryRepositoryImpl
import com.divar.data.repository.category.CategoryRepositoryImpl
import com.divar.domain.repository.ads.AdsSummaryRepository
import com.divar.domain.repository.category.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindRepositories {

    @Binds
    fun bindCategory(repo: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindAdsSummary(repo: AdsSummaryRepositoryImpl): AdsSummaryRepository
}