package com.example.docerify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username: TextView = findViewById(R.id.usuario)
        val password: TextView = findViewById(R.id.password)

        val boton: Button = findViewById(R.id.loginbtn)

        boton.setOnClickListener(){
            if(username.text == "admin" && password.text == "admin")
                Toast.makeText(this,"SESION INICIADA CON ÉXITO", Toast.LENGTH_SHORT).show()
            else{
                Toast.makeText(this,"INICIO DE SESIÓN FALLIDO", Toast.LENGTH_SHORT).show()
                username.text = ""
                password.text = ""
            }
        }

    }
}