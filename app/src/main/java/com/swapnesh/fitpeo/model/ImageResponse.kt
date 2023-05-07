package com.swapnesh.fitpeo.model
import androidx.annotation.Keep

class ImageResponse : ArrayList<ImageResponse.ImageResponseItem>(){
    @Keep
    data class ImageResponseItem(
        val albumId: Int,
        val id: Int,
        val thumbnailUrl: String,
        val title: String,
        val url: String
    )
}