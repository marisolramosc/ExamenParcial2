package com.example.proyectosegundoparcial


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException



class ApplicationsAdapterOpenings(context: Context, feedEntries: ArrayList<Opening>): RecyclerView.Adapter<ApplicationsAdapterOpenings.ViewHolder>() {

    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<Opening>? = null
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsAdapterOpenings.ViewHolder{
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View = layoutInflater.inflate(R.layout.activity_act2_openings, parent, false)
        return ViewHolder(view,mListener)
    }


    //Asignar valores a las filas cuando son reintroducidasa a la pantalla basadas en la posición que tienen en la vista
    override fun onBindViewHolder(holder: ApplicationsAdapterOpenings.ViewHolder, position: Int) {
        val currentFeedEntry: Opening = localFeedEntries!![position]
        holder.textAnimeTitle.text = currentFeedEntry.AnimeTitle
        holder.textNameOp.text = currentFeedEntry.Song
        holder.textTemporada.text = currentFeedEntry.Season
        holder.textBanda.text = currentFeedEntry.Band
    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?: 0
    }

    class ViewHolder(v: View, listener: onItemClickListener): RecyclerView.ViewHolder(v) {

        val textAnimeTitle: TextView = v.findViewById(R.id.textViewAnimeTitle)
        val textNameOp: TextView = v.findViewById(R.id.textViewNameOp)
        val textTemporada: TextView = v.findViewById(R.id.textViewTemporada)
        val textBanda: TextView = v.findViewById(R.id.textViewBanda)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}