package com.example.appreceptora

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotifyReciber : BroadcastReceiver() {

    private val ID_CANAL = "ejemplo_canal_01"
    private val idNotificacion = 101

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action == "com.appemisora.MOSTRAR_NOTIFICACION") {
                createNotificationChannel(context)
                handleSendText(it, context)
            }
        }
    }

    private fun handleSendText(intent: Intent, context: Context?) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
            val notification = NotificationCompat.Builder(context!!, ID_CANAL)
                .setContentTitle("Notificacion de Texto")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(idNotificacion, notification)
        }
    }

    private fun createNotificationChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CANAL_1"
            val descriptionText = "Descripción del canal"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(ID_CANAL, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
