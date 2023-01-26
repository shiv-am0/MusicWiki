package com.sriv.shivam.musicwiki.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sriv.shivam.musicwiki.MainActivity
import com.sriv.shivam.musicwiki.R
import com.sriv.shivam.musicwiki.adapters.GenreRecyclerViewAdapter
import com.sriv.shivam.musicwiki.utils.Resource
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var viewModel: MusicWikiViewModel
    lateinit var genreAdapter: GenreRecyclerViewAdapter

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        /*
        We will observe tag LiveData to get updated with everytime we fetch
        top tags from local/remote database using api. The ui is updated according to
        the response.
         */
        viewModel.tagsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
//                    hideProgressBar()
                    it.data?.let { tagResponse ->
                        genreAdapter.differ.submitList(tagResponse.toptags.tag)
                    }
                }
                is Resource.Error -> {
//                    hideProgressBar()
                    it.message?.let {
                        Log.e(TAG, "An error occurred")
                    }
                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        })
    }

//    private fun hideProgressBar() {
//        paginationProgressBar.visibility = View.INVISIBLE
//    }
//
//    private fun showProgressBar() {
//        paginationProgressBar.visibility = View.VISIBLE
//    }

    private fun setupRecyclerView() {
        genreAdapter = GenreRecyclerViewAdapter()
        genreRecyclerView.apply {
            adapter = genreAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
    }
}