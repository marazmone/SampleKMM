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
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _launchesLiveData = MutableLiveData<ActionResource<List<RocketLaunchEntitys>>>()
    val launchesLiveData: LiveData<ActionResource<List<RocketLaunchEntitys>>> get() = _launchesLiveData

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