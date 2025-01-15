package com.axldev.calculadoradeetapasdevida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Period

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Extrae los datos enviados desde MainActivity
        val name = intent.getStringExtra("name") ?: ""
        val day = intent.getIntExtra("day", 0)
        val month = intent.getIntExtra("month", 0)
        val year = intent.getIntExtra("year", 0)

        setContent {
            ResultScreen(name, day, month, year) // Muestra la pantalla de resultados
        }
    }
}

@Composable
fun ResultScreen(name: String, day: Int, month: Int, year: Int) {
    val today = LocalDate.now() // Obtiene la fecha actual
    val birthDate = LocalDate.of(year, month, day) // Construye la fecha de nacimiento
    val age = remember { Period.between(birthDate, today).years } // Calcula la edad

    // Determina la etapa de vida según la edad
    val stage = when (age) {
        in 0..2 -> "Maternal"
        in 3..5 -> "Kinder"
        in 6..12 -> "Primaria"
        in 13..15 -> "Secundaria"
        in 16..18 -> "Prepa"
        in 19..24 -> "Universidad"
        in 25..65 -> "Trabaja"
        else -> "Jubilado/a"
    }

    // Lista de nombres de meses
    val monthName = listOf(
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    )[month - 1]

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        verticalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre elementos
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        Text("Nombre: $name", fontSize = 20.sp)
        Text("Fecha Nacimiento: $day de $monthName del año $year", fontSize = 20.sp)
        Text("Edad: Tienes $age años", fontSize = 20.sp)
        Text("Etapa: $stage", fontSize = 20.sp)
    }
}
