package com.divar.domain.repository.filter

import com.divar.domain.model.filter.AdsFilter
import kotlinx.coroutines.flow.Flow

interface FilterRepository {

    suspend fun saveFilterFromHome(adsFilter: AdsFilter?)

    suspend fun saveFilterFromCategory(adsFilter: AdsFilter?)

    suspend fun readFilterFromHome(): Flow<AdsFilter?>

    suspend fun readFilterFromCategory(): Flow<AdsFilter?>

}