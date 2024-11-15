package com.divar.data.repository.filter

import android.content.SharedPreferences
import com.divar.data.mapper.category.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.category.Category
import com.divar.domain.model.category.CategoryOfAds
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.repository.category.CategoryRepository
import com.divar.domain.repository.filter.FilterRepository
import com.divar.network.api.category.CategoryApiService
import com.divar.secure_shared_pref.SharedPrefConstant
import com.divar.utils.fromJson
import com.divar.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FilterRepository {
    override suspend fun saveFilterFromHome(adsFilter: AdsFilter?) {
        sharedPreferences.edit().putString(SharedPrefConstant.HOME_FILTER, adsFilter.toJson()).apply()
    }

    override suspend fun saveFilterFromCategory(adsFilter: AdsFilter?) {
        sharedPreferences.edit().putString(SharedPrefConstant.CATEGORY_FILTER, adsFilter.toJson()).apply()
    }

    override suspend fun readFilterFromHome(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstant.HOME_FILTER, null)?.fromJson<AdsFilter?>()?.let { filter ->
            emit(filter)
        } ?: run { emit(null) }
    }

    override suspend fun readFilterFromCategory(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstant.CATEGORY_FILTER, null)?.fromJson<AdsFilter?>()?.let { filter ->
            emit(filter)
        } ?: run { emit(null) }
    }

}