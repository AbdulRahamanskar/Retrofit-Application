package com.example.apiapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.zip.Inflater

class MyAdapter(private val context:Activity, private val productArrayList:List<Product>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    //var for thar rv
    private lateinit var mListner:onItemClickListner
    //listner for RV
    interface onItemClickListner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(listner:onItemClickListner){
        mListner=listner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.item_design,parent,false)
        return MyViewHolder(itemView,mListner)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem=productArrayList[position]

        holder.title.text=currentItem.title
        holder.status.text=currentItem.availabilityStatus
        holder.price.text= currentItem.price.toString()
        holder.rating.text= currentItem.rating.toString()
        holder.discount.text= currentItem.discountPercentage.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.img)




    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }
    class MyViewHolder(itemView: View,listner:onItemClickListner):RecyclerView.ViewHolder(itemView) {

        var title:TextView
        var img:ShapeableImageView
        var status:TextView
        var price:TextView
        var rating:TextView
        var discount:TextView
        init {
            title=itemView.findViewById(R.id.tvproduct)
            img=itemView.findViewById(R.id.shapableimg)
            status=itemView.findViewById(R.id.tvproductstatus)
            price=itemView.findViewById(R.id.tvproductprice)
            discount=itemView.findViewById(R.id.tvproductdiscount)
            rating=itemView.findViewById(R.id.tvproductrating)



            //listners init

            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }



        }
    }
}