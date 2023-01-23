package com.example.quotesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin10.Screen.shared_screen.controller.DataBase
import com.example.quotesapp.Adapter.QuotesAdapter
import com.example.quotesapp.ModelData.QuotesReadMD
import com.example.quotesapp.databinding.ActivityQuotesShowBinding

class Quotes_Show : AppCompatActivity() {

    lateinit var binding : ActivityQuotesShowBinding
    var list2 = arrayListOf<QuotesReadMD>()
    lateinit var dataBase: DataBase
    lateinit var id : String
    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        name = intent.getStringExtra("Cname").toString()

        binding.txtCategoryShow.setText(name)

        dataBase = DataBase(this)
        list2 = dataBase.readQuotes(id)

        CustomerRv()
    }

    fun CustomerRv() {
        val quotesAdapter = QuotesAdapter(this,list2)
        val lm = LinearLayoutManager(this)
        binding.QuotesRV.layoutManager = lm
        binding.QuotesRV.adapter = quotesAdapter
    }
}