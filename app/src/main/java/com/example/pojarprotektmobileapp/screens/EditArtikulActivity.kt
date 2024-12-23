package com.example.pojarprotektmobileapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pojarprotektmobileapp.R
import com.example.pojarprotektmobileapp.http.RetrofitInstance
import com.example.pojarprotektmobileapp.models.ArtikulModel
import com.example.pojarprotektmobileapp.models.ArtikulViewModel
import com.example.pojarprotektmobileapp.models.Client
import com.example.pojarprotektmobileapp.models.GreyArtikulViewModel
import com.example.pojarprotektmobileapp.models.HttpResponse
import com.example.pojarprotektmobileapp.models.MyViewModel
import com.example.pojarprotektmobileapp.ui.theme.PojarprotektMobileAppTheme
import com.example.pojarprotektmobileapp.ui.theme.buttonColor
import com.example.pojarprotektmobileapp.utils.MyDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditArtikulActivity : ComponentActivity() {
    var action : String = ""
    var artikul : String? = ""
    var kontragent : String? = ""
    var invoice : String? = ""
    var med : String? = ""
    var price : String? = ""
    var weight : String? = ""
    var category: String? = ""
    var brand: String? = ""
    var viewModelType : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuActions = listOf("","insert_artikul","edit_artikul_price",
            "edit_artikul_quantity","rename_artikul",
            "delete_artikul","insert_extinguisher","edit_extinguisher_price",
            "edit_extinguisher_quantity", "rename_extinguisher",
            "delete_extinguisher"
        )
        val intent = intent
        val actionIntent = intent.getStringExtra("action")
        artikul = intent.getStringExtra("artikul")
        kontragent = intent.getStringExtra("kontragent")
        invoice = intent.getStringExtra("invoice")
        med = intent.getStringExtra("med")
        price = intent.getStringExtra("price")
        weight =  intent.getStringExtra("weight")
        category = intent.getStringExtra("category")
        brand =  intent.getStringExtra("brand")
        viewModelType = intent.getIntExtra("viewModelType",0);

        menuActions.forEachIndexed { index, s ->
            if(actionIntent.equals(s)) {
                action = s//menuActions[index]
            }
        }


        
        setContent() {
            PojarprotektMobileAppTheme(dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {

                    var viewModel : MyViewModel = viewModel<MyViewModel>()
                    when(viewModelType) {
                       0 -> viewModel = viewModel<ArtikulViewModel>()
                       1 -> viewModel = viewModel<MyViewModel>()
                       2 -> viewModel = viewModel<GreyArtikulViewModel>()
                    }


                    when(action) {
                        "insert_artikul" -> InsertArtikulsUI(viewModel)
                        "rename_artikul" -> RenameArtikulsUI(viewModel)
                        "delete_artikul" -> DeleteArtikulsUI(viewModel)
                        "edit_artikul_quantity" -> EditArtikulsQuantityUI(viewModel)
                        "edit_artikul_price" -> EditArtikulsPriceUI(viewModel)
                        "insert_extinguisher" -> InsertExtinguishersUI(viewModel)
                        "edit_extinguisher_price" -> EditExtinguisherPriceUI(viewModel)
                        "edit_extinguisher_quantity" -> EditExtinguisherQuantityUI(viewModel)
                        "delete_extinguisher" -> RemoveExtinguisherUI(viewModel)
                    }
                }
            }
        }
    }

    @Composable
    private fun RemoveExtinguisherUI(viewModel: MyViewModel) {
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Вид", color = Color.Black)
                Text(text = artikul!!,color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Вместимост", color = Color.Black)
                Text(text = weight!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Категория",color = Color.Black)
                Text(text = category!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Марка", color = Color.Black)
                Text(text = brand!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Контрагент", color = Color.Black)
                Text(text = kontragent!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Фактура", color = Color.Black)
                Text(text = invoice!!, color = Color.Black, textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(1f))
            }
            Button(onClick = {

                  viewModel.deleteExtinguisher(this@EditArtikulActivity, artikul!!,weight!!,
                      category!!,brand!!,invoice!!,kontragent!!
                  ) {
                     httpResponse = it
                     showDialog = true
                  }
            }, modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(20.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(buttonColor)) {
                Text(text = "Изтрий")
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = R.drawable.fire_extinguisher), contentDescription = "ExtinguisherLogo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f),
                tint = Color(0x23FF0000))
        }
    }

    @Composable
    private fun EditExtinguisherQuantityUI(viewModel: MyViewModel) {
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
           Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Контрагент:", color = Color.Black)
                   Text(text = kontragent!!, color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Фактура:", color = Color.Black)
                   Text(text = invoice!!, color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Вид",color = Color.Black)
                   Text(text = artikul!!, color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Вместимост", color = Color.Black)
                   Text(text = weight!!, color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Категория", color = Color.Black)
                   Text(text = category!!,color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               Row(modifier = Modifier.padding(20.dp)) {
                   Text(text = "Марка", color = Color.Black)
                   Text(text = brand!!,color = Color.Black, textAlign = TextAlign.End,
                   modifier = Modifier.fillMaxWidth(1f))
               }
               val quantity = createField(placeholder = "Количество", text = "", maxWidth = 0.4f)
               Button(onClick = {
                      viewModel.editExtinguisherQuantity(this@EditArtikulActivity,
                          quantity,kontragent!!,invoice!!, artikul!! ,weight!!,
                          category!!,brand!!
                      ) {
                         httpResponse = it
                         showDialog = true
                      }

               }, colors = ButtonDefaults.buttonColors(buttonColor),
               modifier = Modifier
                   .height(IntrinsicSize.Max)
                   .padding(20.dp)
                   .height(55.dp)) {
                   Text(text = "Редактирай")
               }
           }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = R.drawable.fire_extinguisher), contentDescription = "ExtinguisherLogo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f),
                tint = Color(0x23FF0000))
        }
    }

    @Composable
    private fun EditExtinguisherPriceUI(viewModel: MyViewModel) {
        val deliveryValue = remember {
            mutableStateOf("")
        }
        val currentValue = remember {
            mutableStateOf("")
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Контрагент", color = Color.Black)

                Text(text = kontragent!!, color = Color.Black, textAlign = TextAlign.End,
                 modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "No Фактура:", color = Color.Black)

                Text(text = invoice!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))

            }
            /*Row(modifier = Modifier.padding(20.dp)) {
                val formatter = SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH)
                val date = Date()
                val currentDate = formatter.format(date)
                Text(text = "Дата:",color = Color.Black)
                Text(text = currentDate,color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }*/
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Вид", color = Color.Black)
                Text(text = artikul!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Вместимост",color = Color.Black)
                Text(text = weight!!,color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Категория",color = Color.Black)
                Text(text = category!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = "Марка", color = Color.Black)
                Text(text = brand!!, color = Color.Black, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(1f))
            }

            Row(modifier = Modifier.padding(0.dp), horizontalArrangement =
            Arrangement.SpaceBetween) {
                Button(onClick = {
                    val item = "$artikul ( Нов ) $weight"
                    viewModel.getArtikulValue(this@EditArtikulActivity,"DeliveryArtikulsDB2",item) {
                        deliveryValue.value = it.toString()
                    }
                }, modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(20.dp)
                    .height(55.dp)
                , colors = ButtonDefaults.buttonColors(buttonColor)) {
                    Text(text = "Виж дост. цена")
                }
                createField2(placeholder = "Дост. цена", deliveryValue, maxWidth = 1f)
            }
            Row(modifier = Modifier.padding(0.dp), horizontalArrangement =
            Arrangement.SpaceBetween) {
               Button(onClick = {
                   viewModel.getExtinguishersPrice(context = this@EditArtikulActivity,
                       artikul!!,
                       weight!!,
                       category!!,
                       brand!!
                   ) {
                       currentValue.value = it.toString()
                   }

               },modifier = Modifier
                   .height(IntrinsicSize.Max)
                   .padding(20.dp)
                   .height(55.dp), colors = ButtonDefaults.buttonColors(buttonColor)) {
                   Text(text = "Виж тек. цена")
               }
                createField2(placeholder = "Текуща цена", currentValue, maxWidth = 1f)
            }

            Button(onClick = {

                             viewModel.editExtinguisherPrice(this@EditArtikulActivity,
                                 currentValue.value, "0", artikul!!,weight!!,category!!,brand!!,
                                 kontragent!!,invoice!!
                             ) {
                                        httpResponse = it
                                        showDialog = true
                             }
            },modifier =
            Modifier
                .height(IntrinsicSize.Max)
                .padding(20.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(buttonColor)) {
                Text(text = "Редактирай")
            }

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = R.drawable.fire_extinguisher), contentDescription = "ExtinguisherLogo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f), tint = Color(0x23FF0000))
        }

    }
    @Composable
    private fun InsertExtinguishersUI(viewModel : MyViewModel) {

        val deliveryValue = remember {
            mutableStateOf("")
        }
        val currentValue = remember {
            mutableStateOf("")
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            val kontragent = createClientsDropdown(viewModel = viewModel)
            val invoice = createField("No Фактура",invoice!!,0.7f)

            val formatter = SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH)
            val date = Date()
            val currentDate = formatter.format(date)

            val dateVal = createField(placeholder = "Дата",currentDate, 0.7f)
            val type = createDropdownField(options = listOf("Пожарогасител Прахов BC",
                "Пожарогасител Прахов ABC","Пожарогасител Воден","Пожарогасител Водопенен","Пожарогасител CO2"),
                "","Вид")
            val weight = createDropdownField(options = listOf("1 кг","2 кг","6 кг","12 кг","25 кг","50 кг","100 кг"),
                "","Вместимост")
            val category = createDropdownField(options = listOf("2","4"), med = "", placeholder = "Категория")
            val brand = createDropdownField(options = listOf("Пожарогасител","Ятрус","Файър Килър"), med = "", placeholder = "Марка")
            val quantity = createField(placeholder = "К-во", text = "", maxWidth = 0.6f)
            Row(modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth(1f)) {
                Button(onClick = {

                   val item = "$type ( Нов ) $weight"
                   viewModel.getArtikulValue(this@EditArtikulActivity,"DeliveryArtikulsDB2",item) {
                       deliveryValue.value = it.toString()
                   }

                },modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(20.dp)
                    .height(55.dp)
                    , colors = ButtonDefaults.buttonColors(buttonColor)) {
                    Text(text = "Виж дост. цена")
                }

                createField2(placeholder = "Дост. цена",deliveryValue, maxWidth = 1f)
            }
            Row(modifier = Modifier.fillMaxWidth(1f)) {
                Button(onClick = {
                    viewModel.getExtinguishersPrice(context = this@EditArtikulActivity, type = type,weight
                    = weight,category = category,
                        brand = brand
                    ) {
                        currentValue.value = it.toString()
                    }
                }, modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(buttonColor)) {
                    Text(text = "Виж текуща цена")
                }
               createField2(placeholder = "Текуща цена", currentValue, maxWidth = 1f)
            }
            Button(onClick = {
                             viewModel.createExtinguisher(this@EditArtikulActivity,type,weight,
                                 category,brand, quantity,currentValue.value,invoice,kontragent,dateVal
                             ) {
                                   httpResponse = it
                                   showDialog = true
                             }
            }, modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(20.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(buttonColor)) {
                Text(text = "Запиши")
            }

        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = R.drawable.fire_extinguisher), contentDescription = "ExitnguisherLogo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f), tint = Color(0x23FF0000))
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun createClientsDropdown(viewModel: MyViewModel) : String {
        val clientsList = viewModel.getClientList()

        val foundedClientsList = remember { mutableStateListOf<Client>() }

        var searchClientText by remember {
            mutableStateOf(kontragent)
        }
        var expandedClientsDropdown by remember {
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(1f),expanded = expandedClientsDropdown, onExpandedChange = {
            expandedClientsDropdown = !expandedClientsDropdown
        }) {

            Row() {
                TextField(value = searchClientText!!, onValueChange = {
                    searchClientText = it
                    foundedClientsList.clear()
                    clientsList.forEach {
                            item ->
                        if(item.name.lowercase().contains(searchClientText!!.lowercase())) {
                            foundedClientsList.add(item)
                        }
                    }

                    expandedClientsDropdown = true
                },
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth(1f), placeholder = { Text(text = "Търсене по Клиент")}
                    , singleLine = true, colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedBorderColor =  Color.Gray,
                        cursorColor = Color.Red
                    )

                )

            }

            ExposedDropdownMenu(expanded = expandedClientsDropdown, onDismissRequest = {
                expandedClientsDropdown = false
            },Modifier.background(Color.White)) {

                LazyColumn(modifier = Modifier
                    .width(500.dp)
                    .height(600.dp)) {

                    if(searchClientText!!.isEmpty()) {
                        items(clientsList) {
                                item ->
                            DropdownMenuItem(text =  { Text(item.name ?: "-"
                                , color = Color.Black)},
                                onClick = {
                                    searchClientText = item.name ?: "-"
                                    expandedClientsDropdown = false
                                },
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .width(IntrinsicSize.Max)

                            )
                            Divider(thickness = 1.dp, color = Color.Gray)

                        }
                    } else {
                        items(foundedClientsList) {
                                item ->
                            DropdownMenuItem(text =  { Text(item.name ?: "-",color = Color.Black) },
                                onClick = {
                                    searchClientText = item.name ?: "-"
                                    expandedClientsDropdown = false
                                },
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .width(IntrinsicSize.Max))
                            Divider(thickness = 1.dp, color = Color.Gray)

                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            viewModel.getClients(this@EditArtikulActivity,clientsList)
        }

        return searchClientText!!
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun createArtikulsDropdown(viewModel: MyViewModel) : String {
        val artikulList = viewModel.getEmptyArtikulsList()

        var searchArtikulText by remember {
            mutableStateOf(artikul)
        }
        var expandedArtikulsDropDwon by remember {
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(expanded = expandedArtikulsDropDwon, onExpandedChange = {
            expandedArtikulsDropDwon = !expandedArtikulsDropDwon
        }, modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(1f)) {
            Row() {
                TextField(value = searchArtikulText!!, onValueChange = {
                    searchArtikulText = it
                    expandedArtikulsDropDwon = true
                },
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth(1f),placeholder = {Text(text = "Търсене по артикул")},
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedBorderColor = Color.Gray,
                        cursorColor = Color.Red
                    ))
            }

            ExposedDropdownMenu(expanded = expandedArtikulsDropDwon, onDismissRequest = {
                expandedArtikulsDropDwon = false
            },Modifier.background(Color.White)) {
                LazyColumn(
                    Modifier
                        .width(500.dp)
                        .height(600.dp)) {

                    val mutableList : MutableList<ArtikulModel> = mutableListOf()
                    for(artikul in artikulList) {

                        val str1 = artikul.artikul.lowercase()
                        val str2 = searchArtikulText!!.lowercase()


                        if(str1.lowercase().contains(str2.lowercase())) {
                            if(!mutableList.contains(artikul)) {
                                mutableList.add(artikul)
                            }
                        }
                    }
                    if(searchArtikulText!!.isEmpty()) {
                        items(artikulList) {

                            DropdownMenuItem(text = { Text(text = it.artikul,color = Color.Black) }, onClick = {
                                searchArtikulText = it.artikul
                                expandedArtikulsDropDwon = false
                            },
                                Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .width(IntrinsicSize.Max))
                            Divider(thickness = 1.dp, color = Color.Gray)
                        }
                    } else {
                        items(mutableList) {
                            DropdownMenuItem(text = { Text(text = it.artikul, color = Color.Black) }, onClick = {
                                searchArtikulText = it.artikul
                                expandedArtikulsDropDwon = false
                            },Modifier.padding(10.dp))
                            Divider(thickness = 1.dp, color = Color.Gray)
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            viewModel.getArtikuls2(this@EditArtikulActivity,artikulList, lambda1 = {

            })
        }
        return searchArtikulText!!
    }

    @Composable
    private fun createField2(placeholder: String, text: MutableState<String>, maxWidth: Float) : String {
        var value2 by remember {
            mutableStateOf(text)
        }
        TextField(value = text.value, onValueChange = {
            text.value = it
        } ,
            Modifier
                .padding(20.dp)
                .fillMaxWidth(maxWidth), placeholder = { Text(text = placeholder)}
            , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            , singleLine = true, colors =
            OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Gray,
                cursorColor = Color.Red
            ))
        return text.value
    }

    @Composable
    private fun createField(placeholder: String, text: String, maxWidth: Float) : String {
        var value by remember {
            mutableStateOf(text)
        }
        TextField(value, onValueChange = {
            value = it
        } ,
            Modifier
                .padding(20.dp)
                .fillMaxWidth(maxWidth), placeholder = { Text(text = placeholder)}
            , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            , singleLine = true, colors =
            OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Gray,
                cursorColor = Color.Red
            ))
        return value
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun createDropdownField(options : List<String>,med: String,  placeholder: String) : String {
        var expandedMedDropdown by remember {
            mutableStateOf(false)
        }
        var medValue by remember {
            mutableStateOf(med)
        }
        ExposedDropdownMenuBox(expanded = expandedMedDropdown, onExpandedChange = {
            expandedMedDropdown = !expandedMedDropdown
        }) {

            TextField(value = medValue,
                onValueChange = {
                    medValue = it
                },
                Modifier
                    .menuAnchor()
                    .padding(start = 20.dp, top = 20.dp)
                    .width(150.dp),
                placeholder = { Text(text = placeholder)}, singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    cursorColor = Color.Red
                ))

            ExposedDropdownMenu(expanded = expandedMedDropdown, onDismissRequest = {
                expandedMedDropdown = false
            },
                Modifier
                    .padding(start = 20.dp)
                    .background(Color.White)
                    .fillMaxWidth()) {
                Column {
                    for(med in options) {
                        DropdownMenuItem(text = { Text(text=med, color = Color.Black) },
                            onClick = {
                                medValue = med
                                expandedMedDropdown = false
                            },Modifier.background(Color.White))
                        Divider(thickness = 1.dp, color = Color.LightGray)
                    }
                }
            }
        }
        return medValue!!
    }

    @Composable
    private fun InsertArtikulsUI(viewModel: MyViewModel) {

        Column(Modifier.verticalScroll(rememberScrollState())) {
            // CREATE CLIENTS DROPDOWN
            val searchClientText = createClientsDropdown(viewModel)

            // CREATE ARTIKULS DROPDWON
            val searchArtikulText = createArtikulsDropdown(viewModel)
            // CREATE ADDITIONAL FIELDS

            //  INVOICE FIELD
            val invoiceValue = createField("No Фактура", invoice!!, 0.7f)

            // DATE FIELD
            val formatter = SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH)
            val date = Date()
            val currentDate = formatter.format(date)
            val dateValue = createField(placeholder = "Дата", text = currentDate, 0.7f)

            var quantityValue : String = ""
            var medValue : String = ""
            // MED FIELD AND DROPDOWN
            Row(Modifier.width(IntrinsicSize.Min)) {
                 quantityValue = createField(placeholder = "К-во", text = "", 0.4f) // createQuantityField()

                 medValue = createDropdownField(listOf("броя","кг","литра","км"),"","М. ед")

            }

            // DELIVERY PRICE FIELD
            val deliveryValue = createField(placeholder = "Дост. цена","",0.5f) //createDeliveryField()

            // SALE PRICE FIELD
            val priceValue = createField(placeholder = "Цена", text = "", maxWidth = 0.5f)// createPriceField()

            var showDialog by remember {
                mutableStateOf(false)
            }
            var httpResponse by remember {
                mutableStateOf(HttpResponse(0,""))
            }
            if(showDialog) {
                MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message, onDismissRequest = {
                    showDialog = false
                })
            }
            // SAVE BUTTON
            Button(onClick = {
                val model = ArtikulModel(
                    artikul = searchArtikulText,
                    quantity = Integer.parseInt(quantityValue),
                    med = medValue,
                    price = priceValue.toFloat(),
                    date = dateValue,
                    invoice = invoiceValue,
                    kontragent = searchClientText,
                    barcode = "0000000",
                    percentProfit = "3",
                    person = "админ"
                )

                viewModel.insertArtikul(context = this@EditArtikulActivity, model = model) {
                    showDialog = true
                    httpResponse = it
                }

            },
                Modifier
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(containerColor = buttonColor)) {
                Text(text = "Запиши")
            }

        }
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.fire_bigger), contentDescription = "Icon_Logo"
                , modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
                , tint = Color(0x23FF0000))
        }



    }
    @Composable
    private fun EditArtikulsPriceUI(viewModel: MyViewModel) {

        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
        var priceValue = ""
        Column {
            Text(text = artikul!!, color = Color.Black, modifier = Modifier.padding(20.dp))
            
            Text(text = kontragent!!, color = Color.Black, modifier = Modifier.padding(20.dp))
            
            Text(text = invoice!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            priceValue = createField(placeholder = "Цена","",0.5f)// createPriceField()

            Button(onClick = {
                             viewModel.editArtikulPrice(this@EditArtikulActivity,
                             priceValue,"0",artikul!!,kontragent!!,invoice!!, res = {
                                    httpResponse = it
                                     showDialog = true
                                 })
            },
                Modifier
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0094FF))) {
                Text(text = "Запиши")
            }
        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.fire_bigger), contentDescription = "Icon_Logo",
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.6f),
            tint = Color(0x23FF0000))
        }
    }

    @Composable
    private fun EditArtikulsQuantityUI(viewModel: MyViewModel) {

        var newQuantityValue by remember {
            mutableStateOf("")
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }

        Column {
            Text(text = artikul!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            Text(text = kontragent!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            Text(text = invoice!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            TextField(value = newQuantityValue, onValueChange = {
                newQuantityValue = it
            }, modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.5f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true, placeholder = {
                 Text(text = "Количество")
                }, colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    cursorColor = Color.Red
                ))

            Button(onClick = {
                viewModel.editArtikulQuantity(this@EditArtikulActivity,artikul!!,
                kontragent!!,invoice!!,newQuantityValue, res = {
                      httpResponse = it
                      showDialog = true
                    })
            },
                Modifier
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0094FF))) {
                Text(text = "Запиши")
            }
        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.fire_bigger), contentDescription = "Icon_Logo"
                , modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
            , tint = Color(0x23FF0000))
        }

    }


    @Composable
    private fun RenameArtikulsUI(viewModel: MyViewModel) {
        var searchArtikulValue = artikul!!
        var newArtikulValue by remember {
            mutableStateOf(artikul!!)
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }


        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code,httpResponse.message
            , onDismissRequest = { showDialog = false })
        }
        Column {
            Text(text = artikul!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            TextField(value = newArtikulValue, onValueChange = {
                newArtikulValue = it
            },
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(1f), singleLine = true,
            placeholder = {Text(text = "Въведете ново име на артикул")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Gray,
                cursorColor = Color.Red
            ))
            
            Button(onClick = {
                viewModel.renameArtikul(this@EditArtikulActivity,searchArtikulValue,newArtikulValue) {
                      httpResponse = it
                      showDialog = true
                }
            },
                Modifier
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0094FF))) {
                Text(text = "Запиши")
            }
        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.fire_bigger), contentDescription = "Icon_Logo"
                , modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
            , tint = Color(0x23FF0000))
        }
    }

    @Composable
    private fun DeleteArtikulsUI(viewModel: MyViewModel) {


         var showDialog by remember {
             mutableStateOf(false)
         }
        var httpResponse by remember {
            mutableStateOf(HttpResponse(0,""))
        }
        if(showDialog) {
            MyDialog().RetroDialog(title = httpResponse.code, message = httpResponse.message) {
                showDialog = false
            }
        }
        Column {
            Text(text = artikul!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            Text(text = kontragent!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            Text(text = invoice!!, color = Color.Black, modifier = Modifier.padding(20.dp))

            Button(onClick = {
                             viewModel.deleteArtikul(this@EditArtikulActivity,
                             artikul!!,kontragent!!,invoice!!
                             ) {
                                 httpResponse = it
                                 showDialog = true
                             }
            },
                Modifier
                    .padding(20.dp)
                    .height(55.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0094FF))) {
                Text(text = "Изтрий")
            }
        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.fire_bigger), contentDescription = "Icon_Logo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f),
            tint = Color(0x23FF0000))
        }

    }





//    @Preview
//    @Composable
//    private fun showPreview() {
//        PojarprotektMobileAppTheme() {
//            Surface {
//               initComponents()
//            }
//        }
//
//    }
}