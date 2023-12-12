package com.example.appreceptora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var texto = findViewById<TextView>(R.id.tv_recibir)

        var contenido =intent.getStringExtra("texto")

        texto.setText(contenido)
    }
}