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

//var YOUTUBE_VIDEO_ID_KEY = "vTDkNw9rbOE"
var posOp = 0
lateinit var linksOp : Array<String>

class Opening {
    var AnimeTitle: String = ""
    var Song: String = ""
    var Season: String = ""
    var Band: String = ""
}


class RSSfeedOpenings : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed_openings)

        val btnMenu = findViewById<Button>(R.id.btnMenu1)



        btnMenu.setOnClickListener(this)

        val recyclerView: RecyclerView = findViewById(R.id.xmlRecyclerView1)

        val Openings = ArrayList<Opening>()
        val AnimeTitles: Array<String> = getResources().getStringArray(R.array.AnimeTitle)
        val Songs: Array<String> = getResources().getStringArray(R.array.Song)
        val Seasons: Array<String> = getResources().getStringArray(R.array.Season)
        val Bands: Array<String> = getResources().getStringArray(R.array.Band)
        linksOp = getResources().getStringArray(R.array.linkOp)


            for((i, element) in AnimeTitles.withIndex()){
                val opening = Opening()
                opening.AnimeTitle = element
                opening.Song = Songs[i]
                opening.Season = Seasons[i]
                opening.Band = Bands[i]
                Openings.add(opening)
                Log.d(TAG, opening.Song)
            }


        val adapter: ApplicationsAdapterOpenings = ApplicationsAdapterOpenings(this, Openings)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ApplicationsAdapterOpenings.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@RSSfeedOpenings,"position: $position",Toast.LENGTH_SHORT).show()
                posOp = position
                Log.d(TAG, "pos: $posOp")
                var YOUTUBE_VIDEO_ID_KEY = linksOp[posOp]
                Log.d(TAG, "link1: $YOUTUBE_VIDEO_ID_KEY")
                startActivity(YouTubeStandalonePlayer.createVideoIntent(this@RSSfeedOpenings, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID_KEY))
                Log.d(TAG, "link2: $YOUTUBE_VIDEO_ID_KEY")
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)

        Log.d(TAG, "onCreate DONE")

    }


    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.btnMenu1 -> Intent(this, MainActivity::class.java)
            else -> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}
