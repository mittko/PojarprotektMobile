package com.example.fireextinguishinginstallationsmobile


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fireextinguishinginstallationsmobile.data.aerozolMap
import com.example.fireextinguishinginstallationsmobile.data.aerozolSections
import com.example.fireextinguishinginstallationsmobile.data.gasMap
import com.example.fireextinguishinginstallationsmobile.data.mapOfModels
import com.example.fireextinguishinginstallationsmobile.data.gasSections
import com.example.fireextinguishinginstallationsmobile.enums.InstallationType
import com.example.fireextinguishinginstallationsmobile.interfaces.IModel
import com.example.fireextinguishinginstallationsmobile.json.MyJsonObject
import com.example.fireextinguishinginstallationsmobile.json.ObjectIdModel
import com.example.fireextinguishinginstallationsmobile.models.CheckedModel
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelThree
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelTwo
import com.example.fireextinguishinginstallationsmobile.models.CountModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModelTwo
import com.example.fireextinguishinginstallationsmobile.models.ExtendedCheckedModel
import com.example.fireextinguishinginstallationsmobile.models.FieldModel
import com.example.fireextinguishinginstallationsmobile.models.FieldModelThree
import com.example.fireextinguishinginstallationsmobile.models.FieldModelTwo
import com.example.fireextinguishinginstallationsmobile.models.TextModel
import com.example.fireextinguishinginstallationsmobile.models.auth.AuthModel
import com.example.fireextinguishinginstallationsmobile.models.auth.LoginRes
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelExtended
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelThree
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCountModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonDropDownModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonDropDownModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModelThree
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonTextModel
import com.example.fireextinguishinginstallationsmobile.retrofit.HttpResponse
import com.example.fireextinguishinginstallationsmobile.retrofit.ISunotechAPI
import com.example.fireextinguishinginstallationsmobile.retrofit.RetrofitInstance
import com.example.fireextinguishinginstallationsmobile.retrofit.RetrofitInstance.getURL
import com.example.fireextinguishinginstallationsmobile.ui.theme.FireExtinguishingInstallationsMobileTheme
import com.example.fireextinguishinginstallationsmobile.utils.MyDialog
import com.example.fireextinguishinginstallationsmobile.utils.PreferencesManager
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import com.example.fireextinguishinginstallationsmobile.models.auth.OnResponseBody
import com.example.fireextinguishinginstallationsmobile.models.barcodeText
import com.example.fireextinguishinginstallationsmobile.utils.PreviewOption
import kotlinx.coroutines.delay
import kotlin.collections.forEach


// Ctrl + Alt + O clean unused imports

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplashScreen = true

        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        enableEdgeToEdge()

        keepSplashScreen = false// hide splash and show UI

        setContent {
            val context = LocalContext.current

            FireExtinguishingInstallationsMobileTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.surface
                    ) {

                        var isTokenValid by remember {
                            mutableStateOf(false)
                        }
                        var httpResponse by remember {
                            mutableStateOf(HttpResponse(0, ""))
                        }
                        var showDialog by remember {
                            mutableStateOf(false)
                        }
                        var token by remember {
                            mutableStateOf(PreferencesManager().getToken(context))
                        }
                        val api = remember {
                            RetrofitInstance.getInstance().create(ISunotechAPI::class.java)
                        }

                        LaunchedEffect(token) {
                            api.sayHello(token).enqueue(object : retrofit2.Callback<String> {
                                override fun onResponse(
                                    call: Call<String>,
                                    response: Response<String>
                                ) {
                                    if (response.isSuccessful) {
                                        isTokenValid = true
                                        showDialog = false
                                    } else {
                                        isTokenValid = false
                                        showDialog = true
                                        httpResponse =
                                            HttpResponse(response.code(), response.message())
                                    }
                                }

                                override fun onFailure(
                                    call: Call<String>,
                                    t: Throwable
                                ) {
                                    isTokenValid = false
                                    showDialog = true
                                    httpResponse = HttpResponse(504, t.message!!)
                                }

                            })
                        }

                        if (!isTokenValid) {
                            LoginPage(
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(innerPadding)
                                    .imePadding()
                                    .verticalScroll(rememberScrollState()),
                                context
                            ) { user, token ->
                                PreferencesManager().putAuth(context, user, token)
                                isTokenValid = true
                            }
                            if (showDialog) {
                                MyDialog().RetroDialog(httpResponse.code, httpResponse.message) {
                                    showDialog = false
                                }
                            }
                        } else {
                            InitialPage(modifier = Modifier
                                .padding(innerPadding)
                                .consumeWindowInsets(innerPadding)   // <-- добавяш това
                                .imePadding()

                                .padding(10.dp, 15.dp, 10.dp, 0.dp))
                        }


                    }

                }
            }
        }
    }
}


@Composable
fun LoginPage(modifier: Modifier, context: Context, onRefreshToken: (String?, String?) -> Unit) {

    var user by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val service = remember {
        RetrofitInstance.getInstance().create(ISunotechAPI::class.java)
    }
    val authModel = AuthModel(user, password)


    var httpResponse by remember {
        mutableStateOf<HttpResponse?>(null)
    }
    MyCard {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Row {
                TextField(value = user, placeholder = {
                    Text(text = "Потребител")
                }, onValueChange = {
                    user = it
                })
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                TextField(value = password, placeholder = {
                    Text(text = "Парола")
                }, onValueChange = {
                    password = it
                })
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                )
                Button(onClick = {
                    service.logIn(authModel).enqueue(object : retrofit2.Callback<LoginRes> {
                        override fun onResponse(
                            call: Call<LoginRes?>,
                            response: Response<LoginRes?>
                        ) {
                            httpResponse = HttpResponse(response.code(),response.message())


                            response.let {
                                val loginResult = it.body()

                                if (it.code() == 200) {
                                    onRefreshToken(loginResult?.user?.usser, loginResult?.token)
                                }
                            }


                        }

                        override fun onFailure(
                            call: Call<LoginRes?>,
                            t: Throwable
                        ) {
                            httpResponse = HttpResponse(500,t.message ?: "")
                        }

                    })
                }) {
                    Text(text = "Влез")
                }
            }


        }
    }

    httpResponse?.let {
        MyDialog().RetroDialog(it.code,it.message) {
            httpResponse = null
        }
    }
}



@Composable
fun MainScreen(modifier: Modifier, type : InstallationType) {

    mapOfModels.clear()

    when(type) {
        InstallationType.AEROZOL ->  {

            mapOfModels.putAll(aerozolMap)
        }
        InstallationType.GAS -> {

            mapOfModels.putAll(gasMap)
        }
    }


    val pagerState = rememberPagerState(
        0,
        pageCount = {
            mapOfModels.size
        })



    // 1. Create a custom fling behavior
    val customFlingBehavior = PagerDefaults.flingBehavior(
        pagerState,
        snapAnimationSpec = tween(
            durationMillis = 450,
            easing = LinearOutSlowInEasing
        )
    )




    Column(modifier = Modifier.fillMaxSize()) {

        // region Pager
        HorizontalPager(
            state = pagerState,
            flingBehavior = customFlingBehavior,
            userScrollEnabled = false,
            // key = { pageIndex -> "page_key_$pageIndex" }
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) { page ->


            when(type) {
                InstallationType.AEROZOL -> {

                    when (page) {

                        0 -> OpenCamera(modifier, pagerState)
                        1 -> {
                            val section = aerozolSections[page]
                            PageHeader(modifier, page, section,pagerState)
                        }
                        2 -> {
                            val section = aerozolSections[page]
                            TestOsnovnoZahranvane(modifier, "№${page+1} $section", section,pagerState)
                        }
                        3 -> {
                            val section = aerozolSections[page]
                            TestOsnovnaPlatka(modifier, title = "№${page+1} $section",  section = section,pagerState)
                        }
                        4  -> {
                            val section = aerozolSections[page]
                            PregledRezervnoZahranvane(modifier, "№${page+1} $section", section,pagerState)
                        }
                        5 -> {
                            val section = aerozolSections[page]
                            PregledControlPanel(modifier, "№${page+1} $section", section, pagerState)
                        }
                        6 -> {
                            val section = aerozolSections[page]
                            FunkcionalenTestElTablo(modifier, title = "№${page+1} $section",section ,pagerState)
                        }
                        7 -> {
                            val section = aerozolSections[page]
                            ProverkaPravilnaSvyrzanost(modifier, "№${page+1} $section", section  = section,pagerState)
                        }
                        8 -> {
                            val section = aerozolSections[page]
                            FunkcionalenTestnaZvukovSignalizator(modifier,"№${page+1} $section",section,pagerState)
                        }

                        9 -> {
                            val section = aerozolSections[page]
                            ProverkaLupoveILinii(modifier, "№${page+1} $section", section,pagerState)
                        }
                        10 -> {
                            val section = aerozolSections[page]
                            ProverkaPojaroizvestitelenDetektor(modifier, "№${page+1} $section", section,pagerState)
                        }
                        11 -> {
                            val section = aerozolSections[page]
                            ProverkaSvobodnoProstranstvoOkoloPojaroizvestitelenDetektor(modifier, "№${page+1} $section",
                                section,pagerState)
                        }
                        12 -> {
                            val section = aerozolSections[page]
                            TestMehanizamVsekiRychenButon(modifier, "№${page+1} $section", section,pagerState)
                        }
                        13 -> {
                            val section = aerozolSections[page]
                            ProverkaNaDostypDoVsichkiPojaroizvestitelniButoni(modifier, "№${page+1} $section",
                                section,pagerState)
                        }
                        14 -> {
                            val section = aerozolSections[page]
                            ProverkaZaNalichieUkazatelniZnaci(modifier,"№${page+1} $section",section,pagerState)
                        }
                        15 -> {
                            val section = aerozolSections[page]
                            VizualnaProverkaNaSydoveteZaGasitelenAgentAerozol(modifier, "№${page+1} $section",
                                section,pagerState)
                        }
                        16 -> {
                            val section = aerozolSections[page]
                            ProverkaSignalniIIzneseniUstrojstva(modifier, "№${page+1} $section",
                                section,pagerState)
                        }
                        17, 18, 19, 20, 21 -> {
                            val section = aerozolSections[page]
                            ZakluchitelniProverki(
                                modifier,
                                "№${page+1} $section",
                                section,
                                pagerState
                            )
                        }

                        22 -> {
                            val section = aerozolSections[page]
                            LastPage(modifier, "№${page+1} $section",
                            section,pagerState)
                    }

                    }
                }
                InstallationType.GAS -> {
                    when (page) {

                        0 -> OpenCamera(modifier, pagerState)
                        1 -> {
                            val section = gasSections[page]

                            PageHeader(modifier, page, section,pagerState)
                        }
                        2 -> {
                            val section = gasSections[page]
                            PregledControlPanel(modifier, "№${page+1} $section", section,pagerState)
                        }
                        3 -> {
                            val section = gasSections[page]
                            FunkcionalenTestElTablo(modifier, "№${page+1} $section",section, pagerState)
                        }
                        4 -> {
                            val section = gasSections[page]
                            TestOsnovnoZahranvane(modifier, "№${page+1} $section", section,pagerState)
                        }
                        5 -> {
                            val section = gasSections[page]
                            TestOsnovnaPlatka(modifier, "№${page+1} $section", section,pagerState)
                        }
                        6, 7, 8 -> {
                            val section = gasSections[page]
                            ProverkaPravilnaSvyrzanost(modifier, "№${page+1} $section", section,pagerState)
                        }
                        9, 10 -> {
                            val section = gasSections[page]
                            ProverkaRychenSpiratelenKran(modifier, "№${page+1} $section",section, pagerState)
                        }
                        11 -> {
                            val section = gasSections[page]
                            PregledRezervnoZahranvane(modifier, "№${page+1} $section", section,pagerState)
                        }
                        12 -> {
                            val section = gasSections[page]
                            ProverkaLupoveILinii(modifier, "№${page+1} $section", section,pagerState)
                        }
                        13 -> {
                            val section = gasSections[page]
                            ProverkaPojaroizvestitelenDetektor(modifier, "№${page+1} $section", section,pagerState)
                        }
                        14 -> {
                            val section = gasSections[page]
                            ProverkaSvobodnoProstranstvoOkoloPojaroizvestitelenDetektor(modifier, "№${page+1} $section", section,pagerState)
                        }
                        15, 16, 17 -> {
                            val section = gasSections[page]
                            TestMehanizamVsekiRychenButon(modifier, "№${page+1} $section", section,pagerState)
                        }
                        18, 20 -> {
                            val section = gasSections[page]
                            VidSydZaGAsitelenAgent(modifier, "№${page+1} $section", section,pagerState)
                        }
                        19 -> {
                            val section = gasSections[page]
                            VizualnaProverkaNaSydoveteZaGasitelenAgent(modifier, "№${page+1} $section", section,pagerState)
                        }
                        21 -> {
                            val section = gasSections[page]
                            ProverkaSignalniIIzneseniUstrojstva(modifier, "№${page+1} $section", section,pagerState)
                        }
                        22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33 -> {
                            val section = gasSections[page]
                            ZakluchitelniProverki(
                                modifier,
                                "№${page+1} $section",
                                section,
                                pagerState
                            )
                        }

                        34 -> {
                            val section = gasSections[page]
                            LastPage(modifier, "№${page+1} $section", section,pagerState)
                        }

                    }
                }
            }


        }
    }

    // endregion Pager
}

@Composable
fun InitialPage(modifier: Modifier) {

    val context = LocalContext.current
    val preferencesManager = PreferencesManager()
    val token = preferencesManager.getToken(context)
    val api = RetrofitInstance.getInstance().create(ISunotechAPI::class.java)

    var shouldOpenPage by remember {
        mutableIntStateOf(0)
    }

    var triggerDataLoading by remember {
        mutableIntStateOf(0)
    }

    when(shouldOpenPage) {
        0 -> {
            Box(contentAlignment = Alignment.Center) {
                MyCard {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Button(onClick = {
                                shouldOpenPage = 1
                                triggerDataLoading++
                            }, modifier = Modifier.height(60.dp)) {
                                Text(text = "Аерозолни ПГИ")
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Button(onClick = {
                             shouldOpenPage = 2
                             triggerDataLoading++
                        }, modifier = Modifier.height(60.dp)) {
                            Text(text = "Газови ПГИ")
                        }
                    }
                }
            }
        }
        1 -> {

            PreferencesManager().setInstallationType(context, InstallationType.AEROZOL)
            MainScreen(modifier, InstallationType.AEROZOL)

             LaunchedEffect(triggerDataLoading) {
                 api.getDefaultProtocolData("/get_sunotech_protokols_default_data",
                     "aerozol",token).enqueue(object :
                     retrofit2.Callback<MyJsonObject> {
                     override fun onResponse(
                         call: Call<MyJsonObject>,
                         response: Response<MyJsonObject>
                     ) {
                         val result = response.body()

                         if (result != null) {


                             PreferencesManager().setObjectId(context, result.objectId)
                             // very important !!!
                             mapOfModels.clear()



                             val mutableMap = result.mutableMap
                             mutableMap.put("Камера", ArrayList())


                             mutableMap.forEach { key, value ->
                                 // if we have barcode no need to overwrite it !!!

                                 val list = mutableMap[key]
                                 val newList = ArrayList<IModel>()
                                 list?.forEach { model ->
                                     when (model.type) {
                                         "checkable" -> {
                                             val jsonCheckedModel = model as JsonCheckedModel
                                             val checkedModel =
                                                 CheckedModel(
                                                     jsonCheckedModel.subTitle
                                                 )
                                             checkedModel.checked = jsonCheckedModel.checked
                                             checkedModel.unchecked = jsonCheckedModel.unchecked
                                             checkedModel.data = jsonCheckedModel.data
                                             newList.add(checkedModel)
                                         }

                                         "checkable count" -> {
                                             val jsonCheckedModelTwo = model as JsonCheckedDataModelTwo
                                             val checkedModelTwo =
                                                 CheckedModelTwo(
                                                     jsonCheckedModelTwo.subTitle
                                                 )
                                             checkedModelTwo.checked = jsonCheckedModelTwo.checked
                                             checkedModelTwo.unchecked = jsonCheckedModelTwo.unchecked
                                             checkedModelTwo.data = jsonCheckedModelTwo.data
                                             checkedModelTwo.count = jsonCheckedModelTwo.count
                                             newList.add(checkedModelTwo)
                                         }

                                         "checkable measure" -> {
                                             val jsonCheckedModelThree =
                                                 model as JsonCheckedDataModelThree
                                             val checkedModelThree =
                                                 CheckedModelThree(
                                                     jsonCheckedModelThree.subTitle
                                                 )
                                             checkedModelThree.checked = jsonCheckedModelThree.checked
                                             checkedModelThree.unchecked =
                                                 jsonCheckedModelThree.unchecked
                                             checkedModelThree.data = jsonCheckedModelThree.data
                                             checkedModelThree.previousMeasurement =
                                                 jsonCheckedModelThree.previousMeasurement
                                             checkedModelThree.currentMeasurement =
                                                 jsonCheckedModelThree.currentMeasurement
                                             newList.add(checkedModelThree)
                                         }

                                         "count" -> {
                                             val jsonCountModel = model as JsonCountModel
                                             val countModel = CountModel(
                                                 jsonCountModel.subTitle
                                             )
                                             countModel.data = jsonCountModel.data
                                             newList.add(countModel)
                                         }

                                         "checkable extended" -> {
                                             val jsonCheckedDataModelExtended =
                                                 model as JsonCheckedDataModelExtended
                                             val extendedCheckedModel =
                                                 ExtendedCheckedModel(
                                                     jsonCheckedDataModelExtended.subTitle
                                                 )
                                             extendedCheckedModel.checked =
                                                 jsonCheckedDataModelExtended.checked
                                             extendedCheckedModel.unchecked =
                                                 jsonCheckedDataModelExtended.unchecked
                                             extendedCheckedModel.data =
                                                 jsonCheckedDataModelExtended.data
                                             extendedCheckedModel.pressure =
                                                 jsonCheckedDataModelExtended.pressure
                                             extendedCheckedModel.lastPressure =
                                                 jsonCheckedDataModelExtended.lastPressure
                                             extendedCheckedModel.fabNum =
                                                 jsonCheckedDataModelExtended.fabNum
                                             extendedCheckedModel.hidrostatMeasurementDate =
                                                 jsonCheckedDataModelExtended.hidrostatMeasurementDate
                                             extendedCheckedModel.device =
                                                 jsonCheckedDataModelExtended.device
                                             newList.add(extendedCheckedModel)
                                         }

                                         "field" -> {
                                             val jsonFiledModel = model as JsonFieldModel
                                             val fieldModel = FieldModel(
                                                 jsonFiledModel.subTitle
                                             )
                                             fieldModel.data = jsonFiledModel.data
                                             newList.add(fieldModel)
                                         }

                                         "field data two" -> {
                                             val jsonFieldModelTwo = model as JsonFieldModelTwo
                                             val fieldModelTwo =
                                                 FieldModelTwo(
                                                     jsonFieldModelTwo.subTitle
                                                 )
                                             fieldModelTwo.data = jsonFieldModelTwo.data
                                             fieldModelTwo.dataTwo = jsonFieldModelTwo.dataTwo
                                             newList.add(fieldModelTwo)
                                         }

                                         "field data three" -> {
                                             val jsonFieldModelThree = model as JsonFieldModelThree
                                             val fieldModelThree =
                                                 FieldModelThree(
                                                     jsonFieldModelThree.subTitle
                                                 )
                                             fieldModelThree.data = jsonFieldModelThree.data
                                             fieldModelThree.pressure = jsonFieldModelThree.pressure
                                             fieldModelThree.oldPressure =
                                                 jsonFieldModelThree.oldPressure
                                             newList.add(fieldModelThree)
                                         }

                                         "text" -> {
                                             val jsonTextModel = model as JsonTextModel
                                             val textModel = TextModel(
                                                 jsonTextModel.subTitle
                                             )
                                             textModel.data = jsonTextModel.data
                                             newList.add(textModel)
                                         }

                                         "dropdown" -> {
                                             val jsonDropDownModel = model as JsonDropDownModel
                                             val dropDownModel = DropDownModel(
                                                 jsonDropDownModel.subTitle
                                             )
                                             dropDownModel.data = jsonDropDownModel.data
                                             newList.add(dropDownModel)
                                         }

                                         "dropdowntwo" -> {
                                             val jsonDropDownModelTwo = model as JsonDropDownModelTwo
                                             val dropDownModelTwo = DropDownModelTwo(
                                                 jsonDropDownModelTwo.subTitle
                                             )
                                             dropDownModelTwo.data = jsonDropDownModelTwo.data
                                             newList.add(dropDownModelTwo)
                                         }
                                     }


                                 }
                                 //mapOfModels[key]?.clear()
                                 mapOfModels[key] = newList

                             }
                         }

                     }

                     override fun onFailure(
                         call: Call<MyJsonObject>,
                         t: Throwable
                     ) {
                        // wzemi json-a ot android (default data)
                       // error dialog here
                     }


                 })

             }


        }
        2 -> {
            PreferencesManager().setInstallationType(context, InstallationType.GAS)
            MainScreen(modifier, InstallationType.GAS)
        }
    }






}


//region Page One
@Composable
fun PregledControlPanel(
    modifier: Modifier,
    title : String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списъкът с карти - той заема свободното място (weight 1)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Използваме лего блокчето за заглавие
                        QuestionHeader(model.subTitle)

                        // Използваме лего блокчето с етикет "изправни"
                        LabeledBinaryChoice(model)
                    }
                }
            }


        }

        // 3. Навигацията - тя е ВИНАГИ видима тук
        BottomPaging(pagerState )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

//region Page Two
@Composable
fun FunkcionalenTestElTablo(
    modifier: Modifier,
    title : String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {

        // 1. Заглавие на страницата
        Title(title)



        // 2. Списъкът с контроли (weight 1 заема средата)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                PageTwoControls(model)
            }
        }

        // 3. Навигация (винаги закована долу)
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion Page Two

//region Page Two Controls
@Composable
fun PageTwoControls(model: IModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),//vertical = 6.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Заглавие на под-въпроса
            QuestionHeader(model.subTitle)

            // Избор Да/Не
            SimpleChoiceRow(model)
            if (model is CheckedModelTwo) {
                DataField(model, value = model.data, placeholder = "Забележка...", lamb = {
                    model.data = it
                })
            }
        }
    }
}
//endregion Page Two Controls

//region Page Three
@Composable
fun TestOsnovnoZahranvane(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // Заглавие на страницата
        Title(title)

        MyColumn(
            modifier = Modifier.weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // 1. Винаги заглавие на въпроса
                        QuestionHeader(model.subTitle)

                        if (model is CheckedModelThree) {
                            BinaryChoice(model)
                            Row {
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {

                                    CountFieldThree(model,value = model.previousMeasurement, enabled = false)

                                }
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {

                                    CountFieldThree(model, value = model.currentMeasurement, placeholder = "Текущ...")
                                }
                            }
                            DataField(
                                model,
                                placeholder = "Забележка...",
                                value = model.data,
                                lamb = {
                                    model.data = it
                                })
                        } else if (model is FieldModelThree) {
                            Row {
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {

                                    CountFieldFour(model, value = model.oldPressure, enabled = false)


                                }
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {
                                    CountFieldFour(model, placeholder = "Текущ...",
                                        value = model.pressure)

                                }
                            }
                            // 3. Ако е TextModel (температурата - модел 1) ИЛИ е CheckedModel (за данни)
                            DataField(
                                model, placeholder = "Забележка...", value = model.data,
                                lamb = { model.data = it })
                        }


                    }
                }
            }

        }

        // Навигацията - закована долу
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(10.dp))
    }

}

//endregion

//region Page Fourth
@Composable
fun TestOsnovnaPlatka(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списъкът с карти (weight 1 фиксира навигацията долу)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {

                        when (model) {
                            is CheckedModel -> {
                                // Блокче 1: Заглавие на въпроса
                                QuestionHeader(model.subTitle)

                                // Блокче 2: Чекбоксове "Да/Не" с етикет "изправни" (като на Page 1)
                                LabeledBinaryChoice(model) {
                                    // model.data = it

                                }

                                // Блокче 3: Поле за забележка (само ако моделът поддържа данни)
                                Spacer(modifier = Modifier.height(8.dp))
                                DataField(
                                    model,
                                    placeholder = "Забележка...",
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })
                            }

                            is TextModel -> {
                                Text(text = model.subTitle)
                            }

                            is FieldModelThree -> {
                                QuestionHeader(model.subTitle)
                                Row {
                                    Row(
                                        Modifier
                                            .weight(0.5f)
                                            .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                    ) {
                                        DataField(
                                            model,
                                            enabled = false,
                                            value = model.oldPressure
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .weight(0.5f)
                                            .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                    ) {
                                        CountFieldFour(
                                            model,
                                            placeholder = "Текущ...",
                                            value = model.pressure)
                                    }
                                }

                                // 3. Ако е TextModel (температурата - модел 1) ИЛИ е CheckedModel (за данни)
                                DataField(
                                    model,
                                    placeholder = "Забележка...",
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })
                            }
                        }

                    }
                }

            }


        }
        // 3. Навигацията - винаги видима
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion Page Fourth


//region Page Five
@Composable
fun ProverkaPravilnaSvyrzanost(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списък с карти - ползваме weight(1f), за да не "избягат" стрелките
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Блокче 2: Ред с "изправни" и Да/Не бутони
                        LabeledBinaryChoice(model, label = "изправни") {

                        }

                        // Блокче 3: Поле за забележка
                        Spacer(modifier = Modifier.height(8.dp))
                        val fieldModel = model as CheckedModel
                        DataField(
                            model,
                            placeholder = "Забележка...",
                            value = fieldModel.data,
                            lamb = {
                                fieldModel.data = it
                            })
                    }
                }
            }
        }

        // 3. Навигация (BottomPaging) - фиксирана най-отдолу
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// endregion Page Five

@Composable
fun FunkcionalenTestnaZvukovSignalizator(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списък с карти - ползваме weight(1f), за да не "избягат" стрелките
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Блокче 2: Ред с "изправни" и Да/Не бутони
                        LabeledBinaryChoice(model, label = "изправни") {

                        }

                        // Блокче 3: Поле за забележка
                        Spacer(modifier = Modifier.height(8.dp))
                        val fieldModel = model as CheckedModel
                        DataField(
                            model,
                            placeholder = "Забележка...",
                            value = fieldModel.data,
                            lamb = {
                                fieldModel.data = it
                            })
                    }
                }
            }
        }

        // 3. Навигация (BottomPaging) - фиксирана най-отдолу
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
@Composable
fun ProverkaDopylnitelniSyoraveniq(
    modifier: Modifier,
    title : String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списък с карти - ползваме weight(1f), за да не "избягат" стрелките
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Блокче 2: Ред с "изправни" и Да/Не бутони
                        LabeledBinaryChoice(model, label = "изправни") {

                        }

                        // Блокче 3: Поле за забележка
                        Spacer(modifier = Modifier.height(8.dp))
                        val fieldModel = model as CheckedModel
                        DataField(
                            model,
                            placeholder = "Забележка...",
                            value = fieldModel.data,
                            lamb = {
                                fieldModel.data = it
                            })
                    }
                }
            }
        }

        // 3. Навигация (BottomPaging) - фиксирана най-отдолу
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// region Page Eight
@Composable
fun ProverkaRychenSpiratelenKran(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)

        MyColumn(modifier = Modifier.weight(1f)) {
            models.forEach { model ->
                val checkedModel = model as CheckedModel
                MyCard {
                    Column(Modifier.padding(16.dp)) {
                        BinaryChoice(model)
                        Spacer(modifier = Modifier.height(8.dp))
                        DataField(model, value = checkedModel.data, lamb = {
                            checkedModel.data = it
                        })
                    }
                }
            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// endregion Page Eight
@Composable
fun ProverkaRychnoPuskovoUstrojstvo(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title = title)

        MyColumn(modifier = Modifier.weight(1f)) {
            models.forEach { model ->
                val checkedModel = model as CheckedModel
                MyCard {
                    Column(Modifier.padding(16.dp)) {
                        BinaryChoice(model)
                        Spacer(modifier = Modifier.height(8.dp))
                        DataField(model, value = checkedModel.data, lamb = {
                            checkedModel.data = it
                        })
                    }
                }
            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//region Page Ten
@Composable
fun PregledRezervnoZahranvane(
    modifier: Modifier,
    title: String,
    section : String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списък с карти (Автоматично обхожда всичките 14 модела)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Блокче 1: Заглавие (Зареждане, Годна, Дата на производство и т.н.)
                        QuestionHeader(model.subTitle)

                        // Блокче 2: Чекбоксове Да/Не (само ако моделът е ICheckable)
                        // На тази страница изглеждат по-добре центрирани (BinaryChoice)

                        when (model) {
                            is TextModel -> {
                                JustLabel(model)
                            }

                            is FieldModel -> {
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }

                            is FieldModelTwo -> {
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                                Spacer(modifier = Modifier.padding(16.dp))
                                DataField(
                                    model,
                                    placeholder = "Забележка...",
                                    value = model.dataTwo,
                                    lamb = {
                                        model.dataTwo = it
                                    })
                            }

                            is CheckedModel -> {
                                BinaryChoice(model)
                            }

                            is CheckedModelTwo -> {
                                BinaryChoice(model)
                                DataField(
                                    model,
                                    placeholder = "Забележка...",
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })
                            }

                            is CheckedModelThree -> {
                                BinaryChoice(model)
                                Row {
                                    Row(
                                        Modifier
                                            .weight(0.5f)
                                            .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                    ) {
                                        DataField(
                                            model,
                                            enabled = false,
                                            value = model.previousMeasurement
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .weight(0.5f)
                                            .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                    ) {
                                        CountFieldThree(
                                            model,
                                            placeholder = "Текущ...",
                                            value = model.currentMeasurement)
                                    }
                                }
                                DataField(
                                    model,
                                    placeholder = "Забележка...",
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })
                            }
                        }

                    }
                }
            }
        }

        // 3. Навигация - фиксирана долу
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

// region Page Eleven
@Composable
fun ProverkaLupoveILinii(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Основно заглавие на страницата
        Title(title)



        // 2. Скролираща се част с картите (weight 1 държи навигацията долу)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {

                    Column(modifier = Modifier.padding(16.dp)) {

                        when (model) {
                            is FieldModel -> {
                                DataField(
                                    model,
                                    placeholder = "забележка...",
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })
                            }

                            is CheckedModel -> {
                                // Блокче 1: Подзаглавие (напр. "Кръг 1")
                                QuestionHeader(model.subTitle)

                                // Блокче 2: Избор "изправни" Да/Не
                                LabeledBinaryChoice(model, label = "изправни") {

                                }
                            }
                        }

                    }
                }
            }
        }

        // 3. Навигация - винаги закована на дъното
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// endregion

//region Page Twelve
@Composable
fun ProverkaPojaroizvestitelenDetektor(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->
                val checkedModelTwo = model as CheckedModelTwo
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        QuestionHeader(model.subTitle)
                        CountInputField(model) // Новото блокче за "брой"
                        LabeledBinaryChoice(model) {

                        }
                        DataField(model, value = checkedModelTwo.data, lamb = {
                            checkedModelTwo.data = it
                        })
                    }
                }
            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

//region Page Fifteen
@Composable
fun ProverkaSvobodnoProstranstvoOkoloPojaroizvestitelenDetektor(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                when (model) {
                    is FieldModel -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                QuestionHeader(model.subTitle)
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }

                    is CheckedModel -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //   QuestionHeader(model.subTitle)
                                BinaryChoice(model)
                            }
                        }
                    }
                }

            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

@Composable
fun TestMehanizamVsekiRychenButon(modifier: Modifier, title: String, section: String,pagerState: PagerState) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                when (model) {
                    is CountModel -> {
                        if (section == "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент") {
                            MyCard {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    CountInputField(model, "брой")
                                }
                            }
                        }
                    }

                    is CheckedModel -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }

                    is CheckedModelTwo -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }
                }

            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
@Composable
fun ProverkaNaDostypDoVsichkiPojaroizvestitelniButoni(modifier: Modifier, title: String, section: String,pagerState: PagerState) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                when (model) {
                    is CountModel -> {
                        if (section == "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент") {
                            MyCard {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    CountInputField(model, "брой")
                                }
                            }
                        }
                    }

                    is CheckedModel -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }

                    is CheckedModelTwo -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }
                }

            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
@Composable
fun ProverkaZaNalichieUkazatelniZnaci(modifier: Modifier, title: String, section: String,pagerState: PagerState) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                when (model) {
                    is CountModel -> {
                        if (section == "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент") {
                            MyCard {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    CountInputField(model, "брой")
                                }
                            }
                        }
                    }

                    is CheckedModel -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }

                    is CheckedModelTwo -> {
                        MyCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                //     QuestionHeader(model.subTitle)
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }
                    }
                }

            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//region Page Thourteen
@Composable
fun VidSydZaGAsitelenAgent(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                if (model is CheckedModelTwo) {
                    val checkedModel = model
                    MyCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            //     QuestionHeader(model.subTitle)
                            LabeledBinaryChoice(model) {
                                model.data = it
                            }
                            DataField(model, value = model.data, lamb = {
                                model.data = it
                            })
                        }
                    }
                } else if (model is CheckedModel) {
                    MyCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            //     QuestionHeader(model.subTitle)
                            LabeledBinaryChoice(model, label = "Херметически затворен") {
                                model.data = it
                            }
                            DataField(model, value = model.data, lamb = {
                                model.data = it
                            })
                        }
                    }
                }
            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

@Composable
fun ProverkaSistemaZaOtkrivaneNaTechove(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title = title)


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                if (model is CheckedModelTwo) {
                    val checkedModel = model
                    MyCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            //     QuestionHeader(model.subTitle)
                            LabeledBinaryChoice(model) {
                                model.data = it
                            }
                            DataField(model, value = model.data, lamb = {
                                model.data = it
                            })
                        }
                    }
                } else if (model is CheckedModel) {
                    MyCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            //     QuestionHeader(model.subTitle)
                            LabeledBinaryChoice(model, label = "Херметически затворен") {
                                model.data = it
                            }
                            DataField(model, value = model.data, lamb = {
                                model.data = it
                            })
                        }
                    }
                }
            }
        }
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// region Page Seventeen
@Composable
fun SeventeenPage(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие
        Title(title)



        // 2. Списък с карти (weight 1 за фиксирана навигация)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        QuestionHeader(model.subTitle)

                        BinaryChoice(model)

                    }
                }
            }
        }

        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// endregion


// region Page Eighteen
@Composable
fun VizualnaProverkaNaSydoveteZaGasitelenAgent(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)



        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp)
        ) {
            models.forEachIndexed { index, model ->
                val extendedModel = model as ExtendedCheckedModel

                ExtendedCheckedCard(index, extendedModel)

            }
        }

        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// region Page Eighteen
@Composable
fun VizualnaProverkaNaSydoveteZaGasitelenAgentAerozol(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)



        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp)
        ) {
            models.forEachIndexed { index, model ->
                val extendedModel = model as ExtendedCheckedModel

                ExtendedCheckedCardAerozol(index, extendedModel)

            }
        }

        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}


// endregion

// region Page twenty
@Composable
fun ProverkaSignalniIIzneseniUstrojstva(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие
        Title(title)



        // 2. Списък с модели
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        QuestionHeader(model.subTitle)

                        when (model) {
                            is CheckedModel -> {
                                LabeledBinaryChoice(model) {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }

                            is CheckedModelTwo -> {
                                CountInputField(model, label = "брой")
                                LabeledBinaryChoice(model, label = "изправни") {

                                }
                                DataField(model, value = model.data, lamb = {
                                    model.data = it
                                })
                            }
                        }

                    }
                }
            }
        }

        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
// endregion

//region Page Twenty One (Универсална за 18-30)
@Composable
fun ZakluchitelniProverki(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {

    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(title)



        // 2. Списък с карти (Автоматично подреждане)
        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            models.forEach { model ->

                val checkedModel = model as CheckedModel
                MyCard {
                    Column(modifier = Modifier.padding(16.dp)) {

                        LabeledBinaryChoice(model, label = "изправни") {

                        }

                        // Блокче 4: Поле за забележка/данни (DataField)
                        // Показваме го, ако е TextModel или ако чекбокс моделът има данни
                        Spacer(modifier = Modifier.height(4.dp))
                        DataField(model, value = checkedModel.data, lamb = {
                            checkedModel.data = it
                        })
                    }
                }
            }
        }

        // 3. Навигация - винаги закована на дъното
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

// region Last Page
@Composable
fun LastPage(
    modifier: Modifier,
    title: String,
    section: String,
    pagerState: PagerState
) {
    val models = mapOfModels[section] ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        Title(title)


        val fieldModel = models[0] as FieldModel
        MyColumn(modifier = Modifier.weight(1f)) {
            MyCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    DataField(fieldModel, value = fieldModel.data, lamb = {
                        fieldModel.data = it
                    })
                }
            }
        }

        BottomPaging(pagerState)
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

// endregion
@Composable
private fun OpenCamera(
    modifier: Modifier,
    pagerState: PagerState
) {
    val context = LocalContext.current

    var errorEvent by remember {
        mutableStateOf<OnResponseBody?>(null)
    }
    var triggerCamera by remember {
        mutableIntStateOf(0)
    }
//    var barcodeText by remember {
//        mutableStateOf("")
//    }


    val coroutineScope = rememberCoroutineScope()
    val permission = Manifest.permission.CAMERA

    var permissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
    }

    // Safely trigger the check/request after composition
    LaunchedEffect(key1 = permissionGranted) {
        if (!permissionGranted) {
            launcher.launch(permission)
        }
    }
    // UI State branching
    if (permissionGranted) {
        StartCamera(modifier, pagerState) { resultFromScanning ->
            triggerCamera++
            barcodeText.value = resultFromScanning
          //  PreferencesManager().setBarcodeNumber(context, barcodeText.value)
        }
    } else {
        // Optional: Show a placeholder UI telling the user why you need the camera
        Text(text = "Camera permission is required to use this feature.")
    }


    LaunchedEffect (triggerCamera) {


        loadMapData(
            context,
            triggerCamera,
            barcodeText.value,
            getURL() + "/get_sunotech_protokol_details_by_barcode"
        ) {
            onResult->
         //   errorEvent = onResult
         //   if (errorEvent?.responseCode == 200) {

            // what response we get , we continue to page header !
                    coroutineScope.launch {
                        delay(400)
                        pagerState.animateScrollToPage(1)
                    }

          //  }
        }

    }

//    errorEvent?.let {
//        if(it.responseCode != 200) {
//            MyDialog().RetroDialog(it.responseCode, it.responseMessage) {
//                errorEvent = null
//            }
//        }
//    }


}


@Composable
private fun StartCamera(
    modifier: Modifier,
    pagerState: PagerState,
    onScanCameraResult: (text: String) -> Unit
) {
    var scanFlag by remember {
        mutableStateOf(false)
    }

// Hold reference outside AndroidView for lifecycle
    val barcodeView: CompoundBarcodeView? = null

    DisposableEffect(Unit) {
        onDispose {
            barcodeView?.pause()
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // 1. Camera Section (Takes up most of the screen, leaving room below)
        Box(
            modifier = Modifier
                .weight(.9f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            //  i have this code to read barcode (in the case barcode is numeric not qr)
            AndroidView(
                factory = { context ->
                    CompoundBarcodeView(context).apply {
                        val capture = CaptureManager(context as Activity, this)
                        capture.initializeFromIntent(context.intent, null)

                        // Restrict formats + TRY_HARDER
                        val hints = mapOf(DecodeHintType.TRY_HARDER to true)
                        this.barcodeView.decoderFactory = DefaultDecoderFactory(
                            listOf(
                                BarcodeFormat.CODE_128,
                                BarcodeFormat.CODE_39,
                                BarcodeFormat.CODE_93
                            ),
                            hints,
                            null,
                            0
                        )

                        // Continuous autofocus
                        this.cameraSettings.isContinuousFocusEnabled = true

                        // Torch on (ако е на тъмно задължително)
                        //  this.setTorch(true)

                        setStatusText("")
                        capture.decode()

                        this.decodeContinuous { result ->
                            if (scanFlag) return@decodeContinuous
                            scanFlag = true
                            result.text?.let { barcode ->
                                onScanCameraResult(barcode)
                                scanFlag = false
                            }
                        }

                        this.resume()
                    }
                }
            )

        }
        BottomPaging(pagerState = pagerState)
    }

}

// region Page Header
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageHeader(
    modifier: Modifier,
    page: Int,
    section: String,
    pagerState: PagerState
) {

    val context = LocalContext.current

    var triggerRequest by remember {
        mutableIntStateOf(0)
    }
    var errorEvent by remember {
        mutableStateOf<OnResponseBody?>(null)
    }
    var loadingData by remember {
        mutableStateOf(false)
    }
    val models = mapOfModels[section] ?: emptyList()

    //val objectIdModel = models[0] as CountModel


    Box(contentAlignment = Alignment.Center) {
        Column(modifier = modifier.fillMaxSize()) {


            Text(
                text = section,
                Modifier.padding(8.dp), fontSize = TITLE_FONT_SIZE,
                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground
            )

            MyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                MyCard {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TestField(models[0], placeholder = "Номер на обект",value = (models[0] as CountModel).data) {
                            (models[0] as CountModel).data = it
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            loadingData = true
                            triggerRequest++
                        }, modifier = Modifier.height(40.dp)) {
                            Text(text = "Зареждане по номер на Обект")
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
                MyCard() {
                    Column(modifier = Modifier.padding(16.dp)) {
                        DataField(
                            models[1],
                            value = (models[1] as FieldModel).data,
                            placeholder = "Номер на баркод"
                        ) {
                            (models[1] as FieldModel).data = it
                        }
                    }

                }


                for (i in 2 until models.size) {
                    val model = models[i]

                    MyCard {
                        Column(Modifier.padding(16.dp)) {
                            QuestionHeader(model.subTitle)

                            when (model) {
                                is CheckedModel -> {
                                    BinaryChoice(model)
                                }

                                is CountModel -> {
                                    CountField(model, value = model.data)
                                }

                                is FieldModel -> DataField(
                                    model,
                                    value = model.data,
                                    lamb = {
                                        model.data = it
                                    })

                                is DropDownModel -> {

                                    when(model.subTitle) {
                                        "Обект" -> {
                                            DropDown(model, options = remember {
                                                listOf(
                                                    PreviewOption("ФЕЦ Хасково 1", 1),
                                                    PreviewOption("ФЕЦ Хасково 2", 2),
                                                    PreviewOption("ФЕЦ Хасково 3", 3),
                                                )
                                            }) {
                                                model.data = it
                                            }
                                        }
                                        "Модел и тип на инсталацията" -> {
                                            DropDown(model, options = remember {
                                                listOf(
                                                    PreviewOption("Аерозолна ПГИ", 1),
                                                    PreviewOption("Газова ПГИ", 2)
                                                )
                                            }) {
                                                model.data = it
                                            }
                                        }
                                        "Модел на автоматика за управление" -> {
                                            DropDown(model, options = remember {
                                                listOf(
                                                    PreviewOption("Smart Line", 1),
                                                    PreviewOption("Kentec Sigma XT K21021M2", 2),
                                                    PreviewOption("Tele Tek IVY", 3),
                                                    PreviewOption("Advanced Ex - 3001", 4),
                                                    PreviewOption("Siemenes XC 1001-A", 5),
                                                    PreviewOption("BOSCH", 6)
                                                )
                                            }) {
                                                model.data = it
                                            }
                                        }
                                        "Вид на гасителен агент" -> {
                                            DropDown(model, options = remember {
                                                listOf(
                                                    PreviewOption(text = "NC 1230 (FK-5-1-12)", 1),
                                                    PreviewOption(text = "Novec 1230", 2),
                                                    PreviewOption(text = "HFC 227ea", 3),
                                                    PreviewOption(text = "FM 200", 4),
                                                    PreviewOption(text = "АЗОТ", 5),
                                                    PreviewOption(text = "HFC-125 - Флуоросъдържащ парников газ", 6),
                                                )
                                            }) {
                                                model.data = it
                                            }
                                        }
                                    }

                                }

//                                is DropDownModelTwo -> {
//                                    DropDownGasitelenAgent(model)
//                                }
                            }
                        }
                    }
                }
            }



            BottomPaging(pagerState)
            Spacer(modifier = Modifier.height(8.dp))

        }
        if(loadingData) {
            CircularProgressIndicator()
        }
    }


    LaunchedEffect(triggerRequest) {

            loadMapData(
                context, triggerRequest,
                (models[0] as CountModel).data,
                getURL() + "/get_sunotech_protokol_details_by_id",
            ) { onResponse ->
                errorEvent = onResponse
                loadingData = false
            }


    }
    errorEvent?.let {
        if(it.responseCode != 200) {
            MyDialog().RetroDialog(it.responseCode, it.responseMessage) {
                errorEvent = null
            }
        }
    }

}

// endregion Page Header

fun loadMapData(
    context: Context,
    triggerRequest : Int,
    id: String,
    url: String,
    result : (onResponse : OnResponseBody) -> Unit) {

    if(triggerRequest == 0) return

    val api: ISunotechAPI = RetrofitInstance.getInstance().create(ISunotechAPI::class.java)


    val prefManager = PreferencesManager()
    val token = prefManager.getToken(context)
    val installationType = prefManager.getInstallationType(context)
// for INIT DEFAULT DATA HELP barcodeText = "106 / 02.12.2024 г."


    api.getProtocolData(
        url,
        ObjectIdModel(installationType,id), token
    )
        .enqueue(object : retrofit2.Callback<MyJsonObject> {
            override fun onResponse(
                call: Call<MyJsonObject?>,
                response: Response<MyJsonObject?>
            ) {


                val result = response.body()

                if (result != null) {


                    PreferencesManager().setObjectId(context, result.objectId)
                    // very important !!!
                    mapOfModels.clear()



                    val mutableMap = result.mutableMap
                    mutableMap.put("Камера", ArrayList())


                    mutableMap.forEach { key, value ->
                        // if we have barcode no need to overwrite it !!!
                        if (key == "Камера") {
                      //      return@forEach
                        }

                        val list = mutableMap[key]
                        val newList = ArrayList<IModel>()
                        list?.forEach { model ->
                            when (model.type) {
                                "checkable" -> {
                                    val jsonCheckedModel = model as JsonCheckedModel
                                    val checkedModel =
                                        CheckedModel(
                                            jsonCheckedModel.subTitle
                                        )
                                    checkedModel.checked = jsonCheckedModel.checked
                                    checkedModel.unchecked = jsonCheckedModel.unchecked
                                    checkedModel.data = jsonCheckedModel.data
                                    newList.add(checkedModel)
                                }

                                "checkable count" -> {
                                    val jsonCheckedModelTwo = model as JsonCheckedDataModelTwo
                                    val checkedModelTwo =
                                        CheckedModelTwo(
                                            jsonCheckedModelTwo.subTitle
                                        )
                                    checkedModelTwo.checked = jsonCheckedModelTwo.checked
                                    checkedModelTwo.unchecked = jsonCheckedModelTwo.unchecked
                                    checkedModelTwo.data = jsonCheckedModelTwo.data
                                    checkedModelTwo.count = jsonCheckedModelTwo.count
                                    newList.add(checkedModelTwo)
                                }

                                "checkable measure" -> {
                                    val jsonCheckedModelThree =
                                        model as JsonCheckedDataModelThree
                                    val checkedModelThree =
                                        CheckedModelThree(
                                            jsonCheckedModelThree.subTitle
                                        )
                                    checkedModelThree.checked = jsonCheckedModelThree.checked
                                    checkedModelThree.unchecked =
                                        jsonCheckedModelThree.unchecked
                                    checkedModelThree.data = jsonCheckedModelThree.data
                                    checkedModelThree.previousMeasurement =
                                        jsonCheckedModelThree.currentMeasurement
                                   // checkedModelThree.currentMeasurement = jsonCheckedModelThree.currentMeasurement
                                    newList.add(checkedModelThree)
                                }

                                "count" -> {
                                    val jsonCountModel = model as JsonCountModel
                                    val countModel = CountModel(
                                        jsonCountModel.subTitle
                                    )
                                    countModel.data = jsonCountModel.data
                                    newList.add(countModel)
                                }

                                "checkable extended" -> {
                                    val jsonCheckedDataModelExtended =
                                        model as JsonCheckedDataModelExtended
                                    val extendedCheckedModel =
                                        ExtendedCheckedModel(
                                            jsonCheckedDataModelExtended.subTitle
                                        )
                                    extendedCheckedModel.checked =
                                        jsonCheckedDataModelExtended.checked
                                    extendedCheckedModel.unchecked =
                                        jsonCheckedDataModelExtended.unchecked
                                    extendedCheckedModel.data =
                                        jsonCheckedDataModelExtended.data

                                    extendedCheckedModel.lastPressure = jsonCheckedDataModelExtended.pressure
                                   // extendedCheckedModel.pressure = jsonCheckedDataModelExtended.pressure
                                    extendedCheckedModel.fabNum =
                                        jsonCheckedDataModelExtended.fabNum
                                    extendedCheckedModel.hidrostatMeasurementDate =
                                        jsonCheckedDataModelExtended.hidrostatMeasurementDate
                                    extendedCheckedModel.device =
                                        jsonCheckedDataModelExtended.device
                                    newList.add(extendedCheckedModel)
                                }

                                "field" -> {
                                    val jsonFiledModel = model as JsonFieldModel
                                    val fieldModel = FieldModel(
                                        jsonFiledModel.subTitle
                                    )
                                    fieldModel.data = jsonFiledModel.data
                                    newList.add(fieldModel)
                                }

                                "field data two" -> {
                                    val jsonFieldModelTwo = model as JsonFieldModelTwo
                                    val fieldModelTwo =
                                        FieldModelTwo(
                                            jsonFieldModelTwo.subTitle
                                        )
                                    fieldModelTwo.data = jsonFieldModelTwo.data
                                    fieldModelTwo.dataTwo = jsonFieldModelTwo.dataTwo
                                    newList.add(fieldModelTwo)
                                }

                                "field data three" -> {
                                    val jsonFieldModelThree = model as JsonFieldModelThree
                                    val fieldModelThree =
                                        FieldModelThree(
                                            jsonFieldModelThree.subTitle
                                        )
                                    fieldModelThree.data = jsonFieldModelThree.data

                                    fieldModelThree.oldPressure = jsonFieldModelThree.pressure
                                 //   fieldModelThree.pressure = jsonFieldModelThree.pressure
                                    newList.add(fieldModelThree)
                                }

                                "text" -> {
                                    val jsonTextModel = model as JsonTextModel
                                    val textModel = TextModel(
                                        jsonTextModel.subTitle
                                    )
                                    textModel.data = jsonTextModel.data
                                    newList.add(textModel)
                                }

                                "dropdown" -> {
                                    val jsonDropDownModel = model as JsonDropDownModel
                                    val dropDownModel = DropDownModel(
                                        jsonDropDownModel.subTitle
                                    )
                                    dropDownModel.data = jsonDropDownModel.data
                                    newList.add(dropDownModel)
                                }

                                "dropdowntwo" -> {
                                    val jsonDropDownModelTwo = model as JsonDropDownModelTwo
                                    val dropDownModelTwo = DropDownModelTwo(
                                        jsonDropDownModelTwo.subTitle
                                    )
                                    dropDownModelTwo.data = jsonDropDownModelTwo.data
                                    newList.add(dropDownModelTwo)
                                }
                            }


                        }
                        //mapOfModels[key]?.clear()
                        mapOfModels[key] = newList

                    }
                }
                result(OnResponseBody(response.code(),response.message()))

            }

            override fun onFailure(
                call: Call<MyJsonObject?>,
                t: Throwable
            ) {
                t.printStackTrace()
                // both success or failure we continue to hea

                result(OnResponseBody(500,t.message ?: "Сървърна грешка"))
            }


        })

}

fun completeProtocol(
    context: Context, user: String, token: String, installationType: String,
     onSuccess : (HttpResponse) -> Unit
) {


    // for internal storage
    /* context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
         it.write(json2.toByteArray())
         it.close()
     }*/

    // for debugging
    val titles = if(installationType == "aerozol") aerozolSections else gasSections


    for (i in 1 until titles.size) {
        val title = titles[i]

        //      val x = title
        val values: ArrayList<IModel> = mapOfModels[title] ?: arrayListOf()

        val jsonList = arrayListOf<JsonModel>()
        values.forEach { model ->

            when (model) {
                is TextModel -> {
                    jsonList.add(JsonTextModel(model.subTitle,  model.data))
                }

                is CountModel -> {
                    jsonList.add(JsonCountModel(model.subTitle,  model.data))
                }

                is FieldModel -> {
                    jsonList.add(JsonFieldModel(model.subTitle,  model.data))
                }

                is FieldModelTwo -> {
                    jsonList.add(
                        JsonFieldModelTwo(
                            model.subTitle, model.data,
                            model.dataTwo
                        )
                    )
                }

                is FieldModelThree -> {
                    jsonList.add(
                        JsonFieldModelThree(
                            model.subTitle,  model.data,
                            model.pressure, model.oldPressure
                        )
                    )
                }

                is CheckedModel -> {
                    jsonList.add(
                        JsonCheckedModel(
                            model.subTitle,
                            model.checked, model.unchecked,
                            model.data
                        )
                    )
                }

                is CheckedModelTwo -> {
                    jsonList.add(
                        JsonCheckedDataModelTwo(
                            model.subTitle,
                            model.checked, model.unchecked, model.data, model.count
                        )
                    )
                }

                is CheckedModelThree -> {
                    jsonList.add(
                        JsonCheckedDataModelThree(
                            model.subTitle,
                            model.checked, model.unchecked, model.data,
                            model.previousMeasurement, model.currentMeasurement
                        )
                    )
                }

                is ExtendedCheckedModel -> {
                    jsonList.add(
                        JsonCheckedDataModelExtended(
                            model.subTitle,
                            model.checked, model.unchecked, model.data,
                            model.pressure, model.lastPressure, model.fabNum,
                            model.hidrostatMeasurementDate, model.device
                        )
                    )
                }

                is DropDownModel -> {
                    jsonList.add(
                        JsonDropDownModel(
                            model.subTitle,
                            model.data
                        )
                    )
                }

                is DropDownModelTwo -> {
                    jsonList.add(
                        JsonDropDownModelTwo(
                            model.subTitle,
                            model.data
                        )
                    )
                }

            }
        }
        jsonMap.put(title, jsonList)
    }
    val headerModels: ArrayList<IModel> = mapOfModels[titles[1]]!!
    val objectIdModel = headerModels[0] as CountModel
    val barcodeModel = headerModels[1] as FieldModel
    val documentDateModel = headerModels[5] as FieldModel
    val objectId = objectIdModel.data
    val barcodeNumber = barcodeModel.data
    val contractDate = documentDateModel.data
    val gson = Gson()
    val jsonBody =
        MyJsonObject(
            installationType,
            objectId,
            barcodeNumber = barcodeNumber, operatorName = user,
            contractDate, jsonMap
        )
    val jsonString: String = gson.toJson(jsonBody)
    Log.d("RETROFIT_DEBUG", "Sending JSON: $jsonString")
    val fileName = "jsonModels.txt"
    val debugFile =
        File(
            context.getExternalFilesDir(null),
            fileName
        )
    debugFile.writeText(jsonString)

    val sunInterface = RetrofitInstance.getInstance().create(ISunotechAPI::class.java)


    sunInterface.writeProtocol(jsonBody,token).enqueue(object : retrofit2.Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody?>,
            response: Response<ResponseBody?>
        ) {
            onSuccess(HttpResponse(response.code(),response.message()))
        }

        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
            onSuccess(HttpResponse(500, t.message ?: "Сървърна Грешка"))
        }

    })

//    sunInterface.writeProtokol(jsonBody,
//        token).enqueue(object : retrofit2.Callback<ResponseBody> {
//        @RequiresApi(Build.VERSION_CODES.Q)
//        override fun onResponse(
//            call: Call<ResponseBody>,
//            response: Response<ResponseBody>
//        ) {
//            if (response.isSuccessful) {




                // Успешно изпращане
//                val headers = response.headers()
//                val fileName = headers["fileName"] ?: "document_${System.currentTimeMillis()}"
//
//                val result = response.body()
//                if (result != null) {
//
//                    CoroutineScope(Dispatchers.Main).launch {
//
//                        val savedFile = withContext(Dispatchers.IO) {
//                            // 1. Записваме файла на заден план (IO нишка)
//                            savePdfToMediaStore(context, result, fileName)
//                        }
//
//                        val activity = context as? Activity
//
//                        if (activity == null ||
//                            activity.isFinishing || activity.isDestroyed) return@launch
//
//                        if (savedFile != null) {
//                            openPdfFile(context, savedFile)
//                        }
//                    }
//                }




//           }
//                       onSuccess(HttpResponse(response.code(),response.message()))
//        }
//
//        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
//                 // Грешка при мрежовата връзка
//                 // MyDialog().RetroDialog(500,t.message!!) { }
//                     onSuccess(HttpResponse(500,t.message ?: "Сървърна грешка"))
//        }
//    })

}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FireExtinguishingInstallationsMobileTheme {
//        MainScreen(modifier = Modifier)
//    }
//}