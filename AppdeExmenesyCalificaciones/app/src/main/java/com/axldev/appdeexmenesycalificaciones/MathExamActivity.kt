package com.axldev.appdeexmenesycalificaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MathExamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val exam = intent.getIntExtra("exam", 1)

        setContent {
            ExamScreen(
                name = name,
                exam = exam,
                questions = listOf(
                    "¿Cuánto es 2 + 2?" to listOf("3", "4", "5"),
                    "¿Cuánto es 5 x 3?" to listOf("15", "20", "25"),
                    "¿Cuánto es 12 ÷ 4?" to listOf("2", "3", "4"),
                    "¿Cuánto es la raíz cuadrada de 16?" to listOf("2", "4", "6")
                ),
                correctAnswers = listOf(2, 1, 2, 2) // Opciones correctas (base 1)
            )
        }
    }
}
