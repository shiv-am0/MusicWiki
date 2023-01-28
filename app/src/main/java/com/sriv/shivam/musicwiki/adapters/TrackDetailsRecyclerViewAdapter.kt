package com.sriv.shivam.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.models.tracks.Track
import kotlinx.android.synthetic.main.item_genre_details_list.view.*

class TrackDetailsRecyclerViewAdapter: RecyclerView.Adapter<TrackDetailsRecyclerViewAdapter.TrackViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackDetailsRecyclerViewAdapter.TrackViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_genre_details_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            mainTv.text = item.name
            subTv.text = item.artist.name
        }
    }

    class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}