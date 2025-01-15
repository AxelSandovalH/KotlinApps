package com.axldev.convertidordedivisas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la actividad con la interfaz de Jetpack Compose
        setContent {
            UnitConverter() // Llama a la función composable principal
        }
    }
}

@Composable
fun UnitConverter() {
    // Lista de unidades disponibles para conversión
    val units = listOf(
        "bit", "Byte", "Kilobyte", "Kibibyte", "Megabyte", "Mebibyte",
        "Gigabyte", "Gibibyte", "Terabyte", "Tebibyte", "Petabyte", "Pebibyte"
    )

    // Estados para los valores de entrada, selección y resultado
    var valueToConvert by remember { mutableStateOf("") } // Entrada del usuario
    var selectedUnitOrigin by remember { mutableStateOf("bit") } // Unidad origen
    var selectedUnitDestination by remember { mutableStateOf("Byte") } // Unidad destino
    var result by remember { mutableStateOf("") } // Resultado de la conversión

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Entrada del valor a convertir
        Text("Valor a convertir:", fontSize = 18.sp)
        BasicTextField(
            value = valueToConvert,
            onValueChange = { valueToConvert = it }, // Actualiza el estado cuando cambia el valor
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(8.dp)
        )

        // Selección de unidad origen
        Text("Seleccione unidad origen:", fontSize = 18.sp)
        DropdownMenuField(selectedValue = selectedUnitOrigin, items = units) {
            selectedUnitOrigin = it
        }

        // Selección de unidad destino
        Text("Seleccione unidad destino:", fontSize = 18.sp)
        DropdownMenuField(selectedValue = selectedUnitDestination, items = units) {
            selectedUnitDestination = it
        }

        // Botón para realizar la conversión
        Button(onClick = {
            val value = valueToConvert.toDoubleOrNull() // Intenta convertir la entrada a Double
            if (value != null) {
                result = convertUnits(value, selectedUnitOrigin, selectedUnitDestination) // Realiza la conversión
            } else {
                result = "Entrada inválida" // Maneja entradas no válidas
            }
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Convertir")
        }

        // Muestra el resultado de la conversión
        if (result.isNotEmpty()) {
            Text("Resultado: $result", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun DropdownMenuField(
    selectedValue: String,
    items: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // Estado para controlar la expansión del menú

    Column {
        Button(onClick = { expanded = !expanded }) {
            Text(selectedValue) // Muestra la unidad seleccionada
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(item) // Actualiza la unidad seleccionada
                        expanded = false // Cierra el menú
                    },
                    text = { Text(item) }
                )
            }
        }
    }
}

fun convertUnits(value: Double, fromUnit: String, toUnit: String): String {
    // Mapa de conversión con equivalencias en bits
    val unitMap = mapOf(
        "bit" to 1.0,
        "Byte" to 8.0,
        "Kilobyte" to 8000.0,
        "Kibibyte" to 8192.0,
        "Megabyte" to 8_000_000.0,
        "Mebibyte" to 8_388_608.0,
        "Gigabyte" to 8_000_000_000.0,
        "Gibibyte" to 8_589_934_592.0,
        "Terabyte" to 8_000_000_000_000.0,
        "Tebibyte" to 8_796_093_022_208.0,
        "Petabyte" to 8_000_000_000_000_000.0,
        "Pebibyte" to 9_007_199_254_740_992.0
    )

    // Obtiene los valores en bits de las unidades origen y destino
    val fromValue = unitMap[fromUnit] ?: return "Unidad desconocida"
    val toValue = unitMap[toUnit] ?: return "Unidad desconocida"

    // Calcula el resultado de la conversión
    return String.format("%.2f", value * fromValue / toValue)
}
