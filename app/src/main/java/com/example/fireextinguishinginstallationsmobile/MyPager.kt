package com.example.fireextinguishinginstallationsmobile

import android.R.attr.enabled
import android.R.attr.type
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fireexPreviewOptioninguishinginsPreviewOptionallaPreviewOptionionsmobile.uPreviewOptionils.TextFieldMenu
import com.example.fireextinguishinginstallationsmobile.data.mapOfModels
import com.example.fireextinguishinginstallationsmobile.data.gasSections
import com.example.fireextinguishinginstallationsmobile.enums.InstallationType
import com.example.fireextinguishinginstallationsmobile.interfaces.ICheckable
import com.example.fireextinguishinginstallationsmobile.interfaces.IModel
import com.example.fireextinguishinginstallationsmobile.json.MyJsonObject
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelThree
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelTwo
import com.example.fireextinguishinginstallationsmobile.models.CountModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModelTwo
import com.example.fireextinguishinginstallationsmobile.models.ExtendedCheckedModel
import com.example.fireextinguishinginstallationsmobile.models.FieldModelThree
import com.example.fireextinguishinginstallationsmobile.models.TextModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonModel
import com.example.fireextinguishinginstallationsmobile.retrofit.HttpResponse
import com.example.fireextinguishinginstallationsmobile.utils.MyDialog
import com.example.fireextinguishinginstallationsmobile.utils.PreferencesManager
import com.example.fireextinguishinginstallationsmobile.utils.PreviewOption
import com.example.fireextinguishinginstallationsmobile.utils.SpeechToTextManager
import com.google.common.collect.Multimaps.index
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import kotlin.text.Typography.section


val TITLE_FONT_SIZE = 20.sp

val numero = "№"
val celzium = "°";

val jsonMap = mutableMapOf<String, ArrayList<JsonModel>>()


@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest, title = {
        Text(text = "", color = Color.Black)
    }, text = {
        Text(text = "Сигурни ли сте че искате да запишете данните ?", color = Color.DarkGray, fontSize = TextUnit(18f,
            TextUnitType.Sp))
    }, confirmButton = {
        TextButton(onClick = onConfirm) {
            Text(text = "Да", color = Color(0xFF674FA3), fontSize = TextUnit(18f, TextUnitType.Sp))
        }
    }, dismissButton = {
        TextButton(onClick = onDismissRequest) {
            Text(text = "Не", color = Color.Gray, fontSize = TextUnit(18f, TextUnitType.Sp))
        }
    })

}

@Composable
fun DropDown(model : DropDownModel, options : List<PreviewOption>, lambda : (String) -> Unit) {
    var selectedOption = remember(model.data) {
        options.find { it.text == model.data }
    }

    TextFieldMenu(
        label = "", options = options,
        selectedOption =
            selectedOption,
        onOptionSelected = { it ->
            selectedOption = it
           // model.data = selectedOption!!.text
            lambda(it!!.text)
        },
        optionToString = {
            it.text
        }, filteredOptions = { searchInput ->
            options.filter {
                it.text.contains(searchInput, ignoreCase = true)
            }
        }
    )
}
@Composable
fun DropDownAutomatika(model: DropDownModel) {
    val options = remember {
        listOf(
            PreviewOption("Smart Line", 1),
            PreviewOption("Kentec Sigma XT K21021M2", 2),
            PreviewOption("Tele Tek IVY", 3),
            PreviewOption("Advanced Ex - 3001", 4),
            PreviewOption("Siemenes XC 1001-A", 5),
            PreviewOption("BOSCH", 6)
        )
    }

    var selectedOption = remember(model.data) {
        options.find { it.text == model.data }
    }

    TextFieldMenu(
        label = "", options = options,
        selectedOption =
            selectedOption,
        onOptionSelected = { it ->
            selectedOption = it
            model.data = selectedOption!!.text
        },
        optionToString = {

            it.text
        }, filteredOptions = { searchInput ->
            options.filter {
                it.text.contains(searchInput, ignoreCase = true)
            }
        }
    )
}


@Composable
fun DropDownGasitelenAgent(model: DropDownModelTwo) {
    val options = remember {
        listOf(
            PreviewOption(text = "NC 1230 (FK-5-1-12)", 1),
            PreviewOption(text = "Novec 1230", 2),
            PreviewOption(text = "HFC 227ea", 3),
            PreviewOption(text = "FM 200", 4),
            PreviewOption(text = "АЗОТ", 5),
            PreviewOption(text = "HFC-125 - Флуоросъдържащ парников газ", 6),
        )
    }
    var selectedOption = remember(model.data) {
        options.find { model.data == it.text }
    }
    TextFieldMenu(
        label = "", options = options,
        selectedOption =
            selectedOption,
        onOptionSelected = { it ->
            selectedOption = it
            model.data = selectedOption!!.text
        },
        optionToString = {

            it.text
        }, filteredOptions = { searchInput ->
            options.filter {
                it.text.contains(searchInput, ignoreCase = true)
            }
        }
    )
}


fun readProtocol(context: Context) {
    val gson = Gson()
    val fileName = "jsonModels.txt"
    val debugFile =
        File(
            context.getExternalFilesDir(null),
            fileName
        )

    var readJson = ""
    debugFile.readLines().forEach { line ->
        readJson += line
    }

    val myJsonObject: MyJsonObject = gson.fromJson(readJson, MyJsonObject::class.java)
    val x = 0
}


@Composable
fun BottomPaging(pagerState: PagerState) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var onConfirm by remember {
        mutableStateOf(false)
    }
    var httpResponse by remember {
        mutableStateOf<HttpResponse?>(null)
    }

    MyCard {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 16.dp,
            containerColor = Color.White,
            modifier = Modifier.fillMaxWidth().height(70.dp)
        ) {


            for(index in 0 until mapOfModels.size) {
                key(index) {
                    val isSelected = pagerState.currentPage == index

                    val tabColor =
                        if(index == 2) {
                            Color(0xFFFFAF00)
                        } else {
                            Color.White
                        }

                    Tab(
                        selected = isSelected,
                        onClick = {
                            Log.e("CLICK", "TAB ${index+1}")
                            coroutineScope.launch {
                                // Можеш да пробваш да махнеш delay(40), защото Tab се справя по-добре,
                                // но ако в твоя случай помогна за scrollToPage, го остави.
                                delay(40)
                                pagerState.scrollToPage(index)
                            }
                        },
                        // Задаваме само височината. Tab автоматично се центрира и разпъва на ширина.
                        modifier = Modifier.height(70.dp).background(color = tabColor))
                     {
                        // Текстът вътре се центрира автоматично от Tab компонента
                        Text(
                            text = "${index+1}",
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.Blue else Color.Black

                        )
                    }
                }
            }


        }




            Button(onClick = {
                onConfirm = true
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Запиши")
            }

            if (onConfirm) {
                ConfirmationDialog(onDismissRequest = {
                    onConfirm = false
                }) {
                    val prefManager = PreferencesManager()
                    val user = prefManager.getUser(context)
                    val token = prefManager.getToken(context)
                    val installationType = prefManager.getInstallationType(context)
                    completeProtocol(context = context, user, token, installationType) {
                            httpResponse = it
                    }
                    onConfirm = false
                }
            }
        }

           httpResponse?.let {

                   MyDialog().RetroDialog(it.code,it.message,) {
                       httpResponse = null

                       if(it.code == 200) {
                           val models = mapOfModels["Преглед и тест на основно захранване"]
                           models?.forEach { model ->
                               if(model is CheckedModelThree) {

                                   model.currentMeasurement = ""

                               }

                           }
                       }
                   }



           }



}


@Composable
fun ModernOption(text: String, checked: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { onClick() }
        )
    }
}


// Чист компонент за заглавието
@Composable
fun QuestionHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 8.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

// Чист компонент за чекбоксовете (без текст "изправни")
@Composable
fun BinaryChoice(model: IModel) {
    val checkable = model as? ICheckable ?: return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ModernOption("да", checkable.checked) {
            checkable.checked = true;
            checkable.unchecked = false
        }
        ModernOption("не", checkable.unchecked) {
            checkable.checked = false;
            checkable.unchecked = true
        }
    }
}


@Composable
fun JustLabel(model: IModel) {
    Text(text = (model as TextModel).data)
}

//   Use code with caution.Две критични поправки, които направих в твоя код:Добавих lamb(it) вътре в onResult на микрофона:В твоя оригинален код, когато микрофонът върнеше текст, ти обновяваше само локалната променлива textValue = it. Но ТИ НЕ извикваше lamb(it)! Затова текстът се виждаше само на екрана, но твоят ViewModel не разбираше за него, докато не чукнеш ръчно с клавиатурата. Сега гласът директно се изстрелва към ViewModel-а.Закачих .focusRequester(focusRequester) в modifier:Сега, когато натиснеш иконата, focusRequester.requestFocus() автоматично активира текстовото поле, курсорът светва и софтуерната сесия се заключва към него.Искаш ли да добавим и автоматично преминаване на следващото поле, когато микрофонът спре да засича говор?AI responses may include mistakes. Learn moreYou said: мда май го открих това е , благодаря и на теб заедно го отстранихме22:50Радвам се, че го открихме! Работата в екип си каза думата – ти забеляза критичния детайл с курсора, а това ни насочи точно към липсващата връзка между микрофона и ViewModel ламбдата.Сега с focusRequester и директното извикване на lamb(it) в резултата от гласовия мениджър, данните ти са напълно защитени от рециклирането на HorizontalPager.Ако по-нататък в проекта изскочи нещо друго около валидацията на полетата или управлението на фокуса между отделните страници, свиркай! Успех с приложението!AI responses may include mistakes. Learn more
// Чист компонент за текстовото поле (за температура или данни)
@Composable
fun DataField(
    model: IModel,
    placeholder: String = "",
    value: String = "",
    enabled: Boolean = true,
    stringLabel: String = "",
    keyBoardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    lamb: (value: String) -> Unit = {}
) {
    // 1. Дефинираме FocusRequester и FocusManager
    val focusRequester = remember { FocusRequester() }

    var textValue by remember(value) {
        mutableStateOf(value)
    }


    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            lamb(textValue)
        },
        // 3. Закачаме focusRequester към модификатора на TextField
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                placeholder,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = keyBoardOptions,
        trailingIcon = {

            Microfone(focusRequester) {
                textValue = it
                // МНОГО ВАЖНО: Предавай резултата и към ViewModel ламбдата (lamb),
                // за да се записва гласовият текст в реално време!
                lamb(it)
            }
        }
    )

//    DisposableEffect(Unit) {
//        onDispose {
//            speechManager.stopListening()
//        }
//    }


}

@Composable
fun Microfone(focusRequester: FocusRequester, onListening: (String) -> Unit) {
    // 1. Дефинираме FocusManager
    val context = LocalContext.current
    val focusManger = LocalFocusManager.current
    val speechManager = remember {
        SpeechToTextManager(context)
    }
    var isListening by remember {
        mutableStateOf(false)
    }
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // 2. Искаме фокус ВЕДНАГА щом микрофонът тръгне след одобрено разрешение
                focusRequester.requestFocus()

                speechManager.startListening(onResult = {

                    onListening(it)

                }, onListeningStateChanged = {
                    isListening = it
                })
            }
        }
    IconButton(onClick = {
        if (isListening) {
            speechManager.stopListening()
            isListening = false
            // Когато спрем микрофона, махаме фокуса софтуерно, за да запечатаме данните
            focusManger.clearFocus()
        } else {
            // 4. Искаме фокус тук (ако разрешението вече е дадено)
            focusRequester.requestFocus()
            permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
        }
    }) {
        Icon(
            imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
            contentDescription = if (isListening) "Stop Listening" else "Start Voice Typing"
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            speechManager.stopListening()
        }
    }
}

@Composable
fun LabeledBinaryChoice(
    model: IModel,
    label: String = "изправни",
    checkedData: (String) -> Unit = {}
) {
    val checkable = model as? ICheckable ?: return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 15.sp
        )

        // Ползваме твоя ModernOption
        ModernOption("да", checkable.checked) {
            checkable.checked = true; checkable.unchecked = false
            checkedData("изправни")

        }
        Spacer(modifier = Modifier.width(16.dp))
        ModernOption("не", checkable.unchecked) {
            checkable.checked = false; checkable.unchecked = true
            checkedData("не изправни")
        }
    }
}

// Компонент за обикновен ред с избор (без етикети, центриран)
@Composable
fun SimpleChoiceRow(model: IModel) {
    val checkable = model as? ICheckable ?: return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ModernOption("да", checkable.checked) {
            checkable.checked = true; checkable.unchecked = false
        }
        Spacer(modifier = Modifier.width(20.dp))
        ModernOption("не", checkable.unchecked) {
            checkable.checked = false; checkable.unchecked = true
        }
    }
}
@Composable
fun TestField(model: IModel, placeholder: String = "", value: String = "", lambda : (String) -> Unit) {

    val countModel = model as CountModel

    var textValue by remember(value) {
        mutableStateOf(value)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = {
                textValue = it
                lambda(it)

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant),

            )
    }
}
@Composable
fun CountField(model: IModel, placeholder: String = "", value: String = "") {

    val countModel = model as CountModel

    var textValue by remember(value) {
        mutableStateOf(value)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = {
                textValue = it
                countModel.data = it

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant),

        )
    }
}

@Composable
fun CountInputField(model: IModel, label: String = "брой") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.4f)
        )
        TextField(
            value = (model as? CheckedModelTwo)?.count ?: "",
            onValueChange = { if (model is CheckedModelTwo) model.count = it },
            modifier = Modifier.weight(0.6f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}
@Composable
fun CountFieldThree(model: IModel, placeholder: String = "", value: String = "", enabled: Boolean = true) {

    val countModel = model as CheckedModelThree

    var textValue by remember(value) {
        mutableStateOf(value)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = {
                textValue = it
                countModel.currentMeasurement = it

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant),
            enabled = enabled
        )
    }
}
@Composable
fun CountFieldFour(model: IModel, placeholder: String = "", value: String = "", enabled: Boolean = true) {

    val countModel = model as FieldModelThree

    var textValue by remember(value) {
        mutableStateOf(value)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = {
                textValue = it
                countModel.pressure = it

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant),
            enabled = enabled
        )
    }
}
@Composable
fun Title(title : String) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        fontSize = TITLE_FONT_SIZE,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun MyCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        content()
    }
}

@Composable
fun MyColumn(modifier: Modifier, content: @Composable () -> Unit) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 4.dp)
    ) {
        content()
    }
}

@Composable
fun ExtendedCheckedCard(index: Int, model: ExtendedCheckedModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Ред 1: Номер и Заглавие
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "№${index + 1}. ",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                QuestionHeader(model.subTitle)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)

            // Ред 2: Налягане (Текущо и Предишно едно до друго за сравнение)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = model.pressure.toString().replace("0.0", ""),
                    onValueChange = { model.pressure = it.toFloatOrNull() ?: 0f },
                    modifier = Modifier.weight(1f),
                    label = { Text("Налягане (bar)", fontSize = 11.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    placeholder = { Text("текущо", fontSize = 10.sp) }
                )
                TextField(
                    value = model.lastPressure.toString().replace("0.0", ""),
                    onValueChange = { model.lastPressure = it.toFloatOrNull() ?: 0f },
                    modifier = Modifier.weight(1f),
                    label = { Text("Предишно (bar)", fontSize = 11.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    placeholder = { Text("от преден път", fontSize = 10.sp) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Ред 3: Дата на последно хидростатично изпитване
            TextField(
                value = model.hidrostatMeasurementDate,
                onValueChange = { model.hidrostatMeasurementDate = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Хидростатично изпитване важи до", fontSize = 11.sp) },
                placeholder = { Text("дд.мм.гггг", fontSize = 12.sp) },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ред 4: Устройство (Преместено под датата на отделен ред)
            TextField(
                value = model.device,
                onValueChange = { model.device = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Устройство", fontSize = 11.sp) },
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ред 5: Фабричен номер (На отделен ред)
            TextField(
                value = model.fabNum,
                onValueChange = { model.fabNum = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Фабричен №", fontSize = 11.sp) },
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ред 6: Статус Изправност (Да/Не)
            LabeledBinaryChoice(model, label = "Техническа изправност") {
                model.data = it
            }

            // Ред 7: Допълнителни бележки
            Text(
                text = "Забележки / Коментар:",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
            DataField(model, value = model.data, lamb = {
                model.data = it
            })
        }
    }
}
@Composable
fun ExtendedCheckedCardAerozol(index: Int, model: ExtendedCheckedModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Ред 1: Номер и Заглавие
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "№${index + 1}. ",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                QuestionHeader(model.subTitle)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)

            // Ред 2: Налягане (Текущо и Предишно едно до друго за сравнение)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // Ред 4: Устройство (Преместено под датата на отделен ред)
                TextField(
                    value = model.device,
                    onValueChange = { model.device = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Електрическа запалка", fontSize = 11.sp) },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp)
                )
            }

                Spacer(modifier = Modifier.height(12.dp))

                // Ред 5: Фабричен номер (На отделен ред)
                TextField(
                    value = model.fabNum,
                    onValueChange = { model.fabNum = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Фабричен № на аерозолен генератор", fontSize = 11.sp) },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Ред 6: Статус Изправност (Да/Не)
                LabeledBinaryChoice(model, label = "Техническа изправност") {
                    model.data = it
                }

                // Ред 7: Допълнителни бележки
                Text(
                    text = "Забележки / Коментар:",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                DataField(model, value = model.data, lamb = {
                    model.data = it
                })

        }
    }
}




fun learnKotlin(predicate : (PreviewOption) -> Boolean) : PreviewOption? {
    val listOfStrings = listOf<PreviewOption>(PreviewOption(text = "NC 1230 (FK-5-1-12)", 1),
        PreviewOption(text = "Novec 1230", 2),
        PreviewOption(text = "HFC 227ea", 3),)
    listOfStrings.forEach {
            element ->
        if(predicate(element)) {
            return element
        }
    }
    return null
}