package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sriv.shivam.musicwiki.MainActivity
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.adapters.TrackDetailsRecyclerViewAdapter
import com.sriv.shivam.musicwiki.utils.Resource
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import kotlinx.android.synthetic.main.fragment_tracks_view.*

class TracksViewFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    private var tagName: String? = null
    lateinit var trackDetailsAdapter: TrackDetailsRecyclerViewAdapter

    private val TAG = "TracksViewFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tracks_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        setFragmentResultListener("selectedTag") { key, bundle ->
            tagName = bundle.getString("data")
            Log.d(TAG, "$tagName in Tracks View Fragment")
            tagName?.let {
                viewModel.getTopTracks(it)
            }
        }

        viewModel.tagTopTracksLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { tracksResponse ->
                        trackDetailsAdapter.differ.submitList(tracksResponse.tracks.track)
                        Log.d(TAG, "Track Details loaded successfully")
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Log.e(TAG, "An error occurred")
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "Track Details loading")
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBarTracks.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarTracks.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        trackDetailsAdapter = TrackDetailsRecyclerViewAdapter()
        tracksRecyclerView.apply {
            adapter = trackDetailsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}