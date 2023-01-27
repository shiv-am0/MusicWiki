package com.sriv.shivam.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.models.artists.Artist
import kotlinx.android.synthetic.main.item_genre_details_list.view.*

class ArtistsRecyclerViewAdapter: RecyclerView.Adapter<ArtistsRecyclerViewAdapter.ArtistViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistsRecyclerViewAdapter.ArtistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_genre_details_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            mainTv.text = item.name
            subTv.visibility = View.GONE
            setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ArtistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    private var onItemClickListener: ((Artist) -> Unit)? = null

    fun setOnItemClickListener(listener: (Artist) -> Unit) {
        onItemClickListener = listener
    }
}