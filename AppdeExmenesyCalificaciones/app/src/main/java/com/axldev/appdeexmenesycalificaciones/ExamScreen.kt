package com.axldev.appdeexmenesycalificaciones

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

@Composable
fun ExamScreen(
    name: String,
    exam: Int,
    questions: List<Pair<String, List<String>>>,
    correctAnswers: List<Int>
) {
    val context = LocalContext.current
    var answers by remember { mutableStateOf(List(questions.size) { 1 }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Examen", fontSize = 24.sp)

        questions.forEachIndexed { index, (question, options) ->
            Text(question)
            Row {
                options.forEachIndexed { optionIndex, option ->
                    RadioButton(
                        selected = answers[index] == optionIndex + 1,
                        onClick = {
                            answers = answers.toMutableList().apply { set(index, optionIndex + 1) }
                        }
                    )
                    Text(option)
                }
            }
        }

        Button(
            onClick = {
                val intent = Intent(context, ResultActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("exam", exam)
                    putExtra("answers", answers.toIntArray())
                    putExtra("correctAnswers", correctAnswers.toIntArray())
                }
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Calificar")
        }
    }
}
