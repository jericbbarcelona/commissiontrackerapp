package com.jbarcelona.commissiontrackerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jbarcelona.commissiontrackerapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SalesRepDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
}