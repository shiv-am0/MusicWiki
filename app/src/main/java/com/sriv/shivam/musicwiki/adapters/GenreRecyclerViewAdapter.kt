package com.sriv.shivam.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.models.genres.Tag
import kotlinx.android.synthetic.main.item_genre_list.view.*

class GenreRecyclerViewAdapter: RecyclerView.Adapter<GenreRecyclerViewAdapter.MusicWikiViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicWikiViewHolder {
        return MusicWikiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_genre_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicWikiViewHolder, position: Int) {
        val tag = differ.currentList[position]
        holder.itemView.apply {
            this.genreButton.text = tag.name
            setOnClickListener {
                onItemClickListener?.let { it(tag) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MusicWikiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    private var onItemClickListener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        onItemClickListener = listener
    }
}