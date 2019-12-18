package com.itis.template

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity() {
    var pause = false
    var isPlaying = true
    private var mediaPlayer = MediaPlayer()
    private var playIntent: Intent? = null
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val title = intent?.extras?.getString(KEY_TITLE) ?: DEFAULT_TITLE
        val album = intent?.extras?.getString(KEY_ALBUM) ?: DEFAULT_ALBUM
        val artist = intent?.extras?.getString(KEY_ARTIST) ?: DEFAULT_ARTIST
        val cover = intent?.extras?.getInt(KEY_COVER) ?: R.mipmap.ic_launcher
        var file = intent.extras?.getInt(KEY_FILE) ?: 0

        tv_title.text = title
        tv_album.text = album
        tv_artist.text = artist
        iv_cover.setImageResource(cover)

        playMusic(file)

        btn_play_pause.setOnClickListener {
            if (!isPlaying) {
                if (pause) {
                    startService(Intent(this, MusicPlayerService::class.java).apply { action = Action.ACTION_RESUME })
                    pause = false
                    isPlaying = true
                    btn_play_pause.setImageResource(R.drawable.ic_pause_black_24dp)
                } else {
//                    startService(Intent(this, MusicPlayerService::class.java).apply { action = Action.CREATE_PLAY })
                    playMusic(file)
                    isPlaying = true
                    btn_play_pause.setImageResource(R.drawable.ic_pause_black_24dp)
                }
            } else {
                startService(Intent(this, MusicPlayerService::class.java).apply { action = Action.ACTION_PAUSE })
                pause = true
                isPlaying = false
                btn_play_pause.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            }
        }

        btn_stop.setOnClickListener {
            if (isPlaying) {
                startService(Intent(this, MusicPlayerService::class.java).apply { action = Action.ACTION_STOP })
                pause = false
                isPlaying = false
                btn_play_pause.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            } else {
                if (pause) {
                    startService(Intent(this, MusicPlayerService::class.java).apply { action = Action.ACTION_STOP })
                    pause = false
                }
            }
        }

        btn_next.setOnClickListener {
            setSongViews(SongRepository.getNext())
            startService(Intent(this, MusicPlayerService::class.java).apply {
                action = Action.ACTION_NEXT
            })
            file = SongRepository.getCurrent().file
        }

        btn_prev.setOnClickListener {
            setSongViews(SongRepository.getPrevious())
            startService(Intent(this, MusicPlayerService::class.java).apply {
                action = Action.ACTION_PREV
            })
            file = SongRepository.getCurrent().file
        }

        btn_close.setOnClickListener {
            onPause()
        }

    }

    private fun setSongViews(song: Song) {
        tv_title.text = song.title
        tv_album.text = song.album
        tv_artist.text = song.artist
        iv_cover.setImageResource(song.cover)
    }


    fun playMusic(file: Int) {
        stopService(intent)
        startService(Intent(this, MusicPlayerService::class.java).apply {
            putExtra("song", file)
            action = Action.CREATE_PLAY
            isPlaying = true
        })
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

        fun createIntent(activity: Activity, song: Song) =
                Intent(activity, SongActivity::class.java).apply {
                    putExtra(KEY_TITLE, song.title)
                    putExtra(KEY_ALBUM, song.album)
                    putExtra(KEY_ARTIST, song.artist)
                    putExtra(KEY_COVER, song.cover)
                    putExtra(KEY_FILE, song.file)
                }
    }
}
