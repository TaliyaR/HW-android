package com.itis.template

object SongRepository {

    private val songsList: ArrayList<Song> = arrayListOf(
            Song("Let It Snow", "Frank Sinatra", "Christmas Songs", R.drawable.ph1, R.raw.let_it_snow),
            Song("We Wish You a Merry Christmas", "Enya", "Christmas Songs", R.drawable.ph2, R.raw.we_wish_you_a_merry_christmas),
            Song("Happy New Year", "ABBA", "Christmas Songs", R.drawable.ph3, R.raw.abba_happy_new_year),
            Song("Jingle Bells", "Frank Sinatra & Bing Crosby", "Christmas Songs", R.drawable.ph4, R.raw.frank_sinatra),
            Song("Let It Go", "Pentatonix", "Christmas Songs", R.drawable.ph5, R.raw.coldplay),
            Song("All I Want For Christmas Is You", "Mariah Carey", "Christmas Songs", R.drawable.ph6, R.raw.mariah_carey)
    )

    private val maxIndex = songsList.size - 1
    private var currentItemIndex = 0

    fun setCurrentIndex(song: Song) {
        currentItemIndex = songsList.indexOf(song)
    }

    fun getList() = songsList

    fun getNext(): Song {
        if (currentItemIndex == maxIndex) {
            currentItemIndex = 0
        } else {
            currentItemIndex++
        }
        return getCurrent()
    }

    fun getCurrent(): Song {
        return songsList.get(currentItemIndex)
    }

    fun getPrevious(): Song {
        if (currentItemIndex == 0) {
            currentItemIndex = maxIndex
        } else {
            currentItemIndex--
        }
        return getCurrent()
    }
}
