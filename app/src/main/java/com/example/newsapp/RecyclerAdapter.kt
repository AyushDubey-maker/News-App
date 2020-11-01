package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<RecyclerViewHolder>() {
     private val items:ArrayList<News> = ArrayList()

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
          val viewHolder =RecyclerViewHolder(view)
                  view.setOnClickListener {
                      listener.onItemClicked(items[viewHolder.adapterPosition])

                  }

          return viewHolder
      }

      override fun getItemCount(): Int {
         return items.size
      }

      override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
          val currentItem=items[position]
          holder.titleView.text=currentItem.title
          holder.author.text=currentItem.author
          Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
      }
      fun updateNews(updateNews:ArrayList<News>){
          items.clear()
          items.addAll(updateNews)
          notifyDataSetChanged()
      }

  }
class RecyclerViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
   val titleView: TextView=itemView.findViewById(R.id.title)
    val image :ImageView=itemView.findViewById(R.id.imageNews)
    val author:TextView=itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}