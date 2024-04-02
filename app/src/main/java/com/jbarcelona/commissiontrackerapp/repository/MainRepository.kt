package com.jbarcelona.commissiontrackerapp.repository

import com.jbarcelona.commissiontrackerapp.network.ApiDataSource
import com.jbarcelona.commissiontrackerapp.network.ApiResource
import com.jbarcelona.commissiontrackerapp.network.ApiService
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
) : BaseRepository, ApiDataSource() {

    override suspend fun getSalesRepData(): ApiResource<List<GetSalesRepDataResponse>> {
        return getResult { apiService.getSalesRepData() }
    }
}