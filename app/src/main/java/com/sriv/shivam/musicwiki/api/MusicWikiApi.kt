package com.sriv.shivam.musicwiki.api

import com.sriv.shivam.musicwiki.models.genres.Genres
import com.sriv.shivam.musicwiki.models.taginfo.TagInfo
import com.sriv.shivam.musicwiki.utils.ApiKey.API_KEY
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicWikiApi {

    @POST("/2.0")
    suspend fun getTopTags(
        @Query("method")
        method: String = "tag.getTopTags",
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("format")
        format: String = "json"
    ):Response<Genres>

    @POST("/2.0")
    suspend fun getTagDetails(
        @Query("tag")
        tagName: String,
        @Query("method")
        method: String = "tag.getinfo",
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("format")
        format: String = "json"
    ):Response<TagInfo>
}