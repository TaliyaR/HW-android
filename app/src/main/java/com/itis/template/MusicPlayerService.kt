package com.itis.template

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MusicPlayerService : Service() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
