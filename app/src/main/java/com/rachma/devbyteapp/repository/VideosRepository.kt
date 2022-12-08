
package com.rachma.devbyteapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rachma.devbyteapp.database.VideosDatabase
import com.rachma.devbyteapp.database.asDomainModel
import com.rachma.devbyteapp.domain.DevByteVideo
import com.rachma.devbyteapp.network.DevByteNetwork
import com.rachma.devbyteapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


// Repositori untuk mengambil video devbyte dari jaringan dan menyimpannya di disk
class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    // Segarkan video yang disimpan di cache offline
    // Fungsi ini menggunakan operator IO untuk memastikan operasi database insert database terjadi pada operator IO. Dengan beralih ke operator IO menggunakan `withContext` ini
    // Fungsi sekarang aman untuk dipanggil dari utas apa pun termasuk utas Utama.
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called");
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }

}
