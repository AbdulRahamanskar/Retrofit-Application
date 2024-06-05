package com.example.apiapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var mrecyclerview:RecyclerView
    lateinit var myAdapter: MyAdapter
    lateinit var mproductArrayList:ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mrecyclerview=findViewById(R.id.recyclerView)



        //https://dummyjson.com/products

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
        //gson comes here
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getProducts() //using retrofitBuilder we are getting data from API Interface

        //retrofit data call back works ctrl+shift+space key
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                //Api generates success then fetch data from api
                val responseBody=p1.body() //
                val productList=responseBody?.products!!

                myAdapter=MyAdapter(this@MainActivity, productList)
                mrecyclerview.adapter=myAdapter
                mrecyclerview.layoutManager=LinearLayoutManager(this@MainActivity)



                //listner functionally of rv in main Activity
                myAdapter.setOnItemClickListner(object:MyAdapter.onItemClickListner{
                    override fun onItemClick(position: Int) {
                        Toast.makeText(this@MainActivity,"Item Clicked $position",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this@MainActivity,ProductDetailActivity::class.java)
                        startActivity(intent)
                    }

                })





            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                //Fetching the  data API fails
                Log.d("Main Activity", "onFailure: ${p1.message}")
            }
        })
    }
}