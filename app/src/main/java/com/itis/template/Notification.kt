package com.itis.template

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

object Notification {


    fun sendNotification(context: Context, song: Song) {

        val getIntent =
                { action: String -> Intent(context, MusicPlayerService::class.java).setAction(action) }
        val getPendingIntent =
                { action: String -> PendingIntent.getService(context, 0, getIntent(action), 0) }

        val builder = NotificationCompat.Builder(context, "player channel").apply {
            setContentTitle(song.title)
            setSmallIcon(song.cover)
            setContentText(song.artist)

            //уведомление на экране блокировки
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            addAction(R.drawable.ic_fast_forward_black_24dp, "Previous", getPendingIntent(Action.ACTION_PREV))

            addAction(R.drawable.ic_fast_rewind_black_24dp, "Next", getPendingIntent(Action.ACTION_NEXT))
        }

        val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}
