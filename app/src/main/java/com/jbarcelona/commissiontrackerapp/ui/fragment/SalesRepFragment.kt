package com.jbarcelona.commissiontrackerapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jbarcelona.commissiontrackerapp.R
import com.jbarcelona.commissiontrackerapp.databinding.FragmentSalesRepBinding
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import com.jbarcelona.commissiontrackerapp.ui.adapter.SalesRepAdapter
import com.jbarcelona.commissiontrackerapp.ui.listener.OnRedirectToDetailsPageListener
import com.jbarcelona.commissiontrackerapp.ui.viewmodel.SalesRepViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SalesRepFragment : Fragment(), OnRedirectToDetailsPageListener {

    private lateinit var viewModel: SalesRepViewModel
    private lateinit var binding: FragmentSalesRepBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var salesRepAdapter: SalesRepAdapter

    companion object {
        const val ARG_SALES_REP_DETAILS = "ARG_SALES_REP_DETAILS"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(SalesRepViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentSalesRepBinding>(
            inflater,
            R.layout.fragment_sales_rep,
            container,
            false
        ).apply {
            this.lifecycleOwner = activity
        }
        initAdapter()
        setProgressBarVisibility(true)
        viewModel.getSalesRepData()
        setupObservers()
        initListener()
        return binding.root
    }

    private fun initAdapter() {
        layoutManager = LinearLayoutManager(requireContext())
        salesRepAdapter = SalesRepAdapter(emptyList(), this, requireContext())
        binding.rvSalesRep.layoutManager = layoutManager
        binding.rvSalesRep.adapter = salesRepAdapter
    }

    private fun initListener() {
        binding.btnRetry.setOnClickListener {
            setProgressBarVisibility(true)
            viewModel.getSalesRepData()
        }
    }

    private fun setupObservers() {
        viewModel.populateSalesRepData.observe(viewLifecycleOwner) {
            setProgressBarVisibility(false)
            when (it) {
                is SalesRepViewModel.DataResult.Success -> {
                    binding.rvSalesRep.visibility = View.VISIBLE
                    binding.tvErrorMessage.visibility = View.GONE
                    binding.ivError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                    salesRepAdapter.salesRepList = it.responseData.orEmpty()
                    salesRepAdapter.notifyDataSetChanged()
                }
                is SalesRepViewModel.DataResult.Error -> {
                    binding.tvErrorMessage.text = it.message
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    binding.ivError.visibility = View.VISIBLE
                    binding.rvSalesRep.visibility = View.GONE
                    binding.btnRetry.visibility = View.VISIBLE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setProgressBarVisibility(visible: Boolean) {
        if (visible) {
            binding.rlProgressbar.visibility = View.VISIBLE
        } else {
            binding.rlProgressbar.visibility = View.GONE
        }
    }

    override fun onRedirectPage(item: GetSalesRepDataResponse?) {
        val bundle = Bundle()
        bundle.putParcelable(ARG_SALES_REP_DETAILS, item)
        findNavController(binding.root).navigate(R.id.navigateToSalesRepDetailsFragment, bundle)
    }
}