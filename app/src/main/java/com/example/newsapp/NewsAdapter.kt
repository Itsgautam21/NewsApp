package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener: RVClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = ArrayList<Model>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.image)
        val titleView : TextView = itemView.findViewById(R.id.headLines)
        val descView : TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_news, parent, false))
        view.itemView.setOnClickListener {
            listener.onItemClickListener(list[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        Glide.with(holder.imageView.context).load(model.image).into(holder.imageView)
        holder.titleView.text = model.title
        holder.descView.text = model.description
    }

    fun updateList(updateList : ArrayList<Model>) {
        list.clear()
        list.addAll(updateList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}
interface RVClickListener {
    fun onItemClickListener(model: Model)
}