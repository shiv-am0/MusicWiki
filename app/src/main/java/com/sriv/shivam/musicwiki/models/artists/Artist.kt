package com.sriv.shivam.musicwiki.models.artists

data class Artist(
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)