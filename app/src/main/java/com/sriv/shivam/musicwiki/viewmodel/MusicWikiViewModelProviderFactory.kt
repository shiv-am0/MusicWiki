package com.sriv.shivam.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sriv.shivam.musicwiki.repository.MusicWikiRepository

class MusicWikiViewModelProviderFactory(
    val musicWikiRepository: MusicWikiRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MusicWikiViewModel(musicWikiRepository) as T
    }
}