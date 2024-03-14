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
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegistryPage(
    onClickCancelButton: () -> Unit = {},
    onSignUpButton: ()->Unit={}
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ){
        val textStateUsername = remember { mutableStateOf(TextFieldValue()) }
        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        TextField(
            value = textStateUsername.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateUsername.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Name") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                focusManager.moveFocus(FocusDirection.Down)
            }),
            // indica que el campo de texto debe ser de una sola línea
            // otras propiedades como placeholder, enabled, isError, etc.
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Enter your name", // Texto del label
                color = Color.Gray, // Color del texto
                fontSize = 12.sp, // Tamaño del texto
                textAlign = TextAlign.Left,
                // Agrega un espacio desde el borde izquierdo
            )
        }
        val textStateEmail = remember { mutableStateOf(TextFieldValue()) }
        var isEmailValid by remember { mutableStateOf(true) }
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = textStateEmail.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateEmail.value = newValue
                isEmailValid=isValidEmail(newValue.text)

                 // actualiza el valor del estado cuando cambia el texto
            },
            isError = !isEmailValid,
            label = { Text("Email") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                focusManager.moveFocus(FocusDirection.Down)
            }),
            // indica que el campo de texto debe ser de una sola línea
            // otras propiedades como placeholder, enabled, isError, etc.
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Column {
                if (!isEmailValid) {
                    Text(
                        text = "Invalid Email",
                        color = Color.Red,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Left,
                    )
                }else{
                    Text(
                        text = "Enter a valid email", // Texto del label
                        color = Color.Gray, // Color del texto
                        fontSize = 12.sp, // Tamaño del texto
                        textAlign = TextAlign.Left,
                        // Agrega un espacio desde el borde izquierdo
                    )
                }


            }

        }

        val textStatePassword = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(30  .dp))
        TextField(
            value = textStatePassword.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStatePassword.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Password") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            // indica que el campo de texto debe ser de una sola línea
            // otras propiedades como placeholder, enabled, isError, etc.
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                focusManager.moveFocus(FocusDirection.Down)
            }),
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Enter your password", // Texto del label
                color = Color.Gray, // Color del texto
                fontSize = 12.sp, // Tamaño del texto
                textAlign = TextAlign.Left,

                // Agrega un espacio desde el borde izquierdo
            )
        }

        val textStatePasswordConf = remember { mutableStateOf(TextFieldValue()) }
        var validPassword by remember { mutableStateOf(true) }
        Spacer(modifier = Modifier.height(30  .dp))
        TextField(
            value = textStatePasswordConf.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStatePasswordConf.value = newValue // actualiza el valor del estado cuando cambia el texto
                validPassword=validatePassword(newValue.text,textStatePassword.value.text)
                            },
            label = { Text("Confirm your Password") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError=!validPassword,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                focusManager.moveFocus(FocusDirection.Down)
            }),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            if(!validPassword){
                Text(
                    text = "Password Doesnt Match", // Texto del label
                    color = Color.Red, // Color del texto
                    fontSize = 12.sp, // Tamaño del texto
                    textAlign = TextAlign.Left,

                    // Agrega un espacio desde el borde izquierdo
                )
            }else{
                Text(
                    text = "Confirm your password", // Texto del label
                    color = Color.Gray, // Color del texto
                    fontSize = 12.sp, // Tamaño del texto
                    textAlign = TextAlign.Left,

                    // Agrega un espacio desde el borde izquierdo
                )
            }

        }

        val textStateAddress = remember { mutableStateOf(TextFieldValue()) }
        Spacer(modifier = Modifier.height(30  .dp))
        TextField(
            value = textStateAddress.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateAddress.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Address") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                keyboardController?.hide()
            }),
            // indica que el campo de texto debe ser de una sola línea
            // otras propiedades como placeholder, enabled, isError, etc.
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Enter your address", // Texto del label
                color = Color.Gray, // Color del texto
                fontSize = 12.sp, // Tamaño del texto
                textAlign = TextAlign.Left,

                // Agrega un espacio desde el borde izquierdo
            )
        }
        Spacer(modifier = Modifier.height(30  .dp))
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.width(277.dp))
        Spacer(modifier = Modifier.height(30  .dp))
        Row(
            horizontalArrangement=Arrangement.End,
            modifier=Modifier.width(277.dp)
        ){
            VinylsButton(
                onClick = onClickCancelButton ,
                type = ButtonType.SECONDARY,
                label = "Cancel",
            )
            Spacer(modifier = Modifier.width(5  .dp))
            VinylsButton(
                onClick = onSignUpButton,
                type = ButtonType.PRIMARY,
                label = "Sign up" ,

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

