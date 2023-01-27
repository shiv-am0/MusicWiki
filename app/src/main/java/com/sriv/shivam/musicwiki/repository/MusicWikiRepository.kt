package com.sriv.shivam.musicwiki.repository

import com.sriv.shivam.musicwiki.api.RetrofitInstance

class MusicWikiRepository {
    suspend fun getTopTags() = RetrofitInstance.api.getTopTags()

    suspend fun getTagDetails(tagName: String) = RetrofitInstance.api.getTagDetails(tagName)

    suspend fun getTopAlbums(tagName: String) = RetrofitInstance.api.getTopAlbums(tagName)

    suspend fun getTopArtists(tagName: String) = RetrofitInstance.api.getTopArtists(tagName)
}