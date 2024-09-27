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
fun RegistryPage(
    onClickCancelButton: () -> Unit = {},
    onSignUpButton: ()->Unit={}
){
    // State to control the visibility of the first and second parts of the form
    var showFirstPart by remember { mutableStateOf(true) }

    // State variables for first part of the form
    val textStateName = remember { mutableStateOf(TextFieldValue()) }
    val textStateAge = remember { mutableStateOf(TextFieldValue()) }
    val textStateProfession = remember { mutableStateOf(TextFieldValue()) }

    // State variables
    var selectedActivity by remember { mutableStateOf("") }
    var timeInMinutes by remember { mutableStateOf("") }
    var selectedTimeSlot by remember { mutableStateOf("") }

    // State for dropdown menus
    var expandedActivity by remember { mutableStateOf(false) }
    var expandedTimeSlot by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        if (showFirstPart) {
            // First part of the form
            TextField(
                value = textStateName.value,
                onValueChange = { textStateName.value = it },
                label = { Text("Nombre") },
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Move focus to the next field */ })
            )
            Row(modifier = Modifier.width(277.dp), horizontalArrangement = Arrangement.Start) {
                Text(text = "Ingresa tu nombre", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = textStateAge.value,
                onValueChange = { textStateAge.value = it },
                label = { Text("Edad") },
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Move focus to the next field */ })
            )
            Row(modifier = Modifier.width(277.dp), horizontalArrangement = Arrangement.Start) {
                Text(text = "Ingresa tu edad", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = textStateProfession.value,
                onValueChange = { textStateProfession.value = it },
                label = { Text("Profesión") },
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Move focus to the next field */ })
            )
            Row(modifier = Modifier.width(277.dp), horizontalArrangement = Arrangement.Start) {
                Text(text = "Ingresa tu profesión", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Button to continue to the second part of the form
            VinylsButton(
                onClick = { showFirstPart = false },
                type = ButtonType.PRIMARY,
                label = "Continuar"
            )
            VinylsButton(
                label = "Cancelar",
                onClick = onClickCancelButton,
                type = ButtonType.TERTIARY,

            )
        } else {

                // Dropdown for activity selection
                Text("Seleccionar actividad:")
                ExposedDropdownMenuBox(
                    expanded = expandedActivity,
                    onExpandedChange = { expandedActivity = !expandedActivity }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedActivity,
                        onValueChange = { /* No-op */ },
                        label = { Text("Seleccione una actividad") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedActivity)
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedActivity,
                        onDismissRequest = { expandedActivity = false }
                    ) {
                        listOf("Estudio", "Lectura", "Trabajo").forEach { activity ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedActivity = activity
                                    expandedActivity = false
                                }
                                ,text={Text(activity)}
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input for time in minutes

                TextField(
                    label = { Text("Tiempo (minutos)") },
                    value = timeInMinutes,
                    onValueChange = { timeInMinutes = it },
                    placeholder = { Text("Ingrese tiempo en minutos") }
                )


            Spacer(modifier = Modifier.height(16.dp))

                // Dropdown for time slot selection
                Text("Seleccionar franja:")
                ExposedDropdownMenuBox(
                    expanded = expandedTimeSlot,
                    onExpandedChange = { expandedTimeSlot = !expandedTimeSlot }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedTimeSlot,
                        onValueChange = { /* No-op */ },
                        label = { Text("Seleccione una franja") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTimeSlot)
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTimeSlot,
                        onDismissRequest = { expandedTimeSlot = false }
                    ) {
                        listOf("Diurno", "Tarde", "Nocturno").forEach { timeSlot ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedTimeSlot = timeSlot
                                    expandedTimeSlot = false
                                }
                                ,text={Text(timeSlot)}
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Display selected values

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
    }

@Preview(showBackground = true)
@Composable
fun RegistryPrev(){
    RegistryPage()
}
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun validatePassword(pass1:String,pass2:String):Boolean{
    return pass1==pass2
}

