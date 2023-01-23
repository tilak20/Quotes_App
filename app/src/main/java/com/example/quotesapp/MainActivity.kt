package com.example.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin10.Screen.shared_screen.controller.DataBase
import com.example.quotesapp.Adapter.Category_Adapter
import com.example.quotesapp.ModelData.QuotesMD
import com.example.quotesapp.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var list1 = arrayListOf<QuotesMD>()
    lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = DataBase(this)
        list1 = dataBase.readCategory()

        CustomerRv()
    }

    fun CustomerRv() {
        val quotesAdapter = Category_Adapter(this,list1)
        val lm = LinearLayoutManager(this)
        binding.RCView.layoutManager = lm
        binding.RCView.adapter = quotesAdapter
    }
}