package com.example.demoapp.Posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.Adapter.PostAdapter
import com.example.demoapp.PostResponse
import com.example.demoapp.R
import com.example.demoapp.Repository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.item_post_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PostActivity : AppCompatActivity(),PostAdapter.PostHolder.OnAdapterListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter:PostAdapter
    val detailPost :PostDetailsActivity= PostDetailsActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post)
        adapter= PostAdapter(ArrayList(),this)
        linearLayoutManager= LinearLayoutManager(this)
        postRecyclerView.layoutManager=linearLayoutManager
        postRecyclerView.adapter=adapter
        CallService();
    }


  private fun CallService(){
        val service=
            Repository.RetrofitRepository.getService()

        GlobalScope.launch(Dispatchers.IO){
            val response=service.getPosts()

            withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        val posts:List<PostResponse>?=response.body()
                        if(posts!=null)updateInfo(posts)
                    }else{
                        Toast.makeText(this@PostActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e:HttpException){
                    Toast.makeText(this@PostActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateInfo(list:List<PostResponse>){
        adapter.updateList(list)
    }

    override fun onItemClickListener(item: PostResponse) {
        Toast.makeText(this,"Click item ${item.username}",Toast.LENGTH_LONG).show()
        val postString : String = Gson().toJson(item, PostResponse::class.java)

        val post : PostResponse = Gson().fromJson(postString, PostResponse::class.java)
        detailPost.getData(post.comment)
    }

}