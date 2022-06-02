package com.marazmone.samplekmm.presentation.main

import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.usecase.LaunchesDeleteByIdUseCase
import com.marazmone.samplekmm.domain.usecase.LaunchesObserveUseCase
import com.marazmone.samplekmm.domain.usecase.LaunchesUpdateUseCase
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel(
    val eventsDispatcher: EventsDispatcher<EventListener>
) : ViewModel(), KoinComponent {

    private val useCase: LaunchesUpdateUseCase by inject()

    private val launchesObserveUseCase: LaunchesObserveUseCase by inject()

    private val launchesDeleteByIdUseCase: LaunchesDeleteByIdUseCase by inject()

    init {
        getLaunches()
        observeLaunches()
    }

    fun getLaunches() {
        viewModelScope.launch {
            eventsDispatcher.dispatchEvent { showLoading() }
            useCase.execute().doOnResult(
                onError = { error ->
                    eventsDispatcher.dispatchEvent {
                        onError(error.msg)
                        hideLoading()
                    }
                }
            )
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch {
            launchesDeleteByIdUseCase.execute(id)
        }
    }

    private fun observeLaunches() {
        viewModelScope.launch {
            launchesObserveUseCase.execute().collect { result ->
                eventsDispatcher.dispatchEvent {
                    onSuccess(result)
                    hideLoading()
                }
            }
        }
    }

    interface EventListener {

        fun showLoading()

        fun hideLoading()

        fun onSuccess(result: List<RocketLaunchesModel>)

        fun onError(error: String)
    }
}