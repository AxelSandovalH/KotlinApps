package com.axldev.calculadoradeetapasdevida

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la interfaz principal usando Jetpack Compose
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current // Contexto para navegar a otras actividades
    var name by remember { mutableStateOf("") } // Estado para el nombre
    var day by remember { mutableStateOf("") } // Estado para el día de nacimiento
    var month by remember { mutableStateOf("") } // Estado para el mes de nacimiento
    var year by remember { mutableStateOf("") } // Estado para el año de nacimiento

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        verticalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre elementos
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        Text("Ingrese sus datos", fontSize = 20.sp)

        // Entrada para el nombre
        Text("Nombre:")
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(8.dp)
        )

        // Entrada para el día de nacimiento
        Text("Día de Nacimiento:")
        BasicTextField(
            value = day,
            onValueChange = { day = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Entrada para el mes de nacimiento
        Text("Mes de Nacimiento:")
        BasicTextField(
            value = month,
            onValueChange = { month = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Entrada para el año de nacimiento
        Text("Año de Nacimiento:")
        BasicTextField(
            value = year,
            onValueChange = { year = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Botón para calcular la etapa de vida
        Button(
            onClick = {
                val intent = Intent(context, ResultActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("day", day.toIntOrNull() ?: 0)
                    putExtra("month", month.toIntOrNull() ?: 0)
                    putExtra("year", year.toIntOrNull() ?: 0)
                }
                context.startActivity(intent) // Inicia la actividad de resultados
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calcular Etapa de Vida")
        }
    }
}