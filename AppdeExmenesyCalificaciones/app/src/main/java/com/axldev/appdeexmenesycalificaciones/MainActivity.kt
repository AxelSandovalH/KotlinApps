package com.axldev.appdeexmenesycalificaciones

import androidx.compose.ui.platform.LocalContext


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var selectedExam by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ingrese sus datos", fontSize = 20.sp)

        Text("Nombre:")
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text("Seleccione Examen:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(selected = selectedExam == 1, onClick = { selectedExam = 1 })
            Text("Matemáticas")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = selectedExam == 2, onClick = { selectedExam = 2 })
            Text("Español")
        }

        Button(
            onClick = {
                val intent = if (selectedExam == 1) {
                    Intent(context, MathExamActivity::class.java)
                } else {
                    Intent(context, SpanishExamActivity::class.java)
                }
                intent.putExtra("name", name)
                intent.putExtra("exam", selectedExam)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Contestar")
        }
    }
}
