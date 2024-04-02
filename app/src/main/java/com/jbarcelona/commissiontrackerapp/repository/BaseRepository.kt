package com.jbarcelona.commissiontrackerapp.repository

import com.jbarcelona.commissiontrackerapp.network.ApiResource
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse

interface BaseRepository {

    suspend fun getSalesRepData(): ApiResource<List<GetSalesRepDataResponse>>
}