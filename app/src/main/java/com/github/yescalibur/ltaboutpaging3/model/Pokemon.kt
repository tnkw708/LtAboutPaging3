package com.github.yescalibur.ltaboutpaging3.model

import com.squareup.moshi.Json

data class Pokemon(
    val number: Int = 0,
    val name: String = "",
    val url: String = "",
) {
//    val number: Int
//        get() {
//            val urlParts = url.split("/")
//            return Integer.parseInt(urlParts[urlParts.size - 1])
//        }
}

data class MarsPhoto(
    val id: String,
    @Json(name = "img_src") val imgUrl: String,
)