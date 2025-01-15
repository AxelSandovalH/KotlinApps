package com.axldev.calculadoracientficaconkotlinyjetpackcomposeoperacionesavanzadasytrigonometra

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
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScientificCalculatorApp()
        }
    }
}

@Composable
fun ScientificCalculatorApp() {
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Campo de entrada
        Text(text = "Entrada:")
        BasicTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Resultado
        Text(
            text = "Resultado: $result",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Operaciones básicas y avanzadas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { result = (input.text.toDoubleOrNull() ?: 0.0).toString() }) {
                Text("+/-")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 0.0
                result = abs(value).toString()
            }) {
                Text("|x|")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = factorial(value.toInt()).toString()
            }) {
                Text("n!")
            }
        }

        // Funciones logarítmicas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = ln(value).toString()
            }) {
                Text("ln")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = log10(value).toString()
            }) {
                Text("log")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = (1 / value).toString()
            }) {
                Text("1/x")
            }
        }

        // Funciones trigonométricas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 0.0
                result = sin(value).toString()
            }) {
                Text("sin")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 0.0
                result = cos(value).toString()
            }) {
                Text("cos")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 0.0
                result = tan(value).toString()
            }) {
                Text("tan")
            }
        }

        // Constante Pi y Potencia
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { result = PI.toString() }) {
                Text("Pi")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = (value.pow(2)).toString()
            }) {
                Text("x^2")
            }
            Button(onClick = {
                val value = input.text.toDoubleOrNull() ?: 1.0
                result = (10.0.pow(value)).toString()
            }) {
                Text("10^x")
            }
        }
    }
}

// Función para calcular el factorial
fun factorial(n: Int): Long {
    return if (n == 0 || n == 1) 1 else n * factorial(n - 1)
}
