package com.swapnesh.fitpeo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.swapnesh.fitpeo.databinding.ActivityDetailsBinding
import com.swapnesh.fitpeo.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity(){
    private  lateinit var  binding: ActivityDetailsBinding
    var title:String?=null
    var image:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle != null){
            title = bundle.getString("title")
            image = bundle.getString("image")

        }

        Picasso.get().load(image).into(binding.imgview);
        binding.tvDesc.text = title

    }
}