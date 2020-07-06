package com.example.demoapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.PostResponse
import com.example.demoapp.Posts.Coments
import com.example.demoapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_details_list.view.*
import kotlinx.android.synthetic.main.item_post_list.view.*
import kotlin.math.sign

class DetailsPostAdapter(private var data:List<Coments>, private val listener: DetailsPostAdapter.PostHolder.OnAdapterListener) : RecyclerView.Adapter<DetailsPostAdapter.PostHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.activity_post_details, false)
        return PostHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post:Coments=this.data[position]
        holder.itemView.txtDetailUserName.text=post.username
        if(!post.user_image.isBlank()){
            Picasso.get().load(post.user_image).into(holder.itemView.iv_details_profile)
        }

    }
    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(postList:List<Coments>){
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