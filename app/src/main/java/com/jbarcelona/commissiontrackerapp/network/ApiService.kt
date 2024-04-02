package com.jbarcelona.commissiontrackerapp.network

import com.jbarcelona.commissiontrackerapp.constants.Constants
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.GET_SALES_REP)
    suspend fun getSalesRepData(): Response<List<GetSalesRepDataResponse>>
}