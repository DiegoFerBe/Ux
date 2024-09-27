package com.example.consciente_te.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.consciente_te.R
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomePage(
    onClickIngressButton: () -> Unit = {},
    onClickforgetButton: () -> Unit = {}
) {
    val textStateUsername = remember { mutableStateOf(TextFieldValue()) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        VinylsButton(
            icon = Icons.Outlined.Add,
            onClick = { /*TODO*/ },
            type = ButtonType.PRIMARY,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(Alignment.End)
        )
        Text(
            text = "Alarmas",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Caja con bordes circulares
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF758694), shape = RoundedCornerShape(15.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Caja circular con la hora
                Box(
                    modifier = Modifier
                        .size(240.dp, 60.dp)
                        .background(Color(0xFFF7E7DC), shape = RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "07:00 AM", color = Color(0xFF758694), fontSize = 28.sp)
                }

                // Switch deslizante
                var isChecked by remember { mutableStateOf(false) }
                Switch(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFFF7E7DC), // Color del "thumb" cuando está activado
                        checkedTrackColor = Color(0xFFF7E7DC), // Color del "track" cuando está activado
                        uncheckedThumbColor = Color(0xFFF7E7DC), // Color del "thumb" cuando está desactivado
                        uncheckedTrackColor = Color(0xFF405D72) // Color del "track" cuando está desactivado
                    )
                )

            }
        }




    /*
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = textStateUsername.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStateUsername.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Username") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )
        Row(
            modifier = Modifier.width(277.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Enter your username", // Texto del label
                color = Color.Gray, // Color del texto
                fontSize = 12.sp, // Tamaño del texto
                textAlign = TextAlign.Left,
                // Agrega un espacio desde el borde izquierdo
            )
        }

        val textStatePassword = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textStatePassword.value, // el valor del campo de texto
            onValueChange = { newValue ->
                textStatePassword.value = newValue // actualiza el valor del estado cuando cambia el texto
            },
            label = { Text("Password") }, // etiqueta del campo de texto
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp), // estilo del texto
            // indica que el campo de texto debe ser de una sola línea
            // otras propiedades como placeholder, enabled, isError, etc.
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                // Cuando se presiona "Siguiente", se enfoca el siguiente campo de texto
                keyboardController?.hide()
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
            Text(
                text = "Enter your password", // Texto del label
                color = Color.Gray, // Color del texto
                fontSize = 12.sp, // Tamaño del texto
                textAlign = TextAlign.Left,

                // Agrega un espacio desde el borde izquierdo
            )
        }



        Spacer(modifier = Modifier.height(16.dp))
        VinylsButton(
            onClick = onClickIngressButton,
            type = ButtonType.PRIMARY,
            label = stringResource(id = R.string.sign_in) ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 0.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        VinylsButton(
            onClick=onClickforgetButton,
            label = stringResource(id = R.string.sign_up),
            type = ButtonType.TERTIARY,

        )*/





    }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF758694))
                .height(100.dp)
                .padding(0.dp)
        ) {
            Spacer(Modifier.height(5.dp))

            Column(
                modifier=Modifier.width(80.dp)
            ) {
                VinylsButton(
                    icon = Icons.Outlined.AccessAlarm,
                    onClick = onClickIngressButton,
                    type = ButtonType.WHITE,
                    modifier = Modifier
                        .width(80.dp)
                        .height(48.dp)
                        .zIndex(1f)
                )
                Spacer(Modifier.height(10.dp))

                Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                ) { // Centra el contenido del Box
                    Divider(
                        color = Color.DarkGray,
                        thickness = 5.dp,
                        modifier = Modifier
                            .width(70.dp)
                            .padding(horizontal = 5.dp) // 5dp de padding a cada lado
                    )
                }
            }

            Column(
                modifier=Modifier.width(80.dp)
            ) {
                VinylsButton(
                    icon = ImageVector.vectorResource(id = R.drawable.tomato),
                    onClick = onClickIngressButton,
                    type = ButtonType.ALTERNATIVE,
                    modifier = Modifier
                        .width(80.dp)
                        .height(48.dp)
                )
                Spacer(Modifier.height(10.dp))
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) { // Centra el contenido del Box
                    Divider(
                        color = Color.White,
                        thickness = 5.dp,
                        modifier = Modifier
                            .width(70.dp)
                            .padding(horizontal = 5.dp) // 5dp de padding a cada lado
                    )
                }
            }

            Spacer(Modifier.height(5.dp))
        }
    }
    // Espaciador para empujar los botones hacia abajo

    // Caja para botones


}

@Preview (showBackground = true)
@Composable
fun HomePagePreview() {

    HomePage()
}