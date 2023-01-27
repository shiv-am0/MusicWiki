package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sriv.shivam.musicwiki.MainActivity
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.adapters.PagerAdapter
import com.sriv.shivam.musicwiki.models.albums.Albums
import com.sriv.shivam.musicwiki.utils.Resource
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import kotlinx.android.synthetic.main.fragment_genre_details.*
import kotlinx.android.synthetic.main.fragment_pager.*

class GenreDetailsFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    private var tagName: String? = null
    lateinit var albums: Albums

    private val TAG = "GenreDetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setFragmentResultListener("selectedTag") { key, bundle ->
            tagName = bundle.getString("data")
            Log.d(TAG, "$tagName in Genre Details Fragment")
            tagTitle.text = tagName
            Log.d(TAG, "getDetails function called")
            viewModel.getTagDetails(tagTitle.text.toString())
            viewModel.getTopAlbums(tagTitle.text.toString())
            viewModel.getTopArtists(tagTitle.text.toString())
        }

        /*
        We will observe tagDetails LiveData to get updated with everytime we fetch
        tag details from local/remote database using api. The ui is updated according to
        the response.
         */
        viewModel.tagDetailsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { tagResponse ->
                        tagSummary.text = tagResponse.tag.wiki.summary
                        Log.d(TAG, "Tag Details loaded successfully")
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

        viewDetailsBtn.setOnClickListener {
            setFragmentResult("selectedTag", bundleOf("data" to tagTitle.text.toString()))
            findNavController().navigate(R.id.action_genreDetailsFragment_to_pagerFragment)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBarGenreDetails.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarGenreDetails.visibility = View.VISIBLE
    }

}