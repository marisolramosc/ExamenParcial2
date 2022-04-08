package com.example.proyectosegundoparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAnime = findViewById<Button>(R.id.btnAnime)
        val btnOpening = findViewById<Button>(R.id.btnOpenings)

        btnAnime.setOnClickListener(this)
        btnOpening.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.btnAnime -> Intent(this, RSSfeedAnime::class.java)
            R.id.btnOpenings -> Intent(this, RSSfeedOpenings::class.java)
            else -> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}
