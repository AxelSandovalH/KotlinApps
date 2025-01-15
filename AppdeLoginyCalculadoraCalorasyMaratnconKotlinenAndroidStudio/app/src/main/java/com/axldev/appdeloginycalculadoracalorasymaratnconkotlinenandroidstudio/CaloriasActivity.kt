package com.axldev.appdeloginycalculadoracalorasymaratnconkotlinenandroidstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CaloriasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la actividad con la interfaz de Compose
        setContent {
            CaloriasActivityContent(
                name = intent.getStringExtra("name") ?: "", // Recibe el nombre
                age = intent.getIntExtra("age", 0), // Recibe la edad
                sex = intent.getStringExtra("sex") ?: "", // Recibe el sexo
                onExit = { finish() } // Acción para cerrar la actividad
            )
        }
    }
}

@Composable
fun CaloriasActivityContent(name: String, age: Int, sex: String, onExit: () -> Unit) {
    // Estados para manejar peso, altura y resultado
    var weight by remember { mutableStateOf("") } // Entrada de peso
    var height by remember { mutableStateOf("") } // Entrada de altura
    var result by remember { mutableStateOf("") } // Resultado calculado

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

        // Entrada para el peso
        Text("Peso (kg):", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
        BasicTextField(
            value = weight,
            onValueChange = { weight = it }, // Actualiza el estado de peso
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(8.dp)
        )

        // Entrada para la altura
        Text("Altura (cm):", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
        BasicTextField(
            value = height,
            onValueChange = { height = it }, // Actualiza el estado de altura
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Botón para calcular calorías basales
        Button(
            onClick = {
                val weightValue = weight.toDoubleOrNull() ?: 0.0 // Convierte el peso a Double
                val heightValue = height.toDoubleOrNull() ?: 0.0 // Convierte la altura a Double
                result = if (sex == "Mujer") {
                    // Fórmula para mujeres
                    ((10 * weightValue) + (6.25 * heightValue) - (5 * age) - 161).toInt().toString()
                } else {
                    // Fórmula para hombres
                    ((10 * weightValue) + (6.25 * heightValue) - (5 * age) + 5).toInt().toString()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calcular")
        }

        // Mostrar el resultado
        if (result.isNotEmpty()) {
            Text(
                text = "Calorías Diarias Basales: $result kcal", // Resultado calculado
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        // Botón para salir
        Button(
            onClick = { onExit() }, // Llama a la función para cerrar la actividad
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Salir")
        }
    }
}
