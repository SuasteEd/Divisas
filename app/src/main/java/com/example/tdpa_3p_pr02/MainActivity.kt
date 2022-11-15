package com.example.tdpa_3p_pr02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        val nombreMoneda = findViewById<EditText>(R.id.nombre)
        val valor = findViewById<EditText>(R.id.cambio)
        val monto = findViewById<EditText>(R.id.monto)
        val spinner = findViewById<Spinner>(R.id.spn_origen)
        val btn_agregar = findViewById<Button>(R.id.btn_agregar)
        val btn_calcular = findViewById<Button>(R.id.btn_convertir)
        var total: Float
        var index = 0

        //Listas
        val moneda:MutableList<String> = mutableListOf("YEN", "USD")
        val cambio:MutableList<Float> = mutableListOf(0.14F, 19F)

        //Spinner
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, moneda)
        spinner.adapter = arrayAdapter

        //Funciones
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                Toast.makeText(this@MainActivity, "Se ha seleccionado " + moneda[position], Toast.LENGTH_SHORT).show()
                index = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "No se ha seleccionado moneda", Toast.LENGTH_SHORT).show()
            }
        }
        //Validaciones
        fun validar():Boolean{
            if(nombreMoneda.text.toString() == "" || valor.text.toString() == ""){
                Toast.makeText(this@MainActivity, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        fun validarCalcular():Boolean{
            if(monto.text.toString() == ""){
                Toast.makeText(this@MainActivity, "Ingresa un monto", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        //Agregar
        btn_agregar.setOnClickListener {
            if(validar()){
                try {
                    moneda.add(nombreMoneda.text.toString())
                    cambio.add(valor.text.toString().toFloat())
                    Toast.makeText(this@MainActivity, "Se agregó la divisa", Toast.LENGTH_SHORT).show()
                } catch(e: Exception) {
                    print(e)
                    Toast.makeText(this@MainActivity, "No se pudo agregar la divisa", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Segunda vista

        //Calcular
        btn_calcular.setOnClickListener{
            if(validarCalcular()){
                total = cambio[index].toString().toFloat() * monto.text.toString().toFloat()
                val intento = Intent(this, ViewActivity::class.java)
                intento.putExtra("mxn", monto.text.toString())
                intento.putExtra("moneda", moneda[index])
                intento.putExtra("valor", cambio[index].toString())
                intento.putExtra("total", total.toString())
                //Toast.makeText(this@MainActivity, "Total: ${total}", Toast.LENGTH_SHORT).show()
                startActivity(intento)
            }
        }


    }
}