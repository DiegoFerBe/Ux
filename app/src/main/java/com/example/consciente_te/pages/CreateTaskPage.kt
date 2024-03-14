package com.example.consciente_te.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton

@Composable
fun CreateTaskPage(
    onClickCancelButton: () -> Unit = {},
    onSaveButton: ()->Unit={}
){
    TimeSelector(
        selectedHours = 0,
        selectedMinutes = 0,
        selectedSeconds = 0,
        onTimeSelected = { hours, minutes, seconds ->
            // Acción cuando se selecciona un tiempo
        },
        onClickCancelButton=onClickCancelButton,
        onSaveButton=onSaveButton
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TimeSelector(selectedHours: Int,
                 selectedMinutes: Int,
                 selectedSeconds: Int,
                 onClickCancelButton: () -> Unit = {},
                 onSaveButton: ()->Unit={},
                 onTimeSelected: (hours: Int, minutes: Int, seconds: Int) -> Unit) {
    var hours by remember { mutableStateOf(selectedHours) }
    var minutes by remember { mutableStateOf(selectedMinutes) }
    var seconds by remember { mutableStateOf(selectedSeconds) }
    val textStateTaskName = remember { mutableStateOf(TextFieldValue()) }
    val textStateTaskDesc = remember { mutableStateOf(TextFieldValue()) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {
        // Selector de horas
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textStateTaskName.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateTaskName.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Task name") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textStateTaskDesc.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateTaskDesc.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            maxLines = 5, // Establecer el número máximo de líneas
            singleLine = false,
            label = { Text("Description") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de minutos
        Row(verticalAlignment = Alignment.CenterVertically) {

            Column {


                OutlinedTextField(
                    value = hours.toString(),
                    onValueChange = {
                        hours = it.toIntOrNull() ?: 0
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Right)
                    }),
                    modifier = Modifier.width(85.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Hours")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                OutlinedTextField(
                    value = minutes.toString(),
                    onValueChange = {
                        minutes = it.toIntOrNull() ?: 0
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Right)
                    }),
                    modifier = Modifier.width(85.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Minutes")

            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {

                OutlinedTextField(
                    value = seconds.toString(),
                    onValueChange = {
                        seconds = it.toIntOrNull() ?: 0
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onNext = {
                        keyboardController?.hide()
                    }),
                    modifier = Modifier.width(85.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Seconds")

            }


        }

        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement=Arrangement.End,
            modifier=Modifier.width(277.dp)){

            VinylsButton(
                label = "Cancel",
                onClick = onClickCancelButton,
                type = ButtonType.TERTIARY,
                modifier = Modifier.width(100.dp)
            )
            // Botón para confirmar la selección
            Spacer(modifier = Modifier.width(5.dp))
            VinylsButton(
                label = "Save",
                onClick = onSaveButton,
                type = ButtonType.PRIMARY,
                modifier = Modifier.width(100.dp)
            )
        }

    }
}

@Preview
@Composable
fun TimeSelectorPreview() {
    CreateTaskPage()
}