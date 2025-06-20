package com.example.kotlindemo.Gallery

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MediaRepository(application)

    private var _mediaItems = MutableLiveData<List<MediaItem>>()
    val mediaItem: LiveData<List<MediaItem>> get() = _mediaItems

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadMedia() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val allMedia = repository.loadAllMedia()
                _mediaItems.value = allMedia
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load media"
            } finally {
                _isLoading.value = false
            }
        }
    }
}