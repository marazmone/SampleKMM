package com.marazmone.samplekmm.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.samplekmm.android.presentation.base.action.ActionResource
import com.marazmone.samplekmm.android.presentation.base.action.ActionState
import com.marazmone.samplekmm.android.presentation.base.setError
import com.marazmone.samplekmm.android.presentation.base.setLoading
import com.marazmone.samplekmm.android.presentation.base.setSuccess
import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _launchesLiveData = MutableStateFlow<ActionResource<List<RocketLaunchEntitys>>>(ActionResource.empty())
    val launchesLiveData: SharedFlow<ActionResource<List<RocketLaunchEntitys>>> = _launchesLiveData.asStateFlow()

    init {
        getLaunches()
    }

    fun getLaunches() {
        viewModelScope.launch {
            _launchesLiveData.setLoading()
            runCatching {
                launchesUseCase.execute()
            }.onSuccess { result ->
                _launchesLiveData.setSuccess(result)
            }.onFailure { exception: Throwable ->
                _launchesLiveData.setError(exception.localizedMessage)
            }
        }
    }
}