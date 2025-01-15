package com.axldev.appdeloginycalculadoracalorasymaratnconkotlinenandroidstudio

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BostonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la actividad con la interfaz de Compose
        setContent {
            BostonActivityContent(
                name = intent.getStringExtra("name") ?: "", // Recibe el nombre
                age = intent.getIntExtra("age", 0), // Recibe la edad
                sex = intent.getStringExtra("sex") ?: "", // Recibe el sexo
                onExit = { finish() } // Acción para cerrar la actividad
            )
        }
    }
}

@Composable
fun BostonActivityContent(name: String, age: Int, sex: String, onExit: () -> Unit) {
    // Estado para mostrar el resultado
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Mostrar los datos recibidos
        Text("Nombre: $name", fontSize = 18.sp)
        Text("Edad: $age", fontSize = 18.sp)
        Text("Sexo: $sex", fontSize = 18.sp)

        // Botón para calcular el tiempo requerido
        Button(
            onClick = {
                result = calculateBostonTime(age, sex) // Calcula el tiempo basado en edad y sexo
                if (result.isEmpty()) {
                    // Muestra un mensaje si la edad no es válida
                    Toast.makeText(
                        context,
                        "Edad no permitida para clasificar al Maratón",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Tiempo requerido")
        }

        // Mostrar el resultado si está disponible
        if (result.isNotEmpty()) {
            Text(
                text = "Tiempo requerido para clasificar: $result", // Tiempo calculado
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        // Botón para salir de la actividad
        Button(
            onClick = { onExit() }, // Llama a la función para cerrar la actividad
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Salir")
        }
    }
}

// Función para calcular el tiempo requerido para clasificar al maratón
fun calculateBostonTime(age: Int, sex: String): String {
    return when {
        // Clasificación basada en rangos de edad y sexo
        age in 18..34 && sex == "Hombre" -> "3:00:00"
        age in 18..34 && sex == "Mujer" -> "3:30:00"
        age in 35..39 && sex == "Hombre" -> "3:05:00"
        age in 35..39 && sex == "Mujer" -> "3:35:00"
        age in 40..44 && sex == "Hombre" -> "3:10:00"
        age in 40..44 && sex == "Mujer" -> "3:40:00"
        age in 45..49 && sex == "Hombre" -> "3:20:00"
        age in 45..49 && sex == "Mujer" -> "3:50:00"
        age in 50..54 && sex == "Hombre" -> "3:25:00"
        age in 50..54 && sex == "Mujer" -> "3:55:00"
        age in 55..59 && sex == "Hombre" -> "3:35:00"
        age in 55..59 && sex == "Mujer" -> "4:05:00"
        age in 60..64 && sex == "Hombre" -> "3:50:00"
        age in 60..64 && sex == "Mujer" -> "4:20:00"
        age in 65..69 && sex == "Hombre" -> "4:05:00"
        age in 65..69 && sex == "Mujer" -> "4:35:00"
        age in 70..74 && sex == "Hombre" -> "4:20:00"
        age in 70..74 && sex == "Mujer" -> "4:50:00"
        age in 75..79 && sex == "Hombre" -> "4:35:00"
        age in 75..79 && sex == "Mujer" -> "5:05:00"
        age >= 80 && sex == "Hombre" -> "4:50:00"
        age >= 80 && sex == "Mujer" -> "5:20:00"
        else -> "" // Edad no válida para clasificar
    }
}
