package com.jbarcelona.commissiontrackerapp.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetSalesRepDataResponse(
    @field:Json(name = "lead_source")
    val leadSource: String? = null,
    @field:Json(name = "job_number")
    val jobNumber: String? = null,
    @field:Json(name = "customer_name")
    val customerName: String? = null,
    @field:Json(name = "customer_contact")
    val customerContact: String? = null,
    @field:Json(name = "address")
    val address: String? = null,
    @field:Json(name = "job_submission_time")
    val jobSubmissionTime: String? = null,
    @field:Json(name = "contract_full_price")
    val contractFullPrice: String? = null,
    @field:Json(name = "payment_method")
    val paymentMethod: String? = null,
    @field:Json(name = "customer_payment")
    val customerPayment: String? = null,
    @field:Json(name = "cash_receiving")
    val cashReceiving: String? = null,
    @field:Json(name = "system_details_and_note")
    val systemDetailsAndNote: List<SystemDetailsAndNote>? = null
): Parcelable

@Parcelize
data class SystemDetailsAndNote(
    @field:Json(name = "item_type")
    val itemType: String? = null,
    @field:Json(name = "quantity")
    val quantity: Int? = null
): Parcelable