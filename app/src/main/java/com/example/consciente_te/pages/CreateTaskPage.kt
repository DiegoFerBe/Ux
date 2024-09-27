package com.example.consciente_te.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton

@Composable
fun CreateTaskPage(
    onClickCancelButton: () -> Unit = {},
    onSaveButton: ()->Unit={},

){
   PinInput(onPinEntered=onSaveButton, modifier = Modifier)
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TimeSelector(
                 onClickCancelButton: () -> Unit = {},
                 onSaveButton: ()->Unit={},
                ) {

}

@Composable
fun PinInput(
    modifier: Modifier,
    onPinEntered: () -> Unit,

) {
    // Estado para manejar el PIN
    // Componente principal
    var pin by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF405D72)) // Color de fondo
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Mostrar líneas para el PIN ingresado
            Row(
                modifier = Modifier.padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until 4) {
                    Text(
                        text = if (i < pin.length) "*" else "_",
                        color = Color.White,
                        fontSize = 28.sp,
                        modifier = Modifier.width(24.dp)
                    )
                }
            }

            // TextField para ingresar el PIN
            TextField(
                value = pin,
                onValueChange = {
                    if (it.length <= 4) {
                        pin = it
                        if (pin.length == 4) {
                            onPinEntered() // Llamar a la función cuando el PIN tiene 4 caracteres
                            // Llamar a la acción si se completa el PIN
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    autoCorrect = false
                ),
                visualTransformation = PasswordVisualTransformation(), // Para mostrar caracteres como puntos
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(Color.Transparent)
                    .alpha(0f) // Hacer invisible el TextField para solo mostrar el PIN como líneas
            )

            // Icono de huella
            Spacer(modifier = Modifier.height(32.dp))
            Icon(
                imageVector = Icons.Filled.Fingerprint, // Cambia esto por el ícono que desees
                contentDescription = "Huella",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
                    .clickable {

                            onPinEntered() // Llamar a la acción al presionar el ícono si el PIN está completo

                    }
            )
        }
    }
}
@Preview
@Composable
fun TimeSelectorPreview() {
    CreateTaskPage()
}