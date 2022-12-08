
package com.rachma.devbyteapp

import android.app.Application
import timber.log.Timber

// Untuk mendeklarasikan kelas yang bernama DevByteApplication
// Untuk mengganti aplikasi untuk menyiapkan pekerjaan latar belakang melalui WorkManager
class DevByteApplication : Application() {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */

     // onCreate dipanggil sebelum layar pertama ditampilkan kepada pengguna.
     // Gunakan untuk mengatur tugas latar belakang apa pun, menjalankan operasi penyiapan yang mahal di latar belakang utas untuk menghindari keterlambatan memulai aplikasi.
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
