package com.dhruv.camerax.presentation

import android.app.Application
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.AndroidViewModel
import com.dhruv.camerax.domain.repository.CameraRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraRepository: CameraRepository
): ViewModel() {

    private val _isRecording = MutableStateFlow(false)
    val isRecording = _isRecording.asStateFlow()

    fun onTakePhoto(
        controller: LifecycleCameraController
    ) {
        viewModelScope.launch {
            cameraRepository.takePhoto(controller)
        }
    }

    fun onRecordVideo(
        controller: LifecycleCameraController
    ) {
        _isRecording.update { !isRecording.value }

        viewModelScope.launch {
            cameraRepository.recordVideo(controller)
        }
    }

}