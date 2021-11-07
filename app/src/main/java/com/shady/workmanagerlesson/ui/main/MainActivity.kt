package com.shady.workmanagerlesson.ui.main

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shady.workmanagerlesson.*

class MainActivity : AppCompatActivity() {
    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var stockTV: TextView
    private lateinit var getStockUpdateBtn: Button
    private lateinit var sharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = this.getSharedPreferences(STOCK_SHARED_PREF, MODE_PRIVATE)
        stockTV = findViewById(R.id.tvStock)
        stockTV.text = sharedPreference.getString(
            STOCK_SHARED_KEY, "First Run")

        getStockUpdateBtn = findViewById(R.id.btnGetStockUpdate)
        getStockUpdateBtn.setOnClickListener {
            vm.returnStockChange(this).observe(this, {
                stockTV.text = it
            })
        }


    }
}