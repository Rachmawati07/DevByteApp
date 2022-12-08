
package com.rachma.devbyteapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rachma.devbyteapp.domain.DevByteVideo


// Untuk mendeklarasikan kelas yang bernama DatabaseVideo
// DatabaseVideo mewakili entitas video dalam database.
@Entity
data class DatabaseVideo constructor(
        @PrimaryKey
        val url: String,
        val updated: String,
        val title: String,
        val description: String,
        val thumbnail: String)



//  Memetakan DatabaseVideos ke entitas domain
fun List<DatabaseVideo>.asDomainModel(): List<DevByteVideo> {
        return map {
                DevByteVideo(
                        url = it.url,
                        title = it.title,
                        description = it.description,
                        updated = it.updated,
                        thumbnail = it.thumbnail)
        }
}
