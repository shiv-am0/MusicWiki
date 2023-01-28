package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel

class AlbumDetailsFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    private val TAG = "AlbumDetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = (activity as MainActivity).viewModel
//
//        viewModel.tagTopAlbumsLiveData.observe(viewLifecycleOwner, Observer {
//            when(it) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    it.data?.let { albumsResponse ->
////                        albumDetails.text =
//                        Log.d(TAG, "Album Details loaded successfully")
//                    }
//                }
//                is Resource.Error -> {
//                    hideProgressBar()
//                    it.message?.let {
//                        Log.e(TAG, "An error occurred")
//                    }
//                }
//                is Resource.Loading -> {
//                    Log.d(TAG, "Tag Details loading")
//                    showProgressBar()
//                }
//            }
//        })
//    }
//
//    private fun hideProgressBar() {
//        paginationProgressBarAlbumsView.visibility = View.INVISIBLE
//    }
//
//    private fun showProgressBar() {
//        paginationProgressBarAlbumsView.visibility = View.VISIBLE
//    }
}