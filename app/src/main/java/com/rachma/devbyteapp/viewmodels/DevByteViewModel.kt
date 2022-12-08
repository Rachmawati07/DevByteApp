
package com.rachma.devbyteapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rachma.devbyteapp.database.getDatabase
import com.rachma.devbyteapp.domain.DevByteVideo
import com.rachma.devbyteapp.network.DevByteNetwork
import com.rachma.devbyteapp.repository.VideosRepository
import kotlinx.coroutines.*
import java.io.IOException

// Untuk mendeklarasikan class DevByteViewModel
class DevByteViewModel(application: Application) : AndroidViewModel(application) {

     // Sumber data ViewModel ini akan mengambil hasil.
    private val videosRepository = VideosRepository(getDatabase(application))

    // Daftar putar video yang ditampilkan di layar.
    val playlist = videosRepository.videos

     // Acara dipicu untuk kesalahan jaringan. Ini pribadi untuk menghindari mengekspos acara untuk menyetel nilai ini ke pengamat.
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

     // Acara dipicu untuk kesalahan jaringan. Tampilan harus menggunakan ini untuk mendapatkan akses ke datanya.
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


     // Tandai untuk menampilkan pesan kesalahan. Ini pribadi untuk menghindari mengekspos acara untuk menyetel nilai ini ke pengamat.
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

     // Tandai untuk menampilkan pesan kesalahan. Tampilan harus menggunakan ini untuk mendapatkan akses ke datanya.
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


     // init{} langsung dipanggil saat ViewModel ini dibuat.
    init {
        refreshDataFromRepository()
    }

     // Menyegarkan data dari repositori. Gunakan peluncuran coroutine untuk dijalankan di utas latar belakang.
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                videosRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(playlist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    // Mereset kesalahan jaringan
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    // untuk membuat DevByteViewModel dengan parameter
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
