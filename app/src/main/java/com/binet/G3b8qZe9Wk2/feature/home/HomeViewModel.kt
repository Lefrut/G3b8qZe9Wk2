package com.binet.G3b8qZe9Wk2.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import com.binet.G3b8qZe9Wk2.domain.repository.BinetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val binetRepository: BinetRepository
) : ViewModel() {

    private val _medicationsState = MutableStateFlow(emptyList<Medication>())
    val medicationsState = _medicationsState.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadMedications()
    }

    fun loadMedications(search: String? = null) = viewModelScope.launch {
        _isLoading.update { true }
        binetRepository.getMedications(search).onSuccess { medications ->
            _isLoading.update { false }
            _medicationsState.update { medications }
        }.onFailure {
            _isLoading.update { true }
        }
    }

}