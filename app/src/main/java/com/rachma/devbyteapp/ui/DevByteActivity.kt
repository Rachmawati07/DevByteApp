
package com.rachma.devbyteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rachma.devbyteapp.R

// Ini adalah aplikasi aktivitas tunggal yang menggunakan pustaka Navigasi. Konten ditampilkan oleh Fragmen.
class DevByteActivity : AppCompatActivity() {

     // Dipanggil saat aktivitas dimulai. Di sinilah sebagian besar inisialisasi harus pergi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_byte_viewer)
    }
}
