package com.example.kotlindemo.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineViewModel : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(500)
                Log.e("CoroutineViewModel", "Hello everyone")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("CoroutineViewModel", "View model destroyed")
    }
}