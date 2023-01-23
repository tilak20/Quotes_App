package com.example.quotesapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin10.Screen.shared_screen.controller.DataBase
import com.example.quotesapp.MainActivity
import com.example.quotesapp.ModelData.QuotesMD
import com.example.quotesapp.Quotes_Show
import com.example.quotesapp.R

class Category_Adapter(val activity : MainActivity, val list1: ArrayList<QuotesMD>) : RecyclerView.Adapter<Category_Adapter.ViewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val view = LayoutInflater.from(activity).inflate(R.layout.quotes_category,parent,false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.QuotesCategoryTxt.setText(list1[position].CName)

        holder.QuotesCategoryBtn.setOnClickListener {
                val intent = Intent(activity, Quotes_Show::class.java)
                intent.putExtra("id", list1[position].Id)
                intent.putExtra("Cname", list1[position].CName)
                activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list1.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var QuotesCategoryTxt = itemView.findViewById<TextView>(R.id.QuotesCategoryTxt)
        var QuotesCategoryBtn = itemView.findViewById<CardView>(R.id.QuotesCategoryBtn)
    }

}