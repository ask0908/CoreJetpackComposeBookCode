package com.example.composebookexample.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowDemoViewModel: ViewModel() {
    val myFlow: Flow<Int> = flow {
        for (i in 1 .. 5) {
            delay(1000)
            emit(i)
        }
    }
    fun doubleIt(value: Int) = flow {
        emit(value)
        delay(1000)
        emit(value + value)
    }
    val newFlow = myFlow.map {
        "Current Value = $it"
    }
    val myFlow2 = flowOf(2, 4, 6, 8)
    val myArrayFlow = arrayOf<String>("Red", "Green", "Blue").asFlow()

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun increaseValue() {
        _stateFlow.value += 1
    }

    private val _sharedFlow = MutableSharedFlow<Int>(
        replay = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun startSharedFlow() = viewModelScope.launch {
        for (i in 1 .. 5) {
            _sharedFlow.emit(i)
            delay(1000)
        }
    }

    val subCount = _sharedFlow.subscriptionCount
    val hotFlow = myFlow.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        replay = 1
    )
}