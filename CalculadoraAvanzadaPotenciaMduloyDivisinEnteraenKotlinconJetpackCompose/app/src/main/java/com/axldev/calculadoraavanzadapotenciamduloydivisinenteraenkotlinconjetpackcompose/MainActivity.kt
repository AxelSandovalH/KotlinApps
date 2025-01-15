package com.axldev.calculadoraavanzadapotenciamduloydivisinenteraenkotlinconjetpackcompose

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración inicial de la actividad
        setContent {
            AdvancedCalculatorApp() // Llama a la función principal de la calculadora
        }
    }
}

@Composable
fun AdvancedCalculatorApp() {
    // Estados para manejar entradas y resultado
    var inputX by remember { mutableStateOf(TextFieldValue("")) } // Entrada X
    var inputY by remember { mutableStateOf(TextFieldValue("")) } // Entrada Y
    var result by remember { mutableStateOf("0") } // Resultado inicial

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Margen interno
        horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
        verticalArrangement = Arrangement.Top // Alinea el contenido en la parte superior
    ) {
        // Campos de entrada para X e Y
        Text(text = "Valor X:")
        BasicTextField(
            value = inputX, // Estado vinculado a la entrada X
            onValueChange = { inputX = it }, // Actualiza el estado cuando cambia el valor
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(8.dp) // Margen interno
        )
        Text(text = "Valor Y:")
        BasicTextField(
            value = inputY, // Estado vinculado a la entrada Y
            onValueChange = { inputY = it }, // Actualiza el estado cuando cambia el valor
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Muestra el resultado de la operación
        Text(
            text = "Resultado: $result", // Muestra el resultado actual
            fontSize = 24.sp, // Tamaño del texto
            modifier = Modifier.padding(16.dp) // Margen interno
        )

        // Botones para operaciones básicas (Suma y Resta)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly // Espaciado uniforme
        ) {
            Button(onClick = {
                val x = inputX.text.toDoubleOrNull() ?: 0.0 // Convierte X a Double
                val y = inputY.text.toDoubleOrNull() ?: 0.0 // Convierte Y a Double
                result = (x + y).toString() // Realiza la suma
            }) {
                Text("+") // Etiqueta del botón
            }
            Button(onClick = {
                val x = inputX.text.toDoubleOrNull() ?: 0.0
                val y = inputY.text.toDoubleOrNull() ?: 0.0
                result = (x - y).toString() // Realiza la resta
            }) {
                Text("-")
            }
        }

        // Botones para operaciones avanzadas (Multiplicación y División)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val x = inputX.text.toDoubleOrNull() ?: 0.0
                val y = inputY.text.toDoubleOrNull() ?: 0.0
                result = (x * y).toString() // Realiza la multiplicación
            }) {
                Text("x")
            }
            Button(onClick = {
                val x = inputX.text.toDoubleOrNull() ?: 0.0
                val y = inputY.text.toDoubleOrNull() ?: 0.0
                result = if (y != 0.0) (x / y).toString() else "División por 0" // Maneja división por 0
            }) {
                Text("÷")
            }
        }

        // Botón para la operación x^y (Potencia)
        Button(onClick = {
            val x = inputX.text.toDoubleOrNull() ?: 0.0
            val y = inputY.text.toDoubleOrNull() ?: 0.0
            result = Math.pow(x, y).toString() // Calcula la potencia
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("x^y")
        }

        // Botón para la operación x%y (Módulo)
        Button(onClick = {
            val x = inputX.text.toIntOrNull() ?: 0 // Convierte X a Int
            val y = inputY.text.toIntOrNull() ?: 1 // Convierte Y a Int (valor predeterminado 1 para evitar división por 0)
            result = (x % y).toString() // Calcula el módulo
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("x % y")
        }

        // Botón para la operación x div y (División entera)
        Button(onClick = {
            val x = inputX.text.toIntOrNull() ?: 0
            val y = inputY.text.toIntOrNull() ?: 1
            result = (x / y).toString() // Realiza la división entera
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("x div y")
        }

        // Botón para reiniciar los valores
        Button(onClick = {
            inputX = TextFieldValue("") // Reinicia la entrada X
            inputY = TextFieldValue("") // Reinicia la entrada Y
            result = "0" // Reinicia el resultado
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Reiniciar")
        }
    }
}
