package com.example.appreceptora

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private val ID_CANAL = "ejemplo_canal_01"
    private val idNotificacion = 101

    private lateinit var tvRecibirTexto:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRecibirTexto = findViewById(R.id.tv_recibir)

        if (intent?.action == Intent.ACTION_SEND && intent.type.equals("text/plain")) {

            handleSendText(intent)
        }
    }

    private fun handleSendText(intent: Intent){
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            tvRecibirTexto.text = it
        }
    }

}