package com.example.bisdiv_burgerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView

class FoodAdapter (private val foodList: ArrayList<Food>,private var onItemClicked: ((foodparam: Food)-> Unit)):
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent, false)
        return FoodViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = foodList[position]
//        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
        holder.textPrice.text = currentItem.price
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)
        val textPrice : TextView = itemView.findViewById(R.id.price)
        val MaterialButton : MaterialButton = itemView.findViewById(R.id.material_button)
    }

}