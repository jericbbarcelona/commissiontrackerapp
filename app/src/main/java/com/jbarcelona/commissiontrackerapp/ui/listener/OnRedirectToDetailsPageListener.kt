package com.jbarcelona.commissiontrackerapp.ui.listener

import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse

interface OnRedirectToDetailsPageListener {
    fun onRedirectPage(item: GetSalesRepDataResponse?)
}