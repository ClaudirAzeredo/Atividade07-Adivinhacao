package br.unipar.atividade07_adivinhacao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPalpite: EditText
    private lateinit var buttonVerificar: Button
    private lateinit var textViewDica: TextView

    private var numeroAleatorio: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPalpite = findViewById(R.id.editTextPalpite)
        buttonVerificar = findViewById(R.id.buttonVerificar)
        textViewDica = findViewById(R.id.textViewDica)

        gerarNumeroAleatorio()

        buttonVerificar.setOnClickListener {
            verificarPalpite()
        }
    }

    private fun gerarNumeroAleatorio() {
        numeroAleatorio = Random.nextInt(1, 101) // Gera um número de 1 a 100
    }

    private fun verificarPalpite() {
        val palpiteStr = editTextPalpite.text.toString()

        if (palpiteStr.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um número!", Toast.LENGTH_SHORT).show()
            return
        }

        val palpite = palpiteStr.toIntOrNull()

        if (palpite == null || palpite < 1 || palpite > 100) {
            Toast.makeText(this, "Por favor, insira um número válido entre 1 e 100!", Toast.LENGTH_SHORT).show()
            return
        }

        when {
            palpite < numeroAleatorio -> {
                textViewDica.text = "Seu palpite é muito baixo."
            }
            palpite > numeroAleatorio -> {
                textViewDica.text = "Seu palpite é muito alto."
            }
            else -> {
                textViewDica.text = "Parabéns! Você acertou."
                gerarNumeroAleatorio()
                Toast.makeText(this, "Gerando novo número! Tente novamente!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
