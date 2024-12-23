package com.example.pojarprotektmobileapp.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

class MyDialog {

    @Composable
    fun RetroDialog(title:Int,message:String,
                            backgroundColor: Color = Color(0xffcccccc),
                            onDismissRequest : () -> Unit) {

        var servermsg = message

            Dialog(onDismissRequest = onDismissRequest) {
                Box(modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(color = backgroundColor)) {
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(Blue)
                                .padding(start = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {

                            Text(text = ""+title, color = Color.White, fontFamily = FontFamily.Monospace)

                            Surface(onClick = onDismissRequest,
                                shape = RectangleShape,
                                modifier = Modifier.padding(5.dp,2.dp,2.dp,5.dp),
                                color = backgroundColor) {
                                Button(onClick = onDismissRequest
                                , shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                                    Icon(Icons.Default.Close, contentDescription = "Close")
                                }

                            }
                        }

                        Row(
                            Modifier.padding(
                                horizontal = 10.dp, vertical = 20.dp
                            ), horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically) {

                            Icon( Icons.Filled.Info, contentDescription = "Error",
                                tint = Red, modifier = Modifier.size(48.dp))

                            if(servermsg.isEmpty()) {
                                when (title) {
                                    200 -> servermsg = "Успех"
                                    400 -> servermsg = "Неправилна заявка"
                                    401 -> servermsg = "Неоторизиран достъп"
                                    403 -> servermsg = "Забранен ресурс"
                                    404 -> servermsg = "Не е намерен такъв ресурс"
                                    500 -> servermsg = "Сървърна грешка"
                                }
                            }
                            Text(text = servermsg, color = Blue, fontFamily = FontFamily.Monospace)
                        }

                        Surface(modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = onDismissRequest ,
                            shape = RectangleShape,
                            color = backgroundColor
                           // , border = BorderStroke(Dp.Hairline, Color.Black)
                        ) {
                            Button(onClick = onDismissRequest,Modifier.padding(bottom = 10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Blue)) {
                                Text(text = "Ok", fontFamily = FontFamily.Monospace,
                                    modifier = Modifier
                                        .widthIn(120.dp)
                                        .padding(vertical = 8.dp),
                                    textAlign = TextAlign.Center)
                            }

                        }
                    }
                }
            }
        }


}