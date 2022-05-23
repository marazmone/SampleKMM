package com.marazmone.samplekmm.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _launchesLiveData = MutableLiveData<LaunchesAction>()
    val launchesLiveData: LiveData<LaunchesAction> get() = _launchesLiveData

    init {
        getLaunches()
    }

    fun getLaunches() {
        viewModelScope.launch {
            _launchesLiveData.value = LaunchesAction.Loading
            runCatching {
                launchesUseCase.execute()
            }.onSuccess { result ->
                _launchesLiveData.value = LaunchesAction.Success(result)
            }.onFailure { exception: Throwable ->
                _launchesLiveData.value = LaunchesAction.Error(exception)
            }
        }
    }

    sealed class LaunchesAction {

        data class Success(val result: List<RocketLaunchEntitys>) : LaunchesAction()

        data class Error(val exception: Throwable) : LaunchesAction()

        object Loading : LaunchesAction()
    }
}