package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demoapp.Posts.PostActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_post_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtIrFeed.setOnClickListener{view ->
            var intent=Intent(this,PostActivity::class.java)
            startActivity(intent)
        }


        callService();
    }


    private fun callService() {
        val service = Repository.RetrofitRepository.getService()

        CoroutineScope(Dispatchers.IO).launch {
            val response =  service.getProfile()

            withContext(Dispatchers.Main) {
                try {
                    if(response.isSuccessful) {

                        val user : UserResponse?  = response.body()
                        if( user != null) {

                            profile_years.text=user.age.toString()
                            profile_email.text=user.email.toString()
                            profile_location.text=user.location.toString()
                            profile_occupation.text=user.occupation.toString()
                            profile_posts.text=user.social.posts.toString()
                            profile_shares.text=user.social.shares.toString()
                            profile_friends.text=user.social.friends.toString()
                            profile_fullname.text = user.name.toString().plus(" ").plus(user.lastname.toString())
                            profile_likes.text = user.social.likes.toString()
                            Picasso.get().load(user.image).into(profile_image)
                        }
                    }else{
                        Toast.makeText(this@MainActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e : HttpException) {
                    Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}