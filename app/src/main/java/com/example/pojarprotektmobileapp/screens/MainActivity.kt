package com.example.pojarprotektmobileapp.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pojarprotektmobileapp.R
import com.example.pojarprotektmobileapp.http.ISayHello
import com.example.pojarprotektmobileapp.http.RetrofitInstance
import com.example.pojarprotektmobileapp.http.SayHello
import com.example.pojarprotektmobileapp.models.ArtikulModel
import com.example.pojarprotektmobileapp.models.ArtikulViewModel
import com.example.pojarprotektmobileapp.models.AuthRequest
import com.example.pojarprotektmobileapp.models.GreyArtikulViewModel
import com.example.pojarprotektmobileapp.models.HttpResponse
import com.example.pojarprotektmobileapp.models.LoginRes
import com.example.pojarprotektmobileapp.models.MyViewModel
import com.example.pojarprotektmobileapp.preferences.PreferencesManager
import com.example.pojarprotektmobileapp.ui.theme.PojarprotektMobileAppTheme
import com.example.pojarprotektmobileapp.ui.theme.buttonColor
import com.example.pojarprotektmobileapp.utils.MyDialog
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


//import com.google.accompanist.permissions.rememberPermissionState


class MainActivity : ComponentActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {


            PojarprotektMobileAppTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    val viewModel = viewModel<ArtikulViewModel>()
//                    val  greyViewModel = viewModel<GreyArtikulViewModel>()
//                    val extinguishersViewModel = viewModel<MyViewModel>()
//
//                    var httpResponse by remember {
//                        mutableStateOf(HttpResponse(0,""))
//                    }
//                    var showDialog by remember {
//                        mutableStateOf(false)
//                    }
//                    val pref = PreferencesManager()
//                    val token = pref.getToken(this@MainActivity)
//
//
//                    val hello = SayHello()
//                    hello.SayHello(token) {
//                        viewModel.setTokenIsValid(it.code == 200)
//                        showDialog = it.code != 200
//                        httpResponse = it
//                    }
//
//                    if(viewModel.isTokenValid()) {
//                        CreateUI(context = LocalContext.current,viewModel,greyViewModel,extinguishersViewModel)
//                        viewModel.setTokenIsValid(true)
//                    } else {
//                        CreateLoginScreen(context = LocalContext.current, viewModel)
//                        if(showDialog) {
//                            MyDialog().RetroDialog(title = httpResponse.code,httpResponse.message) {
//                                 showDialog = false
//                            }
//                        }
//                    }

                    OpenCamera()

                }

                }
            }
        }
    }

@Composable
private fun OpenCamera() {
    var shouldStartCamera by remember {
        mutableStateOf(false)
    }
    var permissionGranted by remember {
        mutableStateOf(false)
    }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("msg","CAM ACCESS GRANTED")
            permissionGranted = true;
        } else {
            ActivityResultContracts.RequestPermission()
        }
    }
    val context = LocalContext.current

    val permission = Manifest.permission.CAMERA
    Button(
        onClick = {
            shouldStartCamera = true;
        }
    ) {
        Text(text = "Open Camera")
    }


    if(shouldStartCamera || permissionGranted) {

        permissionGranted = checkAndRequestCameraPermission(
            context = context, permission = permission)
        if (permissionGranted) {
            StartCamera()
        } else {
            launcher.launch(permission)
        }
    }
}

fun checkAndRequestCameraPermission(
    context: Context,
    permission: String
) : Boolean {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    return if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
        true
    } else {
        // Request a permission
        false
    }


}




@Composable
private fun StartCamera() {
    var scanFlag by remember {
        mutableStateOf(false)
    }

    var barcodeText by remember {
        mutableStateOf("")
    }
    AndroidView(
        factory = { context ->
            CompoundBarcodeView(context).apply {
                val capture = CaptureManager(context as Activity, this)
                capture.initializeFromIntent(context.intent, null)
                setStatusText("")
                capture.decode()
                this.decodeContinuous { result ->
                    if (scanFlag) {
                        return@decodeContinuous
                    }
                    println("scanFlag true")
                    scanFlag = true
                    result.text?.let { barCodeOrQr ->
                        println("fak $barCodeOrQr")

                        barcodeText = barCodeOrQr
                        //Do something and when you finish this something
                        //put scanFlag = false to scan another item
                        scanFlag = false
                    }
                    //If you don't put this scanFlag = false, it will never work again.
                    //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning
                }
                this.resume()
            }
        },
        modifier = Modifier
    )

    if(barcodeText.isNotEmpty()) {
        Text(text = barcodeText)
        barcodeText = ""
    }
}



    @Composable
    private fun CreateLoginScreen(context: Context, model: MyViewModel) {
        var userName by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = userName,
                onValueChange = {
                    userName = it
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(1f),
                placeholder = { Text(text = "Потребител ") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Red,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray
                ))

            TextField(
                value = password, onValueChange = {
                    password = it
                }, modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(1f),
                placeholder = { Text(text = "Парола") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                )
            )

            Button(
                onClick = {
                    val retrofitInstance =
                        RetrofitInstance.getInstance().create(ISayHello::class.java)
                    retrofitInstance.login(AuthRequest(userName, password))
                        .enqueue(object : Callback<LoginRes> {
                            override fun onResponse(
                                call: Call<LoginRes>,
                                response: Response<LoginRes>
                            ) {
                                model.setTokenIsValid(response.isSuccessful)
                                if (response.isSuccessful) {
                                    val res: LoginRes = response.body()!!

                                    val preferencesManager = PreferencesManager()
                                    preferencesManager.putToken(context, res.token)
                                }
                            }

                            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                                model.setTokenIsValid(false)
                            }

                        })
                },
                modifier = Modifier
                    .padding(20.dp)
                    .height(55.dp)
                    .width(IntrinsicSize.Max),
                shape = RoundedCornerShape(64.dp),
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(text = "Влез")
            }
        }

    }

    @Composable
    private fun CreateUI(
        context: Context, viewModel: ArtikulViewModel,
        greyViewModel: GreyArtikulViewModel,
        extinguisherViewModel: MyViewModel
    ) {


        val rememberCoroutineScope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            rememberCoroutineScope.launch {
                viewModel.getArtikuls(context, viewModel.getEmptyArtikulsList(), object :
                    MyCallback {
                    override fun finished(result: Boolean) {
                        viewModel.setArtikulsProgressActive(false)
                    }
                })
            }

            rememberCoroutineScope.launch {
                greyViewModel.getArtikuls(context, greyViewModel.getEmptyArtikulsList(),
                    object : MyCallback {
                        override fun finished(result: Boolean) {
                            greyViewModel.setArtikulsProgressActive(false)
                        }

                    })
            }

            rememberCoroutineScope.launch {
                extinguisherViewModel.getExtinguishers(
                    context,
                    extinguisherViewModel.getEmptyExtinguishersList()
                ) {
                    extinguisherViewModel.setExtinguishersProgressActive(false)
                }
            }
        }

        val tabs = getTabs()
        val pagerState = rememberPagerState(pageCount = { tabs.size })

        Column(modifier = Modifier.fillMaxSize()) {
            TabLayout(tabs = tabs, pagerState = pagerState)
            TabContent(
                viewModel = viewModel, greyViewModel, extinguisherViewModel,
                context = context, pagerState = pagerState
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun LoadNewExtinguishers(viewModel: MyViewModel, context: Context) {
        val scope = rememberCoroutineScope()
        val state = rememberPullToRefreshState()
        var isRefreshing by remember {
            mutableStateOf(false)
        }
        PullToRefreshBox(state = state, isRefreshing = isRefreshing,
            onRefresh =
            {

                isRefreshing = true
                viewModel.setExtinguishersProgressActive(true)

                scope.launch {
                    viewModel.getExtinguishers(
                        context, viewModel.getEmptyExtinguishersList()
                    ) {
                        viewModel.setExtinguishersProgressActive(false)
                        isRefreshing = false
                    }
                }
            }) {
            InitExtinguishersPageUI(viewModel = viewModel)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun LoadArtikuls(viewModel: MyViewModel, context: Context) {
        val scope = rememberCoroutineScope()
        val state = rememberPullToRefreshState()
        var isRefreshing by remember {
            mutableStateOf(false)
        }
        PullToRefreshBox(
            state = state,
            isRefreshing = isRefreshing,
            onRefresh =
            {
                isRefreshing = true
                viewModel.setArtikulsProgressActive(true)

                scope.launch {
                    viewModel.getArtikuls(context, viewModel.getEmptyArtikulsList(),
                        object : MyCallback {
                            override fun finished(result: Boolean) {
                                viewModel.setArtikulsProgressActive(false)
                                isRefreshing = false;
                            }
                        })
                }

            },
            indicator =
            {

            }) {
            InitArtikulPageUI(viewModel = viewModel)
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun TabLayout(
        tabs: List<String>,
        pagerState: androidx.compose.foundation.pager.PagerState
    ) {

        val scope = rememberCoroutineScope()

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            divider =
            {
                Spacer(modifier = Modifier.padding(5.dp))
            }, edgePadding = 2.dp,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(2.dp)
                        .width(2.dp)
                        .background(Color.Magenta)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        {
            tabs.forEachIndexed { index, s ->

                Tab(selected = pagerState.currentPage == index, onClick =
                {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = { Text(text = s, fontSize = 20.sp, color = Color.Black) })

            }
        }

    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun TabContent(
        viewModel: MyViewModel, greyViewModel: MyViewModel,
        extinguisherViewModel: MyViewModel,
        context: Context, pagerState: androidx.compose.foundation.pager.PagerState
    ) {
        HorizontalPager(state = pagerState) { index ->
            when (index) {
                0 -> {
                    LoadArtikuls(viewModel = viewModel, context = context)
                }

                1 -> {
                    LoadNewExtinguishers(viewModel = extinguisherViewModel, context = context)
                }

                2 -> {
                    LoadArtikuls(viewModel = greyViewModel, context = context)
                }

            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InitExtinguishersPageUI(viewModel: MyViewModel) {


        var searchTextValue by remember {
            mutableStateOf("")
        }

        val context = LocalContext.current
        val menuActions = listOf(
            "", "insert_extinguisher", "edit_extinguisher_price", "edit_extinguisher_quantity",
            "delete_extinguisher"
        )

        ConstraintLayout(modifier = Modifier.fillMaxHeight()) {

            val (searchField, constraintLayout, circularProgress, iconLogo) = createRefs()

            if (viewModel.isExtinguishersProgressActive()) {
                CircularProgressIndicator(modifier =
                Modifier
                    .size(64.dp)
                    .constrainAs(circularProgress) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }, color = Color.Red, trackColor = Color.Yellow
                )
            }

            TextField(value = searchTextValue,
                onValueChange = {
                    searchTextValue = it
                },
                Modifier
                    .fillMaxWidth(1f)
                    .constrainAs(searchField) {
                        top.linkTo(parent.top)
                    },
                placeholder = { Text(text = "Търсене") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                    cursorColor = Color.Red
                ))




            Box(
                Modifier
                    .constrainAs(constraintLayout) {
                        top.linkTo(searchField.bottom)
                    }
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)) {

                LazyColumn() {
                    items(viewModel.getEmptyExtinguishersList()) {
                        var dropDownMenuExpanded by remember {
                            mutableStateOf(false)
                        }
                        Card(
                            Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .fillMaxWidth()
                                .pointerInput(Unit) {
                                    detectTapGestures(onLongPress = {
                                        dropDownMenuExpanded = true
                                    })
                                },
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = RoundedCornerShape(
                                CornerSize(2.dp)
                            ),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = it.type,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Red
                                )
                                Text(
                                    text = String.format(
                                        "цена %s количество %s броя",
                                        it.price,
                                        it.quantity
                                    ), Modifier.padding(10.dp, 7.dp), color = Color.Black
                                )
                                Text(
                                    text = it.invoiceByKontragent,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = it.kontragent,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = it.wheight,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = it.category,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = it.brand,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = it.dateString,
                                    Modifier.padding(10.dp, 7.dp),
                                    color = Color.Black
                                )


                                DropdownMenu(
                                    expanded = dropDownMenuExpanded, onDismissRequest = {
                                        dropDownMenuExpanded = false
                                    },
                                    Modifier
                                        .fillMaxWidth(0.7f)
                                        .background(Color.White)
                                        .padding(10.dp), offset = DpOffset(50.dp, (-25).dp)
                                ) {
                                    val menuOptions = listOf(
                                        it.type,
                                        "Нов Пожарогасител",
                                        "Редактирай цена",
                                        "Редактирай к-во",
                                        "Изтрий пожарогасител"
                                    )

                                    menuOptions.forEachIndexed(action = { index,
                                                                          extinguisherName ->
                                        DropdownMenuItem(text = {
                                            Text(
                                                text = extinguisherName,
                                                color = if (index == 0) Color.Red else Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }, modifier = Modifier.padding(5.dp), onClick = {
                                            if (index > 0) {
                                                val intent =
                                                    Intent(context, EditArtikulActivity::class.java)
                                                intent.putExtra("action", menuActions[index])
                                                intent.putExtra("artikul", it.type)
                                                intent.putExtra("invoice", it.invoiceByKontragent)
                                                intent.putExtra("kontragent", it.kontragent)
                                                intent.putExtra("med", "броя")
                                                intent.putExtra("price", it.price)
                                                intent.putExtra("weight", it.wheight)
                                                intent.putExtra("category", it.category)
                                                intent.putExtra("brand", it.brand)
                                                intent.putExtra(
                                                    "viewModelType",
                                                    viewModel.getType()
                                                )

                                                context.startActivity(intent)

                                                dropDownMenuExpanded = false
                                            }

                                        })
                                    })
                                }
                            }


                        }

                        //   Divider(thickness = 1.dp, color = Color.LightGray)

                    }
                }
            }

            Icon(painter = painterResource(id = R.drawable.fire_extinguisher),
                contentDescription = "ExtinguisherLogo",
                modifier = Modifier
                    .constrainAs(iconLogo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f),
                tint = Color(0x23FF0000))

        }


    }


    interface MyCallback {
        fun finished(result: Boolean)
    }


    @Composable
    fun InitArtikulPageUI(viewModel: MyViewModel) {


        ConstraintLayout(Modifier.fillMaxHeight()) {
            val (textField, circularProgress, layout, logoIcon) = createRefs()


            if (viewModel.isArtikulsProgressActive()) {
                CircularProgressIndicator(
                    Modifier
                        .size(64.dp)
                        .constrainAs(circularProgress) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                    color = Color.Red,
                    trackColor = Color.Yellow
                )
            }

            TextField(
                value = viewModel.searchArtikulText.value,
                onValueChange = {
                    viewModel.searchArtikulText.value = it
                },
                modifier = Modifier
                    //  .width(300.dp)
                    .fillMaxWidth(1f)
                    .constrainAs(textField) {
                        top.linkTo(parent.top)
                    },
                placeholder = { Text("Търсене") },
                singleLine = true, colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    cursorColor = Color.Red,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray
                )
            )

            Box(modifier = Modifier
                .padding(0.dp, 5.dp, 0.dp, 0.dp)
                .constrainAs(layout) {
                    top.linkTo(textField.bottom)
                }) {


                LazyColumn() {


                    if (viewModel.searchArtikulText.value.isEmpty()) {
                        items(viewModel.getEmptyArtikulsList()) { plant ->
                            CreateCard(artikulModel = plant, viewModel.getType())
                        }
                    } else {
                        val list = mutableStateListOf<ArtikulModel>()
                        for (model: ArtikulModel in viewModel.getEmptyArtikulsList()) {
                            if (model.artikul.lowercase()
                                    .contains(viewModel.searchArtikulText.value.lowercase())
                            ) {
                                list.add(model)
                            }
                        }
                        items(list) { plant ->
                            CreateCard(artikulModel = plant, viewModel.getType())
                        }
                    }
                }
            }

            Icon(painter = painterResource(id = R.drawable.fire),
                contentDescription = "App_Logo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
                    .constrainAs(logoIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                tint = Color(0x23FF0000)
            )

        }

    }


    @Composable
    fun CreateCard(artikulModel: ArtikulModel, viewType: Int) {
        val menuOptions = listOf(
            artikulModel.artikul,
            "Нов Артикул",
            "Редактирай цена",
            "Редактирай к-во",
            "Прейменувай артикул",
            "Изтрий артикул"
        )
        val menuActions = listOf(
            "",
            "insert_artikul",
            "edit_artikul_price",
            "edit_artikul_quantity",
            "rename_artikul",
            "delete_artikul"
        )

        val isDropDownExpanded = remember {
            mutableStateOf(false)
        }

        val itemPosition = remember {
            mutableStateOf(0)
        }

        val context = LocalContext.current

        Card(modifier = Modifier

            .padding(horizontal = 8.dp, vertical = 2.dp)
            .setRippleEffectOnClick {
                isDropDownExpanded.value = true
            }
            .fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(2.dp)),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )

        ) {

            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = artikulModel.artikul, modifier = Modifier
                        .padding(10.dp, 7.dp)
                        .clickable(
                            onClick = {

                                //    isDropDownExpanded.value = true
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(false)
                        ), color = Color.Red
                )
                Text(
                    text = (String.format(
                        Locale.getDefault(),
                        "цена %.2f количество %s %s",
                        artikulModel.price,
                        artikulModel.quantity,
                        artikulModel.med
                    )), modifier = Modifier.padding(10.dp, 7.dp), color = Color.Black
                )
                Text(
                    text = artikulModel.invoice,
                    modifier = Modifier.padding(10.dp, 7.dp),
                    color = Color.Black
                )
                Text(
                    text = artikulModel.kontragent,
                    modifier = Modifier.padding(10.dp, 7.dp),
                    color = Color.Black
                )
                Text(
                    text = artikulModel.date,
                    modifier = Modifier.padding(10.dp, 7.dp),
                    color = Color.Black
                )
            }

            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }, offset = DpOffset(50.dp, (-25).dp), modifier = Modifier
                    .background(Color.White)
                    .padding(10.dp)
                    .fillMaxWidth(0.7f)
            ) {
                menuOptions.forEachIndexed { index, username ->
                    DropdownMenuItem(text = {
                        Text(
                            text = username,
                            color = if (index == 0) Color.Red else Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                        onClick = {
                            if (index > 0) {
                                isDropDownExpanded.value = false
                                itemPosition.value = index

                                val intent = Intent(context, EditArtikulActivity::class.java)
                                intent.putExtra("action", menuActions.get(index = index))
                                intent.putExtra("artikul", artikulModel.artikul)
                                intent.putExtra("invoice", artikulModel.invoice)
                                intent.putExtra("kontragent", artikulModel.kontragent)
                                intent.putExtra("med", artikulModel.med)
                                intent.putExtra("price", artikulModel.price.toString())
                                intent.putExtra("viewModelType", viewType)
                                context.startActivity(intent)
                            }
                        })
                }
            }
        }


    }

    @Composable
    fun Modifier.setRippleEffectOnClick(onClick: () -> Unit): Modifier = composed {
        clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(color = Color.Black),
            onClick = onClick
        )
    }

    private fun getTabs(): List<String> {
        return listOf("Артикули", "Нови Пожарогасители", "Други")
    }
//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun GreetingPreview() {
//    PojarprotektMobileAppTheme {
//        // Greeting("Android")
//        Surface() {
//            initArtikulPageUI()
//        }
//
//    }
//}

