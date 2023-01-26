package com.sriv.shivam.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sriv.shivam.musicwiki.models.genres.Genres
import com.sriv.shivam.musicwiki.models.genres.Tag
import com.sriv.shivam.musicwiki.repository.MusicWikiRepository
import com.sriv.shivam.musicwiki.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MusicWikiViewModel(
    val musicWikiRepository: MusicWikiRepository
): ViewModel() {

    val tagsLiveData: MutableLiveData<Resource<Genres>> = MutableLiveData()

    init {
        getTopTags()
    }

    private fun getTopTags() = viewModelScope.launch {
        tagsLiveData.postValue(Resource.Loading())
        val tagResponse = musicWikiRepository.getTopTags()
        tagsLiveData.postValue(handleTagResponse(tagResponse))
    }

    private fun handleTagResponse(tagResponse: Response<Genres>): Resource<Genres> {
        if(tagResponse.isSuccessful) {
            tagResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(tagResponse.message())
    }
}