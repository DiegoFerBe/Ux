package com.example.consciente_te.pages

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.consciente_te.R
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton

@Composable
fun MediaPage(

    onClickIngressButton: () -> Unit = {},
    onClickforgetButton: () -> Unit = {},
    onClickConcentrateButton : ()->Unit={},
    onclickSettingButton: ()->Unit={}
){
    val textStateUsername = remember { mutableStateOf(TextFieldValue()) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isStarted by remember { mutableStateOf(false) }
    var timeElapsed by remember { mutableStateOf(0) }
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        VinylsButton(
            icon = Icons.Outlined.Settings,
            onClick = onclickSettingButton,
            type = ButtonType.PRIMARY,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(40.dp))


            Box(
            modifier = Modifier
                .fillMaxSize()


            // Centrar el contenido
        ) {
            Column(
                modifier=Modifier.fillMaxWidth(),
                 // Centra horizontalmente
                horizontalAlignment = Alignment.CenterHorizontally, // Centrar el contenido del Column
                // Centrar verticalmente
            ) {
                // Círculo grande con texto "Iniciar"
                Box(
                    modifier = Modifier
                        .size(320.dp) // Tamaño del círculo
                        .background(
                            if (isStarted) Color(0xFFE07A5F) else Color(0xFF758694),
                            shape = CircleShape
                        )
                        .clickable {
                            isStarted = !isStarted
                            if (isStarted) {
                                timeElapsed = 0
                                // Simular incremento del tiempo (puedes agregar tu lógica de temporizador aquí)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isStarted) "Tiempo: 25:00" else "Iniciar", // Muestra tiempo si está iniciado
                        color = Color.White,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                }

                // Espacio entre el círculo y el botón
                Spacer(modifier = Modifier.height(20.dp))

                // Botón debajo del círculo
                VinylsButton(
                    label = "Modo Concentración",
                    onClick = onClickConcentrateButton,
                    type = ButtonType.PRIMARY,
                    modifier = Modifier.width(300.dp)
                )
            }
        }






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
                    onClick = onClickforgetButton,
                    type = ButtonType.ALTERNATIVE,
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
                        color = Color.White,
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
                    type = ButtonType.WHITE,
                    modifier = Modifier
                        .width(80.dp)
                        .height(48.dp)
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

            Spacer(Modifier.height(5.dp))
        }
    }
    // Espaciador para empujar los botones hacia abajo

    // Caja para botones
}



data class CardItem(
    val imageUrl: String,
    val title: String,
    val description: String,
    val videoUrl: String
)
@Preview
@Composable
fun StackedCardListPreview() {
    MediaPage()
}

