package com.axldev.appdeloginycalculadoracalorasymaratnconkotlinenandroidstudio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la interfaz principal usando Jetpack Compose
        setContent {
            MainActivityContent() // Llama a la función composable principal
        }
    }
}

@Composable
fun MainActivityContent() {
    val context = LocalContext.current // Contexto necesario para lanzar intents

    // Estados para manejar los datos del formulario
    var name by remember { mutableStateOf("") } // Almacena el nombre
    var age by remember { mutableStateOf("") } // Almacena la edad
    var selectedSex by remember { mutableStateOf("Hombre") } // Almacena el sexo seleccionado

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        horizontalAlignment = Alignment.CenterHorizontally // Alinea el contenido horizontalmente
    ) {
        // Entrada para el nombre
        Text("Nombre:", fontSize = 18.sp)
        BasicTextField(
            value = name,
            onValueChange = { name = it }, // Actualiza el estado cuando cambia el valor
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Entrada para la edad
        Text("Edad:", fontSize = 18.sp)
        BasicTextField(
            value = age,
            onValueChange = { age = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Opciones de selección de sexo
        Text("Sexo:", fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // Espaciado uniforme
        ) {
            RadioButton(
                selected = selectedSex == "Hombre",
                onClick = { selectedSex = "Hombre" } // Cambia el estado al seleccionar
            )
            Text("Hombre")

            RadioButton(
                selected = selectedSex == "Mujer",
                onClick = { selectedSex = "Mujer" }
            )
            Text("Mujer")
        }

        // Botón para ir a la Calculadora de Calorías
        Button(
            onClick = {
                if (name.isBlank() || age.isBlank()) {
                    // Validación de campos vacíos
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val ageValue = age.toIntOrNull()
                if (ageValue == null || ageValue <= 0) {
                    // Validación de edad inválida
                    Toast.makeText(context, "Por favor, introduzca una edad válida", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Crear intent para navegar a la actividad de cálculo de calorías
                val intent = Intent(context, CaloriasActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("age", ageValue)
                    putExtra("sex", selectedSex)
                }
                context.startActivity(intent) // Lanza la nueva actividad
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculadora de Calorías")
        }

        // Botón para ir a la sección del Maratón de Boston
        Button(
            onClick = {
                if (name.isBlank() || age.isBlank()) {
                    // Validación de campos vacíos
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val ageValue = age.toIntOrNull()
                if (ageValue == null || ageValue <= 0) {
                    // Validación de edad inválida
                    Toast.makeText(context, "Por favor, introduzca una edad válida", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Crear intent para navegar a la actividad del maratón
                val intent = Intent(context, BostonActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("age", ageValue)
                    putExtra("sex", selectedSex)
                }
                context.startActivity(intent) // Lanza la nueva actividad
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Boston 2020")
        }
    }
}
