package com.shady.workmanagerlesson.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shady.workmanagerlesson.R

class MainActivity : AppCompatActivity() {
    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var stockTV: TextView
    private lateinit var getStockUpdateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stockTV = findViewById(R.id.tvStock)
        getStockUpdateBtn = findViewById(R.id.btnGetStockUpdate)
        getStockUpdateBtn.setOnClickListener {
            stockTV.text = vm.returnStockChange()

        }
    }
}