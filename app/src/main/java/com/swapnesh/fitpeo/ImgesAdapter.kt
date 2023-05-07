package com.swapnesh.fitpeo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.swapnesh.fitpeo.databinding.ItemRestBinding
import com.swapnesh.fitpeo.interfcae.ItemClickedListener
import com.swapnesh.fitpeo.model.ImageResponse

class ImgesAdapter(
    val imagList: ArrayList<ImageResponse.ImageResponseItem>,
    var param: ItemClickedListener
) :
    RecyclerView.Adapter<ImgesAdapter.ViewMaker>() {
    var ctx: Context? = null
    var listener: ItemClickedListener? = null
    inner class ViewMaker(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRestBinding.bind(itemView)
        fun bind() {

            val imag = imagList[adapterPosition]
            try {
                if(!imag.url.isEmpty()){
                    Picasso.get().load(imag.url).into(binding.imgview);
                }
            } catch (e: Exception) {
            }

            binding.imgview.setOnClickListener {
                listener!!.ItemClicked(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMaker {
        ctx = parent.context
        return ViewMaker(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rest, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewMaker, position: Int) {
        listener = param
        holder.bind()


    }

    override fun getItemCount(): Int = imagList.size
}