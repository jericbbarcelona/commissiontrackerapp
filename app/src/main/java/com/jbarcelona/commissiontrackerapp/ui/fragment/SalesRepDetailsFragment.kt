package com.jbarcelona.commissiontrackerapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbarcelona.commissiontrackerapp.R
import com.jbarcelona.commissiontrackerapp.constants.Constants
import com.jbarcelona.commissiontrackerapp.databinding.FragmentSalesDetailsBinding
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import com.jbarcelona.commissiontrackerapp.ui.adapter.SystemDetailsAdapter
import com.jbarcelona.commissiontrackerapp.ui.viewmodel.SalesRepDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SalesRepDetailsFragment : Fragment() {

    private lateinit var viewModel: SalesRepDetailsViewModel
    private lateinit var binding: FragmentSalesDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(SalesRepDetailsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentSalesDetailsBinding>(
            inflater,
            R.layout.fragment_sales_details,
            container,
            false
        ).apply {
            this.lifecycleOwner = activity
        }
        populateSalesRepDetails()
        return binding.root
    }

    private fun populateSalesRepDetails() {
        val salesRepDetails: GetSalesRepDataResponse? = arguments?.getParcelable(SalesRepFragment.ARG_SALES_REP_DETAILS)
        salesRepDetails?.let {
            val layoutManager = LinearLayoutManager(requireContext())
            val sysDetailsAdapter = SystemDetailsAdapter(it.systemDetailsAndNote.orEmpty())
            binding.rvSystemDetails.layoutManager = layoutManager
            binding.rvSystemDetails.adapter = sysDetailsAdapter
            binding.tvCustomerName.text = it.customerName
            binding.tvContactNo.text = it.customerContact
            binding.tvAddress.text = it.address
            binding.tvJobNumber.text = it.jobNumber
            binding.tvLeadSource.text = it.leadSource
            binding.tvJobSubmissionTime.text = it.jobSubmissionTime
            binding.tvPaymentMethod.text = it.paymentMethod
            binding.tvFullPrice.text = "$${it.contractFullPrice}"
            binding.tvCashReceiving.text = "$${it.cashReceiving}"
            binding.tvContractFullPrice.text = "$${it.contractFullPrice}"
            when (it.customerPayment?.lowercase(Locale.ENGLISH)) {
                Constants.CustomerPayment.PAID -> {
                    updateCustomerPaymentView(it.customerPayment, R.color.green)
                }
                Constants.CustomerPayment.FALSE -> {
                    updateCustomerPaymentView(it.customerPayment, R.color.red)
                }
                Constants.CustomerPayment.PARTIAL -> {
                    updateCustomerPaymentView(it.customerPayment, R.color.orange)
                }
            }
        }
    }

    private fun updateCustomerPaymentView(customerPayment: String, color: Int) {
        val payment = if (Constants.CustomerPayment.FALSE.equals(customerPayment, true)) "Unpaid" else customerPayment
        binding.llBanner.setBackgroundResource(color)
        binding.tvCustomerPayment.text = payment.substring(0, 1).uppercase(Locale.ENGLISH) + payment.substring(1)
        binding.llPaymentInfo.setBackgroundResource(color)
    }
}