package com.divar.data.di.repository

import com.divar.data.repository.ads.AdsRepositoryImpl
import com.divar.data.repository.ads.AdsSummaryRepositoryImpl
import com.divar.data.repository.category.CategoryOfAdsRepositoryImpl
import com.divar.data.repository.category.CategoryRepositoryImpl
import com.divar.data.repository.filter.FilterRepositoryImpl
import com.divar.data.repository.location.LocationRepositoryImpl
import com.divar.data.repository.parameter.ParameterRepositoryImpl
import com.divar.domain.repository.ads.AdsRepository
import com.divar.domain.repository.ads.AdsSummaryRepository
import com.divar.domain.repository.category.CategoryOfAdsRepository
import com.divar.domain.repository.category.CategoryRepository
import com.divar.domain.repository.filter.FilterRepository
import com.divar.domain.repository.location.LocationRepository
import com.divar.domain.repository.parameter.ParameterRepository
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

    @Binds
    fun bindLocation(repo: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindCategoryOfAds(repo: CategoryOfAdsRepositoryImpl): CategoryOfAdsRepository

    @Binds
    fun bindParameterRepository(repo: ParameterRepositoryImpl): ParameterRepository

    @Binds
    fun bindFilterRepo(repo: FilterRepositoryImpl): FilterRepository

    @Binds
    fun bindAdsRepo(repo: AdsRepositoryImpl): AdsRepository
}