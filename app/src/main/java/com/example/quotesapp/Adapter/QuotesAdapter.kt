package com.example.quotesapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.Full_Quotes
import com.example.quotesapp.ModelData.QuotesReadMD
import com.example.quotesapp.Quotes_Show
import com.example.quotesapp.R

class QuotesAdapter(val quotesShow: Quotes_Show,val list2: ArrayList<QuotesReadMD>) : RecyclerView.Adapter<QuotesAdapter.ViewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val view = LayoutInflater.from(quotesShow).inflate(R.layout.quotes,parent,false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.txtQuotes1.setText(list2[position].Body)

        holder.imgViewMore.setOnClickListener {
            val intent = Intent(quotesShow,Full_Quotes::class.java)

            intent.putExtra("id", list2[position].Id)
            intent.putExtra("Cname", list2[position].Cname)
            intent.putExtra("Aname", list2[position].Aname)
            intent.putExtra("Body", list2[position].Body)
            intent.putExtra("Aid", list2[position].Aid)

            quotesShow.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list2.size
    }

    class ViewData(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var txtQuotes1 = itemView.findViewById<TextView>(R.id.txtQuotes1)
        var imgViewMore = itemView.findViewById<ImageButton>(R.id.imgViewMore)
    }

}