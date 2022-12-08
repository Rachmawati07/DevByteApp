
package com.rachma.devbyteapp.domain

import com.rachma.devbyteapp.util.smartTruncate


// Mendeklarasikan kelas yang bernama DevByteVideo
// Dan mendeklarasikan variavel yang bernama title, description, url, update, thumbnail
data class DevByteVideo(val title: String,
                        val description: String,
                        val url: String,
                        val updated: String,
                        val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}