package com.jbarcelona.commissiontrackerapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jbarcelona.commissiontrackerapp.R
import com.jbarcelona.commissiontrackerapp.constants.Constants
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import com.jbarcelona.commissiontrackerapp.ui.listener.OnRedirectToDetailsPageListener
import java.util.Locale

class SalesRepAdapter(
    var salesRepList: List<GetSalesRepDataResponse>,
    val listener: OnRedirectToDetailsPageListener?,
    private val context: Context
) : RecyclerView.Adapter<SalesRepAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sales_rep, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = salesRepList[position]
        holder.tvJobNumber.text = item.jobNumber
        holder.tvCustomerName.text = item.customerName
        holder.tvLeadSource.text = item.leadSource
        holder.tvSubmissionTime.text = item.jobSubmissionTime
        holder.tvCustomerPayment.text = item.customerPayment
        holder.tvFullPrice.text = "$${item.contractFullPrice}"
        when (item.customerPayment?.lowercase(Locale.ENGLISH)) {
            Constants.CustomerPayment.PAID -> {
                updateCustomerPaymentView(holder, item.customerPayment, R.color.green)
            }
            Constants.CustomerPayment.FALSE -> {
                updateCustomerPaymentView(holder, item.customerPayment, R.color.red)
            }
            Constants.CustomerPayment.PARTIAL -> {
                updateCustomerPaymentView(holder, item.customerPayment, R.color.orange)
            }
        }
        holder.cardView.setOnClickListener {
            listener?.onRedirectPage(item)
        }
    }

    private fun updateCustomerPaymentView(holder: ViewHolder, customerPayment: String, color: Int) {
        val payment = if (Constants.CustomerPayment.FALSE.equals(customerPayment, true)) "Unpaid" else customerPayment
        holder.tvCustomerPayment.text = payment.substring(0, 1).uppercase(Locale.ENGLISH) + payment.substring(1)
        holder.tvCustomerPayment.setTextColor(context.getColor(color))
        holder.ivPaid.setColorFilter(ContextCompat.getColor(context, color))
    }

    override fun getItemCount() = salesRepList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJobNumber: TextView = view.findViewById(R.id.tv_job_number)
        val tvCustomerName: TextView = view.findViewById(R.id.tv_customer_name)
        val tvLeadSource: TextView = view.findViewById(R.id.tv_lead_source)
        val tvSubmissionTime: TextView = view.findViewById(R.id.tv_job_submission_time)
        val tvCustomerPayment: TextView = view.findViewById(R.id.tv_customer_payment)
        val tvFullPrice: TextView = view.findViewById(R.id.tv_full_price)
        val ivPaid: ImageView = view.findViewById(R.id.iv_paid)
        val cardView: CardView = view.findViewById(R.id.cv_cardview)
    }
}