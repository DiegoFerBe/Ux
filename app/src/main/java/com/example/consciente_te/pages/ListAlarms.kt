package com.example.consciente_te.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.consciente_te.components.ButtonType
import com.example.consciente_te.components.VinylsButton

@Composable
fun ListAlarmPage(
    onCreateAlarm:()->Unit={}
){
    Column(verticalArrangement= Arrangement.Center,horizontalAlignment=Alignment.CenterHorizontally,
        modifier=Modifier.fillMaxSize()) {
        Text(text = "There are not created alarms")
        VinylsButton(onClick = onCreateAlarm,label="Create Alarm",type = ButtonType.PRIMARY)
    }
    
}
@Preview
@Composable
fun PreviewListPage(){
    ListAlarmPage()
}