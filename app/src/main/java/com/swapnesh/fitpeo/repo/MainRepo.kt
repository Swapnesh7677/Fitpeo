package com.swapnesh.fitpeo.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.swapnesh.fitpeo.model.ImageResponse
import com.swapnesh.fitpeo.retrofit.JfClient
import com.swapnesh.fitpeo.retrofit.JfServer
import com.swapnesh.fitpeo.utils.Logger
import com.swapnesh.fitpeo.utils.Constants.Companion.API_ERROR_TAG
import com.swapnesh.fitpeo.utils.Constants.Companion.API_INVALID_RESPONSE_ERROR
import com.swapnesh.fitpeo.utils.Constants.Companion.API_NULL_RESPONSE_ERROR
import com.swapnesh.fitpeo.utils.Constants.Companion.API_URL_TAG
import com.swapnesh.fitpeo.utils.VRC


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object MainRepo {
    private var jfClient: JfClient = JfServer.getJfServer().create(JfClient::class.java)


    fun getImages(ml_restaurant: MutableLiveData<ImageResponse>): MutableLiveData<ImageResponse> {
        val apiName = "search/repositories"
        val params = HashMap<String, String>()


        val req: Call<ImageResponse> = jfClient.getImages()
        Logger.logApi(
            API_URL_TAG,
            apiName,
            VRC(req.request().url.toString(),params).getRequestedUrl()
        )

        req.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {

                    if (response.body() != null) {
                        val  gitResponse = response.body()!!
                        Log.d("responce",gitResponse.toString())
                        ml_restaurant.value = gitResponse
                    } else {

                        Logger.logApi(API_ERROR_TAG, apiName, API_NULL_RESPONSE_ERROR)
                        ml_restaurant.value = null
                    }

                } else {

                    Logger.logApi(API_ERROR_TAG, apiName, API_INVALID_RESPONSE_ERROR)
                    ml_restaurant.value = null
                }
            }


            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                t.printStackTrace()
                Logger.logApi(API_ERROR_TAG, apiName, t.localizedMessage)
                ml_restaurant.value =null
            }

        })
        return ml_restaurant
    }



}