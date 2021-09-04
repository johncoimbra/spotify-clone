package com.johncoimbra.spotifyclone.model

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName("titulo")
    var title: String = "",
    @SerializedName("albuns")
    var albums: List<Album> = arrayListOf()
)

data class Album(
    @SerializedName("url_imagem")
    var album: String = ""
)

data class Categories(@SerializedName("categoria")
    val categories: List<Category>
)