package com.example.demoapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.PostResponse
import com.example.demoapp.Posts.PostDetailsActivity
import com.example.demoapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_list.view.*

class PostAdapter(private var data:List<PostResponse>, private val listener:PostHolder.OnAdapterListener) : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.item_post_list, false)
        return PostHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: PostAdapter.PostHolder, position: Int) {
        val post:PostResponse=this.data[position]
        holder.itemView.tv_username.text=post.username
        holder.itemView.tv_description.text=post.body
        holder.itemView.tv_likes.text =post.likes.toString()

        if(!post.user_image.isBlank()){
            Picasso.get().load(post.user_image).into(holder.itemView.iv_profile)
        }
        if(!post.image.isBlank()) {
            Picasso.get()
                .load(post.image)
                .into(holder.itemView.iv_list_posts)
        }

        holder.itemView.btn_coment.setOnClickListener { listener.onItemClickListener(post) }

    }

    fun updateList(postList:List<PostResponse>){
        this.data=postList
        this.notifyDataSetChanged()
    }

    class PostHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        init {
            v.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }
        interface OnAdapterListener {
            fun onItemClickListener( item: PostResponse)
        }

    }
}