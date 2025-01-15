package com.axldev.appdeexmenesycalificaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SpanishExamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val exam = intent.getIntExtra("exam", 2)

        setContent {
            ExamScreen(
                name = name,
                exam = exam,
                questions = listOf(
                    "¿Cuál es un sinónimo de 'feliz'?" to listOf("Triste", "Contento", "Cansado"),
                    "¿Qué palabra lleva tilde en la frase 'El árbol es alto'?" to listOf("El", "Árbol", "Alto"),
                    "¿Cuál es el plural de 'luz'?" to listOf("Luces", "Luzes", "Luzs"),
                    "¿Qué tipo de palabra es 'rápido'?" to listOf("Adjetivo", "Verbo", "Sustantivo")
                ),
                correctAnswers = listOf(2, 2, 1, 1) // Opciones correctas (base 1)
            )
        }
    }
}
