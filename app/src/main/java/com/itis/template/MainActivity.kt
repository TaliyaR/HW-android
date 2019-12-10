package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: SongsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SongsAdapter(SongRepository.getList()) { Song ->
            navigateToSong(Song)
        }
        rv_songs.adapter = adapter
    }

    private fun navigateToSong(song: Song) {
        startActivity(SongActivity.createIntent(
                this, song.title, song.album, song.artist, song.cover, song.file))

    }

    companion object {
        fun getDataSource(): List<Song> = arrayListOf(
                Song("Let It Snow", "Frank Sinatra", "Christmas Songs", R.drawable.ph1, R.raw.let_it_snow)
        )
    }
}

