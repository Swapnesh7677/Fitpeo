package com.swapnesh.fitpeo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapnesh.fitpeo.activity.DetailsActivity
import com.swapnesh.fitpeo.databinding.ActivityMainBinding
import com.swapnesh.fitpeo.interfcae.ItemClickedListener
import com.swapnesh.fitpeo.model.ImageResponse
import com.swapnesh.fitpeo.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVm: MainViewModel
    private var imgAdapter: ImgesAdapter? = null
    var layoutManager: LinearLayoutManager?=null
    private val imageList: ArrayList<ImageResponse.ImageResponseItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainVm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(MainViewModel::class.java)
        layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvImages.setHasFixedSize(true)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        binding.rvImages.layoutManager = layoutManager

        mainVm.getImages().observe(this@MainActivity) {
            if(it!=null){
                imageList.addAll(it)
                Log.d("data", it.size.toString())
                Log.d("imageList", imageList.size.toString())


                binding.rvImages.adapter = ImgesAdapter(imageList,object :ItemClickedListener{
                    override fun ItemClicked(position: Int) {
                       
                        val bundle = Bundle()
                        bundle.putString("title", imageList.get(position).title)
                        bundle.putString("image", imageList.get(position).url)
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }

                })

            }

        }
    }
}