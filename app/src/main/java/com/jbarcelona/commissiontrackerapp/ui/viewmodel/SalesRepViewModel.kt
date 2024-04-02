package com.jbarcelona.commissiontrackerapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbarcelona.commissiontrackerapp.network.ApiResource
import com.jbarcelona.commissiontrackerapp.network.response.GetSalesRepDataResponse
import com.jbarcelona.commissiontrackerapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalesRepViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val populateSalesRepData = MutableLiveData<DataResult>()

    fun getSalesRepData() {
        viewModelScope.launch {
            try {
                val responseData = mainRepository.getSalesRepData()
                if (responseData.status == ApiResource.Status.SUCCESS) {
                    populateSalesRepData.postValue(DataResult.Success(responseData.data))
                } else {
                    populateSalesRepData.postValue(DataResult.Error(responseData.message.toString()))
                }
            } catch (e: Exception) {
                populateSalesRepData.value = DataResult.Error(e.message.toString())
            }
        }
    }

    sealed class DataResult {
        class Success(val responseData: List<GetSalesRepDataResponse>?) : DataResult()
        class Error(val message: String) : DataResult()
    }
}