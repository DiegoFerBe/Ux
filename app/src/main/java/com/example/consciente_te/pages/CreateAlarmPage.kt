package com.example.consciente_te.pages

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

@Composable
fun CreateAlarmPage(
    onClickCancelButton: () -> Unit = {},
    onSaveButton: ()->Unit={}
) {
    val textStateTaskName = remember { mutableStateOf(TextFieldValue()) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textStateTaskName.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateTaskName.value =
                    newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Task name") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            modifier=Modifier.fillMaxWidth()
        )
        val state = rememberTimePickerState()
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text("Enter Time",modifier=Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(20.dp))
            TimeInput(
                state = state,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        var selectedOption by remember { mutableStateOf(true) }
        Row(modifier=Modifier.fillMaxWidth().height(50.dp)) {
            Column(
                modifier=Modifier.fillMaxHeight(),
                horizontalAlignment=Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Alarm sound")
            }
            Column(modifier=Modifier.fillMaxSize(),
                horizontalAlignment=Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Switch(
                    checked = selectedOption,
                    onCheckedChange = {
                        selectedOption = it
                    }
                )

            }

        }
        var selectedOption2 by remember { mutableStateOf(true) }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier=Modifier.fillMaxWidth().height(50.dp)) {

            Column(
                modifier=Modifier.fillMaxHeight(),
                horizontalAlignment=Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Pospone")
            }
            Column(modifier=Modifier.fillMaxSize(),
                horizontalAlignment=Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Switch(
                    checked = selectedOption2,
                    onCheckedChange = {
                        selectedOption2 = it
                    }
                )

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement=Arrangement.End,
            modifier=Modifier.fillMaxWidth()
            
            ){

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
                onClick =  onSaveButton ,
                type = ButtonType.PRIMARY,
                modifier = Modifier.width(100.dp)
            )
        }



    }
}
enum class Option {
    First, Second, Third
}
@Preview
@Composable
fun CreateAlarmPreview(){
    CreateAlarmPage()
}

