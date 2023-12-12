package com.example.appreceptora

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class NotifyReciber : BroadcastReceiver() {

    private val ID_CANAL = "ejemplo_canal_01"
    private val idNotificacion = 101

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            intent?.let {
                if (it.action == "com.appemisora.MOSTRAR_NOTIFICACION") {

                    lanzarNotificacion(context, it, ID_CANAL)
                }
            }
        }
    }

    private fun lanzarNotificacion(context: Context, intent: Intent, idCanal: String) {
        // Extraer el título y el contenido de la notificación del Intent
        val titulo = intent.getStringExtra("titulo") ?: "Título por defecto"
        val contenido = intent.getStringExtra("texto") ?: "Contenido por defecto"

        //Intent para abrir la app
        val intentApp = Intent(context, MainActivity::class.java).apply {
            putExtra("texto", contenido)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // Pending intent en el que metemos el intent anterior
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intentApp,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Crear el canal de notificación para Android 8.0 y superiores
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nombre = "Nombre del canal"
            val descripcion = "Descripción del canal"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(idCanal, nombre, importancia).apply {
                description = descripcion
            }
            // Registrar el canal en el sistema
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)

            // Construir la notificación
            val builder = NotificationCompat.Builder(context, idCanal)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // En el contenido de la notificacion le metemos el pending intent
                .setContentIntent(pendingIntent)
                .build()

            // Lanzar la notificación
            notificationManager.notify(idNotificacion, builder)
        }


    }

}

