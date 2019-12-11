package com.itis.template

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity() {
    var pause = false
    private var mediaPlayer = MediaPlayer()

    private lateinit var musicPlayerService: MusicPlayerService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.LocalBinder
            musicPlayerService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val title = intent?.extras?.getString(KEY_TITLE) ?: DEFAULT_TITLE
        val album = intent?.extras?.getString(KEY_ALBUM) ?: DEFAULT_ALBUM
        val artist = intent?.extras?.getString(KEY_ARTIST) ?: DEFAULT_ARTIST
        val cover = intent?.extras?.getInt(KEY_COVER) ?: R.mipmap.ic_launcher
        val file = intent.extras?.getInt(KEY_FILE) ?: 0 //TODO ?:

        tv_title.text = title
        tv_album.text = album
        tv_artist.text = artist
        iv_cover.setImageResource(cover)



        btn_play.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                if (pause) {
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    pause = false
                } else {
                    mediaPlayer = MediaPlayer.create(applicationContext, file)
                    mediaPlayer.start()
                    btn_play.setImageResource(R.drawable.ic_pause_black_24dp)
                }
            } else {
                mediaPlayer.pause()
                pause = true
                btn_play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            }

        }
        btn_stop.setOnClickListener {
            if (mediaPlayer.isPlaying || pause.equals(true)) {
                pause = false
                mediaPlayer.apply {
                    stop()
                    reset()
                    release()
                }
            }
        }
        btn_next.setOnClickListener { }
        btn_prev.setOnClickListener { }
        btn_close.setOnClickListener { }

    }

    //bind to Serivice
    override fun onStart() {
        super.onStart()
        Intent(this, MusicPlayerService::class.java).also { intent ->
            startService(intent)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(connection)
            mBound = false
        }
    }

    companion object {
        private const val DEFAULT_TITLE = "DEFAULT TITLE"
        private const val DEFAULT_ALBUM = "DEFAULT ALBUM"
        private const val DEFAULT_ARTIST = "DEFAULT ARTIST"

        private const val KEY_TITLE = "title"
        private const val KEY_ALBUM = "album"
        private const val KEY_ARTIST = "artist"
        private const val KEY_COVER = "cover"
        private const val KEY_FILE = "file"

        fun createIntent(activity: Activity, title: String, album: String, artist: String, cover: Int, file: Int) =
                Intent(activity, SongActivity::class.java).apply {
                    putExtra(KEY_TITLE, title)
                    putExtra(KEY_ALBUM, album)
                    putExtra(KEY_ARTIST, artist)
                    putExtra(KEY_COVER, cover)
                    putExtra(KEY_FILE, file)
                }
    }
}
