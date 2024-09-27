package com.example.consciente_te.pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consciente_te.R
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton
import android.util.Patterns
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

@Composable
fun ConfigurarPomoPage(
    onClickCancelButton: () -> Unit = {},
    onSignUpButton: ()->Unit={}
){
    var showFirstPart by remember { mutableStateOf(true) }

    val textStateTimeProductive = remember { mutableStateOf(TextFieldValue()) }
    val textStatePomodoros = remember { mutableStateOf(TextFieldValue()) }

    val colorOptions = listOf("Azul", "Naranja", "Gris")
    var selectedColor by remember { mutableStateOf(colorOptions[0]) }

    val timeSlotOptions = listOf("Diurno", "Tarde", "Nocturno")
    var selectedTimeSlot by remember { mutableStateOf(timeSlotOptions[0]) }

    var expandedColor by remember { mutableStateOf(false) }
    var expandedTimeSlot by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {

        // Tiempo Productivo (numérico)
        TextField(
            value = textStateTimeProductive.value,
            onValueChange = { textStateTimeProductive.value = it },
            label = { Text("Tiempo Productivo (min)") },
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Move focus to the next field */ })
        )


        Spacer(modifier = Modifier.height(30.dp))

        // Pomodoros al día (numérico)
        TextField(
            value = textStatePomodoros.value,
            onValueChange = { textStatePomodoros.value = it },
            label = { Text("Pomodoros al Día") },
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Move focus to the next field */ })
        )
       

        Spacer(modifier = Modifier.height(30.dp))

        // Seleccionar color (dropdown)
        ExposedDropdownMenuBox(
            expanded = expandedColor,
            onExpandedChange = { expandedColor = !expandedColor }
        ) {
            TextField(
                readOnly = true,
                value = selectedColor,
                onValueChange = {},
                label = { Text("Seleccionar Color") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedColor)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedColor,
                onDismissRequest = { expandedColor = false }
            ) {
                colorOptions.forEach { color ->
                    DropdownMenuItem(
                        text = { Text(color) },
                        onClick = {
                            selectedColor = color
                            expandedColor = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Seleccionar franja (dropdown)
        ExposedDropdownMenuBox(
            expanded = expandedTimeSlot,
            onExpandedChange = { expandedTimeSlot = !expandedTimeSlot }
        ) {
            TextField(
                readOnly = true,
                value = selectedTimeSlot,
                onValueChange = {},
                label = { Text("Seleccionar Franja") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTimeSlot)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedTimeSlot,
                onDismissRequest = { expandedTimeSlot = false }
            ) {
                timeSlotOptions.forEach { timeSlot ->
                    DropdownMenuItem(
                        text = { Text(timeSlot) },
                        onClick = {
                            selectedTimeSlot = timeSlot
                            expandedTimeSlot = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Button to continue or cancel
        VinylsButton(
            onClick = onSignUpButton,
            type = ButtonType.PRIMARY,
            label = "Guardar"
        )
        VinylsButton(
            label = "Cancelar",
            onClick = onClickCancelButton,
            type = ButtonType.TERTIARY,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistryPrevView(){
    ConfigurarPomoPage()
}

