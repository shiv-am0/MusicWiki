package com.sriv.shivam.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.models.albums.Album
import kotlinx.android.synthetic.main.item_genre_details_list.view.*

class AlbumDetailsRecyclerViewAdapter: RecyclerView.Adapter<AlbumDetailsRecyclerViewAdapter.AlbumDetailsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
        return AlbumDetailsRecyclerViewAdapter.AlbumDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_genre_details_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            mainTv.text = item.name
            subTv.text = item.artist.name
            setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class AlbumDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    private var onItemClickListener: ((Album) -> Unit)? = null

    fun setOnItemClickListener(listener: (Album) -> Unit) {
        onItemClickListener = listener
    }
}