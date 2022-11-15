package com.example.tdpa_3p_pr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        //Variables
        val mxn = findViewById<TextView>(R.id.mxn)
        val nombre = findViewById<TextView>(R.id.divisaName)
        val divisa = findViewById<TextView>(R.id.divisa)
        val total = findViewById<TextView>(R.id.total)
        val btn = findViewById<Button>(R.id.btn_volver)


        val bundle = intent.extras
        val mxnVal = bundle?.getString("mxn")
        mxn.text = mxnVal
        val nombreVal = bundle?.getString("moneda")
        nombre.text = nombreVal
        val divisaVal = bundle?.getString("valor")
        divisa.text = "$divisaVal MXN"
        val totalVal = bundle?.getString("total")
        total.text = "$totalVal MXN"

        btn.setOnClickListener{
            finish()
        }
    }
}