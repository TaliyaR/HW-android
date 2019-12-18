package com.itis.template

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MusicPlayerService : Service(), MediaPlayer.OnPreparedListener {

    private lateinit var mediaPlayer: MediaPlayer
    private val binder = LocalBinder()
    private var resumePosn: Int = 0
    private var mediaFile: Int = 0

    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    override fun onBind(intent: Intent): IBinder = binder

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Служба создана", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Служба запущена", Toast.LENGTH_SHORT).show()
        mediaFile = intent.extras?.getInt("song") ?: 0
        when (intent.action) {
            Action.CREATE_PLAY -> {
                mediaPlayer = MediaPlayer.create(this, mediaFile)
                mediaPlayer.apply {
                    setOnPreparedListener(this@MusicPlayerService)
                }
                Notification.sendNotification(this, SongRepository.getCurrent())
            }
            Action.ACTION_PLAY -> {
                playMedia()
            }
            Action.ACTION_RESUME -> {
                resumeMedia()
            }
            Action.ACTION_PAUSE -> {
                pauseMedia()
            }
            Action.ACTION_STOP -> {
                stopMedia()
            }
            Action.ACTION_NEXT -> {
                nextMedia()
            }
            Action.ACTION_PREV -> {
                prevMedia()
            }

        }
        return START_STICKY
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Служба остановлена", Toast.LENGTH_SHORT).show()
        stopWithRelease()
    }

    private fun nextMedia() {
        stopWithRelease()
        mediaPlayer = MediaPlayer.create(this, SongRepository.getCurrent().file)
        mediaPlayer.start()
    }

    private fun prevMedia() {
        stopWithRelease()
        mediaPlayer = MediaPlayer.create(this, SongRepository.getCurrent().file)
        mediaPlayer.start()
    }

    private fun playMedia() {
        mediaPlayer.start()
        Notification.sendNotification(this, SongRepository.getCurrent())
    }

    private fun stopMedia() {
        mediaPlayer.stop()
        Notification.sendNotification(this, SongRepository.getCurrent())
    }

    private fun stopWithRelease() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun pauseMedia() {
        mediaPlayer.pause()
        resumePosn = mediaPlayer.currentPosition
        Notification.sendNotification(this, SongRepository.getCurrent())

    }

    private fun resumeMedia() {
        mediaPlayer.seekTo(resumePosn)
        mediaPlayer.start()
    }

}
