package com.abdusatarov.picsum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdusatarov.picsum.api.ImageListItem
import com.bumptech.glide.Glide

class MainAdapter(private val data: List<ImageListItem>,private val listener:(x: ImageListItem) ->Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var author: TextView
        init {
            image = itemView.findViewById(R.id.image_preview)
            author = itemView.findViewById(R.id.image_author)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.author.text = item.author
        val url = "https://picsum.photos/id/${item.id}/200/300"
        Glide.with(holder.itemView).load(url).into(holder.image)
        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }
    }
}