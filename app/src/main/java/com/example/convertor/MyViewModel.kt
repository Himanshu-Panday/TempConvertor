package com.example.convertor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ConvertorState(
    val userData: String = "",
    val result: String = "",
    val isFahrenheit: Boolean = false
)

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConvertorState())
    val uiState: StateFlow<ConvertorState> =_uiState

    private fun convertTemperature(){
        val number = _uiState.value.userData.toInt()
        val result = if(_uiState.value.isFahrenheit){
            (number - 32)*5 / 9
        }else{
            (number * 9 / 5) + 32
        }

        _uiState.value = _uiState.value.copy(result = result.toString())
    }
    fun updateSwitchState(isFahrenheit: Boolean){
        _uiState.value = _uiState.value.copy(isFahrenheit = isFahrenheit)
    }
    fun onUserSubmit(){
        convertTemperature()
    }
    fun onTextChange(userData: String){
        _uiState.value = _uiState.value.copy(userData = userData)
    }
}