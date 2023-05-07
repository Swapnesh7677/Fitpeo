package com.swapnesh.fitpeo.retrofit
import com.swapnesh.fitpeo.model.ImageResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface JfClient {




    @GET("photos")
    fun getImages(): Call<ImageResponse>


}