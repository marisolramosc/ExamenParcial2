package com.example.proyectosegundoparcial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationsAdapterAnime(context: Context, feedEntries: ArrayList<Anime>): RecyclerView.Adapter<ApplicationsAdapterAnime.ViewHolder>() {

    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<Anime>? = null
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    init {
        localContext = context
        localFeedEntries = feedEntries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsAdapterAnime.ViewHolder{
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View = layoutInflater.inflate(R.layout.activity_act1_animes, parent, false)
        return ViewHolder(view, mListener)
    }


    //Asignar valores a las filas cuando son reintroducidasa a la pantalla basadas en la posición que tienen en la vista
    override fun onBindViewHolder(holder: ApplicationsAdapterAnime.ViewHolder, position: Int) {
        val currentFeedEntry: Anime = localFeedEntries!![position]
        holder.textAnimeTitle2.text = currentFeedEntry.AnimeTitle2
        holder.textAbout.text = currentFeedEntry.About
        holder.textChapters.text = currentFeedEntry.Chapters
        holder.Rating.rating = currentFeedEntry.Rating.toFloat()
        holder.Image.setImageDrawable(holder.Image.context.getDrawable(currentFeedEntry.Photo))
    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?: 0
    }

    class ViewHolder(v: View, listener: onItemClickListener): RecyclerView.ViewHolder(v) {

        val textAnimeTitle2: TextView = v.findViewById(R.id.textViewAnimeTitle2)
        val textAbout: TextView = v.findViewById(R.id.textViewSinopsis)
        val textChapters: TextView = v.findViewById(R.id.textViewCapitulos)
        val Rating: RatingBar = v.findViewById(R.id.ratingBar)
        val Image: ImageView = v.findViewById(R.id.imageViewVideo)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}