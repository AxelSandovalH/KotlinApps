package com.axldev.calculadorabasica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración inicial del contenido de la actividad
        // Aquí estamos definiendo que se mostrará la UI de Jetpack Compose
        setContent {
            BasicCalculatorApp() // Llama a la función principal de la calculadora
        }
    }
}

@Composable
fun BasicCalculatorApp() {
    // Estado para almacenar el número actual de la calculadora
    var num by remember { mutableStateOf(0) }

    // Diseño principal de la aplicación
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Agrega un margen interno de 16dp
        horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
        verticalArrangement = Arrangement.Top // Alinea el contenido en la parte superior
    ) {
        // Muestra el número actual en un texto grande
        Text(
            text = "$num", // Convierte el número a texto
            fontSize = 36.sp, // Tamaño de fuente grande
            modifier = Modifier.padding(top = 50.dp) // Agrega margen superior
        )

        // Primera fila de botones (Sumar, Restar, Reiniciar)
        Row(
            modifier = Modifier
                .fillMaxWidth() // La fila ocupa todo el ancho
                .padding(top = 50.dp), // Agrega margen superior
            horizontalArrangement = Arrangement.SpaceEvenly // Espacia los botones de manera uniforme
        ) {
            // Botón para sumar 1
            Button(onClick = { num++ }) {
                Text(text = "Sumar")
            }
            // Botón para restar 1
            Button(onClick = { num-- }) {
                Text(text = "Restar")
            }
            // Botón para reiniciar el número a 0
            Button(onClick = { num = 0 }) {
                Text(text = "Reiniciar")
            }
        }

        // Segunda fila de botones (+10, -10, Cambiar Signo)
        Row(
            modifier = Modifier
                .fillMaxWidth() // La fila ocupa todo el ancho
                .padding(top = 50.dp), // Agrega margen superior
            horizontalArrangement = Arrangement.SpaceEvenly // Espacia los botones de manera uniforme
        ) {
            // Botón para incrementar en 10
            Button(onClick = { num += 10 }) {
                Text(text = "+10")
            }
            // Botón para decrementar en 10
            Button(onClick = { num -= 10 }) {
                Text(text = "-10")
            }
            // Botón para cambiar el signo del número actual
            Button(onClick = { num *= -1 }) {
                Text(text = "Cambiar Signo")
            }
        }
    }
}

