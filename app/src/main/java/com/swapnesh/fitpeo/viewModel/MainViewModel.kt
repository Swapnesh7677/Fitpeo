package com.swapnesh.fitpeo.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.swapnesh.fitpeo.model.ImageResponse
import com.swapnesh.fitpeo.repo.MainRepo
import com.swapnesh.gitsearch.viewmodels.BaseViewModel


class MainViewModel(application: Application) : BaseViewModel(application) {

    private val mlrepos: MutableLiveData<ImageResponse> = MutableLiveData()

    fun getImages(): MutableLiveData<ImageResponse> {
        return MainRepo.getImages(mlrepos)
    }




}