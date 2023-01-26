package com.sriv.shivam.musicwiki.repository

import com.sriv.shivam.musicwiki.api.RetrofitInstance

class MusicWikiRepository {
    suspend fun getTopTags() = RetrofitInstance.api.getTopTags()
}