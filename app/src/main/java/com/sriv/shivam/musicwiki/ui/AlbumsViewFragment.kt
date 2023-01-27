package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sriv.shivam.musicwiki.MainActivity
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.adapters.AlbumDetailsRecyclerViewAdapter
import com.sriv.shivam.musicwiki.models.albums.Albums
import com.sriv.shivam.musicwiki.utils.Resource
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import kotlinx.android.synthetic.main.fragment_albums_view.*
import kotlinx.android.synthetic.main.fragment_genre_details.*
import kotlinx.android.synthetic.main.fragment_genre_details.tagTitle
import kotlinx.android.synthetic.main.fragment_home.*

class AlbumsViewFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    private var tagName: String? = null
    lateinit var albums: Albums
    lateinit var topAlbumsAdapter: AlbumDetailsRecyclerViewAdapter

    private val TAG = "AlbumsViewFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        setFragmentResultListener("selectedTag") { key, bundle ->
            tagName = bundle.getString("data")
            Log.d(TAG, "$tagName in Albums View Fragment")
            tagName?.let {
                viewModel.getTopAlbums(it)
            }
        }

        viewModel.tagTopAlbumsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { albumsResponse ->
                        topAlbumsAdapter.differ.submitList(albumsResponse.albums.album)
                        Log.d(TAG, "Album Details loaded successfully")
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Log.e(TAG, "An error occurred")
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "Tag Details loading")
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBarA.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarA.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        topAlbumsAdapter = AlbumDetailsRecyclerViewAdapter()
        albumsRecyclerView.apply {
            adapter = topAlbumsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}