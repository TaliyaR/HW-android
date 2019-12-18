package com.itis.template

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var playIntent: Intent? = null

    private lateinit var musicPlayerService: MusicPlayerService

    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService()
        mBound = true

        rv_songs.adapter = SongsAdapter(SongRepository.getList()) { Song ->
            navigateToSong(Song)
        }
    }

    private fun navigateToSong(song: Song) {
        SongRepository.setCurrentIndex(song)
        startActivity(SongActivity.createIntent(this, song))
    }

    private fun bindService() {
        playIntent = Intent(this, MusicPlayerService::class.java)
        bindService(playIntent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(playIntent)
        unbindService(connection)
        mBound = false
    }


    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.LocalBinder
            musicPlayerService = binder.getService()
            mBound = true
            Toast.makeText(this@MainActivity, "onServiceConnected", Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
            Log.d("TAG", "onServiceDisconnected")
            Toast.makeText(this@MainActivity, "onServiceDisconnected", Toast.LENGTH_SHORT).show()
        }
    }
}
