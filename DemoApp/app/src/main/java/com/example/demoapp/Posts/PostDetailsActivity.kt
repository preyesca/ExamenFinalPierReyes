package com.example.demoapp.Posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.Adapter.DetailsPostAdapter
import com.example.demoapp.Adapter.PostAdapter
import com.example.demoapp.PostResponse
import com.example.demoapp.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.item_post_list.*

class PostDetailsActivity : AppCompatActivity(),DetailsPostAdapter.PostHolder.OnAdapterListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: DetailsPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        setContentView(R.layout.activity_post_details)
        adapter= DetailsPostAdapter(ArrayList(),this)
        linearLayoutManager= LinearLayoutManager(this)
        detailsPostRecyclerView.layoutManager=linearLayoutManager
        detailsPostRecyclerView.adapter=adapter

    }

    override fun onItemClickListener(item: PostResponse) {
        Toast.makeText(this,"Click item ${item.username}", Toast.LENGTH_LONG).show()

        btn_coment.setOnClickListener { view ->
            var intent= Intent(this,PostDetailsActivity::class.java)
            startActivity(intent)
        }
        val postString : String = Gson().toJson(item, PostResponse::class.java)

        Log.d("GSON Class to String", postString )
        /**
         * puedes enviar los extras a una pantalla de detalle
         */

        val post : PostResponse = Gson().fromJson(postString, PostResponse::class.java)

    }

    fun getData(coments:List<Coments>){
        adapter.updateList(coments)
    }
}