package com.sriv.shivam.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sriv.shivam.musicwiki.ui.AlbumsViewFragment
import com.sriv.shivam.musicwiki.ui.ArtistsViewFragment
import com.sriv.shivam.musicwiki.ui.TracksViewFragment

class PagerAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AlbumsViewFragment()
            1 -> ArtistsViewFragment()
            2 -> TracksViewFragment()
            else -> AlbumsViewFragment()
        }
    }
}