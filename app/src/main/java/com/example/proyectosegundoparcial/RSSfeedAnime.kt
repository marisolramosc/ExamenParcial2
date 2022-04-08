package com.example.proyectosegundoparcial


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException

var posAnime = 0
lateinit var linksTrailers : Array<String>

class Anime {
    var AnimeTitle2: String = ""
    var About: String = ""
    var Chapters: String = ""
    var Rating: String = ""
    var Photo: Int = 0
}

class RSSfeedAnime : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed_anime)

        val btnMenu = findViewById<Button>(R.id.btnMenu2)

        btnMenu.setOnClickListener(this)

        val recyclerView2: RecyclerView = findViewById(R.id.xmlRecyclerView2)

        val Animes = ArrayList<Anime>()
        val AnimeTitles2: Array<String> = getResources().getStringArray(R.array.AnimeTitle2)
        val About: Array<String> = getResources().getStringArray(R.array.About)
        val Chapters: Array<String> = getResources().getStringArray(R.array.Chapters)
        val Rating: Array<String> = getResources().getStringArray(R.array.Rating)
        val Photos: IntArray = intArrayOf(R.drawable.anim2, R.drawable.anime1, R.drawable.anime3, R.drawable.anime4, R.drawable.anime5, R.drawable.anime6, R.drawable.anime7, R.drawable.anime8, R.drawable.anime9, R.drawable.anime10, R.drawable.anime11, R.drawable.anime12, R.drawable.anime13, R.drawable.anime14, R.drawable.anime15, R.drawable.anime16, R.drawable.anime17, R.drawable.anime18, R.drawable.anime19, R.drawable.anime20)
        linksTrailers = getResources().getStringArray(R.array.linkTrailer)


        for((i, element) in AnimeTitles2.withIndex()){
            val anime = Anime()
            anime.AnimeTitle2 = element
            anime.About = About[i]
            anime.Chapters = Chapters[i]
            anime.Rating = Rating[i]
            anime.Photo = Photos[i]
            Animes.add(anime)
            Log.d(TAG, anime.AnimeTitle2)
        }

        val adapter: ApplicationsAdapterAnime = ApplicationsAdapterAnime(this, Animes)
        recyclerView2.adapter = adapter
        adapter.setOnItemClickListener(object : ApplicationsAdapterAnime.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@RSSfeedAnime,"position: $position", Toast.LENGTH_SHORT).show()
                posAnime = position
                Log.d(TAG, "pos: $posAnime")
                var YOUTUBE_VIDEO_ID_KEY = linksTrailers[posAnime]
                Log.d(TAG, "link1: $YOUTUBE_VIDEO_ID_KEY")
                startActivity(YouTubeStandalonePlayer.createVideoIntent(this@RSSfeedAnime, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID_KEY))
                Log.d(TAG, "link2: $YOUTUBE_VIDEO_ID_KEY")
            }
        })
        recyclerView2.layoutManager = LinearLayoutManager(this)
        Log.d(TAG, "onCreate DONE")
    }

override fun onClick(view: View) {
    val intent = when(view.id){
            R.id.btnMenu2 -> Intent(this, MainActivity::class.java)
            else -> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}