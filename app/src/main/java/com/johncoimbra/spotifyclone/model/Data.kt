package com.johncoimbra.spotifyclone.model

class Category(
    var title: String = "",
    var albums: MutableList<Album> = ArrayList()
)

class Album(
    var album: Int = 0
)