package com.styvencatagua.pr16_exercicidepersistnciaemmagatzematgeintern

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etNom: EditText
    private lateinit var etTelefon: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNom = findViewById(R.id.editTextText)
        etTelefon = findViewById(R.id.editTextPhone)
        val btnGuardar = findViewById<Button>(R.id.button)

        btnGuardar.setOnClickListener { guardarContacte() }
    }

    private fun guardarContacte() {
        val nom = etNom.text.toString().trim()
        val telefon = etTelefon.text.toString().trim()

        if (nom.isEmpty() || telefon.isEmpty()) {
            Toast.makeText(this, "Omple tots els camps", Toast.LENGTH_SHORT).show()
            return
        }

        val linia = "$nom;$telefon\n"

        try {
            // MODE_APPEND afegeix al final; MODE_PRIVATE sobreescriuria l'arxiu
            openFileOutput("contactes.txt", Context.MODE_APPEND).use {
                it.write(linia.toByteArray())
            }
            Toast.makeText(this, "Contacte guardat", Toast.LENGTH_SHORT).show()
            etNom.text.clear()
            etTelefon.text.clear()
        } catch (e: IOException) {
            Toast.makeText(this, "Error en guardar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}