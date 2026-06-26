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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fireextinguishinginstallationsmobile.data.mapOfModels
import com.example.fireextinguishinginstallationsmobile.data.titles
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
import com.example.fireextinguishinginstallationsmobile.ui.theme.FireExtinguishingInstallationsMobileTheme
import com.example.fireextinguishinginstallationsmobile.utils.MyDialog
import com.example.fireextinguishinginstallationsmobile.utils.PreferencesManager
import com.example.fireextinguishinginstallationsmobile.utils.openPdfFile
import com.example.fireextinguishinginstallationsmobile.utils.savePdfToMediaStore
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File


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
                                    } else {
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
                                context, {
                                    token = it
                                    PreferencesManager().putToken(context, it)
                                })
                            if (showDialog)
                                MyDialog().RetroDialog(httpResponse.code, httpResponse.message) {
                                    showDialog = false
                                }
                        } else {
                            MainScreen(
                                Modifier
                                    .padding(innerPadding)
                                    .imePadding()
                                    .padding(10.dp, 15.dp, 10.dp, 0.dp)
                            )
                        }


                    }

                }
            }
        }
    }
}


@Composable
fun LoginPage(modifier: Modifier, context: Context, onRefreshToken: (String) -> Unit) {

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
                            response.let {
                                val loginResult = it.body()

                                if (it.code() == 200) {
                                    onRefreshToken(loginResult!!.token)

                                }
                                Log.e("token: ", " ${loginResult?.token}")
                            }


                        }

                        override fun onFailure(
                            call: Call<LoginRes?>,
                            t: Throwable
                        ) {
                            Log.e("Failure: ", t.message!!)
                        }

                    })
                }) {
                    Text(text = "Влез")
                }
            }


        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {

    val pagerState = rememberPagerState(
        0,
        pageCount = {
            mapOfModels.size + 1
        })

    // 1. Create a custom fling behavior
    val customFlingBehavior = PagerDefaults.flingBehavior(
        pagerState,
        snapAnimationSpec = tween(
            durationMillis = 450,
            easing = LinearOutSlowInEasing
        )
    )


    // region Pager
    HorizontalPager(
        state = pagerState,
        flingBehavior = customFlingBehavior,
        userScrollEnabled = false,
        // key = { pageIndex -> "page_key_$pageIndex" }

    ) { page ->
        when (page) {
            0 -> InitialPage(modifier, pagerState)
            1 -> OpenCamera(modifier, pagerState)
            2 -> PageHeader(modifier, page, pagerState)
            3 -> PageThree(modifier, page, pagerState)
            4 -> PageFour(modifier, page, pagerState)
            5 -> PageFive(modifier, page, pagerState)
            6 -> PageSix(modifier, page, pagerState)
            7, 8, 9 -> PageSeven(modifier, page, pagerState)
            10, 11 -> PageTen(modifier, page, pagerState)
            12 -> PageTwelve(modifier, page, pagerState)
            13 -> PageThirteen(modifier, page, pagerState)
            14 -> PageFourteen(modifier, page, pagerState)
            15 -> PageFifteen(modifier, page, pagerState)
            16, 17, 18 -> PageSixteen(modifier, page, pagerState)
            19, 21 -> PageNineteen(modifier, page, pagerState)
            20 -> PageTwenty(modifier, page, pagerState)
            22 -> PageTwentyTwo(modifier, page, pagerState)
            23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 -> PageTwentyThree(
                modifier,
                page,
                pagerState
            )

            35 -> LastPage(modifier, page, pagerState)

        }

    }
    // endregion Pager
}

@Composable
fun InitialPage(modifier: Modifier, pagerState: PagerState) {

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    var shouldLoadDefaultData by remember {
        mutableStateOf(false)
    }
    var shouldLoadCurrentData by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center) {
        MyCard {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    shouldLoadDefaultData = true
                }, modifier = Modifier.height(60.dp)) {
                    Text(text = "Зареждане на данни по подразбиране")
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = {
                    shouldLoadCurrentData = true
                }, modifier = Modifier.height(60.dp)) {
                    Text(text = "Зареждане на текущи данни")
                }
            }
        }
    }



    if (shouldLoadDefaultData) {
        PreferencesManager().setDataLoadingType(context, true)

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(1)
            }
        }

    }
    if (shouldLoadCurrentData) {
        PreferencesManager().setDataLoadingType(context, false)

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(1)
            }
        }
    }


}


//region Page One
@Composable
fun PageThree(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {


    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]]!!

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
        BottomPaging(pagerState)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
//endregion

//region Page Two
@Composable
fun PageFour(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {

        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]]!!

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
fun PageFive(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize()) {
        // Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]]!!

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
                                    DataField(
                                        model,
                                        value = model.previousMeasurement,
                                        enabled = false
                                    )
                                }
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {
                                    DataField(
                                        model, placeholder = "Текущ...",
                                        value = model.currentMeasurement, lamb = {
                                            model.currentMeasurement = it
                                        })
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
                                    DataField(
                                        model,
                                        value = model.oldPressure,
                                        enabled = false
                                    )
                                }
                                Row(
                                    Modifier
                                        .weight(0.5f)
                                        .padding(0.dp, 0.dp, 4.dp, 8.dp)
                                ) {
                                    DataField(
                                        model,
                                        placeholder = "Текущ...",
                                        value = model.pressure,
                                        lamb = {
                                            // if (pressureAsFloat != null && pressureAsFloat > 0.0f) {
                                            model.pressure = it
                                            //  }
                                        })
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
fun PageSix(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]]!!

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
                                        DataField(
                                            model,
                                            placeholder = "Текущ...",
                                            value = model.pressure,
                                            lamb = {
                                                model.pressure = it
                                            })
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

            // 3. Навигацията - винаги видима
            BottomPaging(pagerState)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
//endregion Page Fourth


//region Page Five
@Composable
fun PageSeven(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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


// region Page Eight
@Composable
fun PageTen(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {

    Column(modifier = modifier.fillMaxSize()) {
        Title(page)
        val models = mapOfModels[titles[page]]!!
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

//region Page Ten
@Composable
fun PageTwelve(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
                                        DataField(
                                            model,
                                            placeholder = "Текущ...",
                                            value = model.currentMeasurement,
                                            lamb = {
                                                model.currentMeasurement = it
                                            })
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
fun PageThirteen(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Основно заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
fun PageFourteen(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        Title(page)
        val models = mapOfModels[titles[page]] ?: emptyList()

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
fun PageFifteen(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        Title(page)
        val models = mapOfModels[titles[page]] ?: emptyList()

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
fun PageSixteen(modifier: Modifier, page: Int, pagerState: PagerState) {
    Column(modifier = modifier.fillMaxSize()) {
        Title(page)
        val models = mapOfModels[titles[page]] ?: emptyList()

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 4.dp)
        ) {
            models.forEach { model ->

                when (model) {
                    is CountModel -> {
                        if (titles[page] == "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент") {
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
fun PageNineteen(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        Title(page)
        val models = mapOfModels[titles[page]] ?: emptyList()

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


// region Page Seventeen
@Composable
fun SeventeenPage(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
fun PageTwenty(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
// endregion

// region Page twenty
@Composable
fun PageTwentyTwo(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
fun PageTwentyThree(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 1. Заглавие на страницата
        Title(page)

        val models = mapOfModels[titles[page]] ?: emptyList()

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
    page: Int,
    pagerState: PagerState
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        Title(page)

        val models = mapOfModels[titles[page]]!!
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

    var barcodeText by remember {
        mutableStateOf("")
    }

    var dataLoadingType by remember {
        mutableStateOf(PreferencesManager().getDataLoadingType(context))
    }
    var initializationRequest by remember {
        mutableStateOf(false)
    }


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
            barcodeText = resultFromScanning
        }
    } else {
        // Optional: Show a placeholder UI telling the user why you need the camera
        Text(text = "Camera permission is required to use this feature.")
    }

    if (barcodeText.isNotEmpty()) {


        Text(text = barcodeText)
        //barcodeText = ""
        LoadMapData(context, barcodeText, defaultDocument = dataLoadingType) {
            initializationRequest = it

        }
        if (initializationRequest) {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                coroutineScope.launch {

                    pagerState.animateScrollToPage(2)

                }
            }
        }
    }
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
    var barcodeView: CompoundBarcodeView? = null

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

            // What Was Broken at Each Step
            //         VersionProblemOriginalITF + Intent format filter silently ignored → garbage scansMy fixcapture.decode() + decodeContinuous conflict + factory reset by initializeFromIntent → nothing scansThis versionNo CaptureManager, one callback, factory set once → should work
            // If Still Nothing Scans
            //    Check camera permission is granted at runtime before the composable renders — CompoundBarcodeView silently fails with no permission rather than crashing.Claude Fable 5 is currently unavailable.Learn more(opens in new tab)
            //  if (startDataLoading)
            //      SmoothCircularSpinner()


        }
        BottomPaging(pagerState = pagerState)
    }

//    if (barcodeText.isNotEmpty()) {
//
//        startDataLoading = true
//        Text(text = barcodeText)
//        //barcodeText = ""
//        LoadMapData(context) {
//            initializationSuccess = it
//
//        }
//        if (initializationSuccess) {
//            startDataLoading = false
//            val coroutineScope = rememberCoroutineScope()
//            LaunchedEffect(Unit) {
//                coroutineScope.launch {
//
//                    pagerState.animateScrollToPage(2)
//
//                }
//            }
//        }
//    }
}

// region Page Header
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageHeader(
    modifier: Modifier,
    page: Int,
    pagerState: PagerState
) {

    val context = LocalContext.current

    var barcodeField by remember {
        mutableStateOf(PreferencesManager().getObjectId(context))
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = titles[page],
            Modifier.padding(8.dp), fontSize = TITLE_FONT_SIZE,
            fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground
        )


        val models = mapOfModels[titles[page]]!!

        MyColumn(
            modifier = Modifier
                .weight(1f)
        ) {

            MyCard() {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = barcodeField, onValueChange = {
                            barcodeField = it
                        })
                }

            }
            models.forEach { model ->
                MyCard {
                    Column(Modifier.padding(16.dp)) {
                        QuestionHeader(model.subTitle)

                        when (model) {
                            is CheckedModel -> {
                                BinaryChoice(model)
                            }

                            is CountModel -> {
                                CountField(model)
                            }

                            is FieldModel -> DataField(
                                model,
                                value = model.data,
                                lamb = {
                                    model.data = it
                                })

                            is DropDownModel -> {
                                DropDownAutomatika(model)
                            }

                            is DropDownModelTwo -> {
                                DropDownGasitelenAgent(model)
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

// endregion Page Header
@Composable
fun LoadMapData(
    context: Context,
    barcodeText: String,
    defaultDocument: Boolean,
    result: (onSuccess: Boolean) -> Unit
) {
    val api: ISunotechAPI = RetrofitInstance.getInstance().create(ISunotechAPI::class.java)

    val token = PreferencesManager().getToken(context)
// for INIT DEFAULT DATA HELP barcodeText = "106 / 02.12.2024 г."
    api.getProtokolData(
        ObjectIdModel(barcodeText), defaultDocument =
            if (defaultDocument) "true" else "false", token
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
                    mutableMap.forEach { key, value ->
                        val list = mutableMap[key]
                        val newList = ArrayList<IModel>()
                        list?.forEach { model ->
                            when (model.type) {
                                "checkable" -> {
                                    val jsonCheckedModel = model as JsonCheckedModel
                                    val checkedModel =
                                        CheckedModel(
                                            jsonCheckedModel.subTitle,
                                            jsonCheckedModel.position
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
                                            jsonCheckedModelTwo.subTitle,
                                            jsonCheckedModelTwo.position
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
                                            jsonCheckedModelThree.subTitle,
                                            jsonCheckedModelThree.position
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
                                        jsonCountModel.subTitle,
                                        jsonCountModel.position
                                    )
                                    countModel.data = jsonCountModel.data
                                    newList.add(countModel)
                                }

                                "checkable extended" -> {
                                    val jsonCheckedDataModelExtended =
                                        model as JsonCheckedDataModelExtended
                                    val extendedCheckedModel =
                                        ExtendedCheckedModel(
                                            jsonCheckedDataModelExtended.subTitle,
                                            jsonCheckedDataModelExtended.position
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
                                        jsonFiledModel.subTitle,
                                        jsonFiledModel.position
                                    )
                                    fieldModel.data = jsonFiledModel.data
                                    newList.add(fieldModel)
                                }

                                "field data two" -> {
                                    val jsonFieldModelTwo = model as JsonFieldModelTwo
                                    val fieldModelTwo =
                                        FieldModelTwo(
                                            jsonFieldModelTwo.subTitle,
                                            jsonFieldModelTwo.position
                                        )
                                    fieldModelTwo.data = jsonFieldModelTwo.data
                                    fieldModelTwo.dataTwo = jsonFieldModelTwo.dataTwo
                                    newList.add(fieldModelTwo)
                                }

                                "field data three" -> {
                                    val jsonFieldModelThree = model as JsonFieldModelThree
                                    val fieldModelThree =
                                        FieldModelThree(
                                            jsonFieldModelThree.subTitle,
                                            jsonFieldModelThree.position
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
                                        jsonTextModel.subTitle,
                                        jsonTextModel.position
                                    )
                                    textModel.data = jsonTextModel.data
                                    newList.add(textModel)
                                }

                                "dropdown" -> {
                                    val jsonDropDownModel = model as JsonDropDownModel
                                    val dropDownModel = DropDownModel(
                                        jsonDropDownModel.subTitle,
                                        jsonDropDownModel.position
                                    )
                                    dropDownModel.data = jsonDropDownModel.data
                                    newList.add(dropDownModel)
                                }

                                "dropdowntwo" -> {
                                    val jsonDropDownModelTwo = model as JsonDropDownModelTwo
                                    val dropDownModelTwo = DropDownModelTwo(
                                        jsonDropDownModelTwo.subTitle,
                                        jsonDropDownModelTwo.position
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
                result(true)

            }

            override fun onFailure(
                call: Call<MyJsonObject?>,
                t: Throwable
            ) {
                t.printStackTrace()
                // both success or failure we continue to hea
                result(true)
            }


        })
}

fun completeProtocol(
    context: Context
) {


    // for internal storage
    /* context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
         it.write(json2.toByteArray())
         it.close()
     }*/

    // for debugging

    for (i in 2 until titles.size) {
        val title = titles[i]

        //      val x = title
        val values: ArrayList<IModel> = mapOfModels[title]!!

        val jsonList = arrayListOf<JsonModel>()
        values.forEach { model ->

            when (model) {
                is TextModel -> {
                    jsonList.add(JsonTextModel(model.subTitle, model.position, model.data))
                }

                is CountModel -> {
                    jsonList.add(JsonCountModel(model.subTitle, model.position, model.data))
                }

                is FieldModel -> {
                    jsonList.add(JsonFieldModel(model.subTitle, model.position, model.data))
                }

                is FieldModelTwo -> {
                    jsonList.add(
                        JsonFieldModelTwo(
                            model.subTitle, model.position, model.data,
                            model.dataTwo
                        )
                    )
                }

                is FieldModelThree -> {
                    jsonList.add(
                        JsonFieldModelThree(
                            model.subTitle, model.position, model.data,
                            model.pressure, model.oldPressure
                        )
                    )
                }

                is CheckedModel -> {
                    jsonList.add(
                        JsonCheckedModel(
                            model.subTitle, model.position,
                            model.checked, model.unchecked,
                            model.data
                        )
                    )
                }

                is CheckedModelTwo -> {
                    jsonList.add(
                        JsonCheckedDataModelTwo(
                            model.subTitle, model.position,
                            model.checked, model.unchecked, model.data, model.count
                        )
                    )
                }

                is CheckedModelThree -> {
                    jsonList.add(
                        JsonCheckedDataModelThree(
                            model.subTitle, model.position,
                            model.checked, model.unchecked, model.data,
                            model.previousMeasurement, model.currentMeasurement
                        )
                    )
                }

                is ExtendedCheckedModel -> {
                    jsonList.add(
                        JsonCheckedDataModelExtended(
                            model.subTitle, model.position,
                            model.checked, model.unchecked, model.data,
                            model.pressure, model.lastPressure, model.fabNum,
                            model.hidrostatMeasurementDate, model.device
                        )
                    )
                }

                is DropDownModel -> {
                    jsonList.add(
                        JsonDropDownModel(
                            model.subTitle, model.position,
                            model.data
                        )
                    )
                }

                is DropDownModelTwo -> {
                    jsonList.add(
                        JsonDropDownModelTwo(
                            model.subTitle, model.position,
                            model.data
                        )
                    )
                }

            }
        }
        jsonMap.put(title, jsonList)
    }
    val headerModels: ArrayList<IModel> = mapOfModels[titles[2]]!!
    val documentIdModel = headerModels[0] as FieldModel
    val documentNameModel = headerModels[1] as FieldModel
    val documentDate = "24.06.2026";
    val gson = Gson()
    val jsonBody =
        MyJsonObject(
            "WZ522503162309100085", operatorName = "miti",
            documentDate, defaultDocument = "false", jsonMap
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
    sunInterface.writeProtokol(jsonBody).enqueue(object : retrofit2.Callback<ResponseBody> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                // Успешно изпращане
                val headers = response.headers()
                val fileName = headers["fileName"] ?: "document_${System.currentTimeMillis()}"

                val result = response.body()
                if (result != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        // 1. Записваме файла на заден план (IO нишка)
                        val savedFile = savePdfToMediaStore(context, result, fileName)
                        // 2. Връщаме се на Главната нишка (Main), за да отворим PDF-а
                        if (savedFile != null) {
                            withContext(Dispatchers.IO) {
                                openPdfFile(context, savedFile)
                            }
                        }
                    }
                }

            }
        }

        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
            // Грешка при мрежовата връзка MyDialog().RetroDialog(500,t.message!!) { }
        }
    })

}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FireExtinguishingInstallationsMobileTheme {
//        MainScreen(modifier = Modifier)
//    }
//}