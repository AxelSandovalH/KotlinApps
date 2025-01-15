package com.axldev.appdeexmenesycalificaciones

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val exam = intent.getIntExtra("exam", 1)
        val answers = intent.getIntArrayExtra("answers")?.toList() ?: emptyList()
        val correctAnswers = intent.getIntArrayExtra("correctAnswers")?.toList() ?: emptyList()

        setContent {
            ResultScreen(
                name = name,
                exam = exam,
                answers = answers,
                correctAnswers = correctAnswers,
                onFinish = {
                    // Regresa a MainActivity y limpia la pila de actividades
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            )
        }
    }
}

@Composable
fun ResultScreen(
    name: String,
    exam: Int,
    answers: List<Int>,
    correctAnswers: List<Int>,
    onFinish: () -> Unit
) {
    val score = answers.zip(correctAnswers).count { it.first == it.second } * 2.5
    val status = if (score >= 6) "Aprobado" else "Reprobado"
    val subject = if (exam == 1) "Matemáticas" else "Español"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Resultados", fontSize = 24.sp)
        Text("Nombre: $name")
        Text("Materia: $subject")
        Text("Calificación: %.2f".format(score))
        Text("Estado: $status")

        Button(onClick = onFinish) {
            Text("Volver al Inicio")
        }
    }
}
