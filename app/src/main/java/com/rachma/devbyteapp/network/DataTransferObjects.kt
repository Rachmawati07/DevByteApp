
package com.rachma.devbyteapp.network

import com.rachma.devbyteapp.database.DatabaseVideo
import com.rachma.devbyteapp.domain.DevByteVideo
import com.squareup.moshi.JsonClass

// DataTransferObjects masuk dalam file ini. Ini bertanggung jawab untuk mem-parsing respons dari server
// atau memformat objek untuk dikirim ke server. Anda harus mengonversinya menjadi objek domain sebelumnya menggunakan mereka
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

// Video mewakili devbyte yang dapat diputar.
@JsonClass(generateAdapter = true)
data class NetworkVideo(
        val title: String,
        val description: String,
        val url: String,
        val updated: String,
        val thumbnail: String,
        val closedCaptions: String?)

//  Untuk Mengkonversi hasil Jaringan ke objek basis data
fun NetworkVideoContainer.asDomainModel(): List<DevByteVideo> {
    return videos.map {
        DevByteVideo(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}

 //  Untuk Mengkonversi hasil Jaringan ke objek basis data
fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}

