package com.sriv.shivam.musicwiki.models.artists

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)