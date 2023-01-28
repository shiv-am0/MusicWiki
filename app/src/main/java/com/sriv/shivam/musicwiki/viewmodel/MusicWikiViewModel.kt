package com.sriv.shivam.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sriv.shivam.musicwiki.models.albums.Albums
import com.sriv.shivam.musicwiki.models.artists.ArtistsInfo
import com.sriv.shivam.musicwiki.models.genres.Genres
import com.sriv.shivam.musicwiki.models.taginfo.TagInfo
import com.sriv.shivam.musicwiki.models.tracks.TracksInfo
import com.sriv.shivam.musicwiki.repository.MusicWikiRepository
import com.sriv.shivam.musicwiki.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MusicWikiViewModel(
    val musicWikiRepository: MusicWikiRepository
): ViewModel() {

    val tagsLiveData: MutableLiveData<Resource<Genres>> = MutableLiveData()
    val tagDetailsLiveData: MutableLiveData<Resource<TagInfo>> = MutableLiveData()
    val tagTopAlbumsLiveData: MutableLiveData<Resource<Albums>> = MutableLiveData()
    val tagTopArtistsLiveData: MutableLiveData<Resource<ArtistsInfo>> = MutableLiveData()
    val tagTopTracksLiveData: MutableLiveData<Resource<TracksInfo>> = MutableLiveData()

    init {
        getTopTags()
    }

    fun getTopTags() = viewModelScope.launch {
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

    fun getTagDetails(tagName: String) = viewModelScope.launch {
        tagDetailsLiveData.postValue(Resource.Loading())
        val tagDetailsResponse = musicWikiRepository.getTagDetails(tagName)
        tagDetailsLiveData.postValue(handleTagDetailsResponse(tagDetailsResponse))
    }

    private fun handleTagDetailsResponse(tagDetailsResponse: Response<TagInfo>): Resource<TagInfo> {
        if(tagDetailsResponse.isSuccessful) {
            tagDetailsResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(tagDetailsResponse.message())
    }

    fun getTopAlbums(tagName: String) = viewModelScope.launch {
        tagTopAlbumsLiveData.postValue(Resource.Loading())
        val topAlbumsResponse = musicWikiRepository.getTopAlbums(tagName)
        tagTopAlbumsLiveData.postValue(handleTopAlbumsResponse(topAlbumsResponse))
    }

    private fun handleTopAlbumsResponse(tagDetailsResponse: Response<Albums>): Resource<Albums> {
        if(tagDetailsResponse.isSuccessful) {
            tagDetailsResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(tagDetailsResponse.message())
    }

    fun getTopArtists(tagName: String) = viewModelScope.launch {
        tagTopArtistsLiveData.postValue(Resource.Loading())
        val topArtistsResponse = musicWikiRepository.getTopArtists(tagName)
        tagTopArtistsLiveData.postValue(handleTopArtistsResponse(topArtistsResponse))
    }

    private fun handleTopArtistsResponse(artistsDetailsResponse: Response<ArtistsInfo>): Resource<ArtistsInfo> {
        if(artistsDetailsResponse.isSuccessful) {
            artistsDetailsResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(artistsDetailsResponse.message())
    }

    fun getTopTracks(tagName: String) = viewModelScope.launch {
        tagTopTracksLiveData.postValue(Resource.Loading())
        val topTracksResponse = musicWikiRepository.getTopTracks(tagName)
        tagTopTracksLiveData.postValue(handleTopTracksResponse(topTracksResponse))
    }

    private fun handleTopTracksResponse(artistsDetailsResponse: Response<TracksInfo>): Resource<TracksInfo> {
        if(artistsDetailsResponse.isSuccessful) {
            artistsDetailsResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(artistsDetailsResponse.message())
    }
}