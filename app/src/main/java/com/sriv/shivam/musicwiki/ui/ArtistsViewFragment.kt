package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sriv.shivam.musicwiki.MainActivity
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.adapters.ArtistsRecyclerViewAdapter
import com.sriv.shivam.musicwiki.utils.Resource
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import kotlinx.android.synthetic.main.fragment_artists_view.*
import kotlinx.android.synthetic.main.fragment_artists_view.tagTitle

class ArtistsViewFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    lateinit var artistsRecyclerViewAdapter: ArtistsRecyclerViewAdapter
    private var tagName: String? = null

    private val TAG = "ArtistsViewFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artists_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        setFragmentResultListener("selectedTag") { key, bundle ->
            tagName = bundle.getString("data")
            Log.d(TAG, "$tagName in Artists View Fragment")
            tagName?.let {
                viewModel.getTopArtists(it)
            }
        }

        viewModel.tagTopArtistsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { artistsResponse ->
                        artistsRecyclerViewAdapter.differ.submitList(artistsResponse.topartists.artist)
                        Log.d(TAG, "Artist Details loaded successfully")
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
        paginationProgressBarArtists.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarArtists.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        artistsRecyclerViewAdapter = ArtistsRecyclerViewAdapter()
        artistsRecyclerView.apply {
            adapter = artistsRecyclerViewAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}