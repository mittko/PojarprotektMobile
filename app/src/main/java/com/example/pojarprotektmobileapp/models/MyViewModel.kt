package com.example.pojarprotektmobileapp.models

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pojarprotektmobileapp.http.IEditExtinguishers
import com.example.pojarprotektmobileapp.http.IGetClients
import com.example.pojarprotektmobileapp.http.IEditArtikuls
import com.example.pojarprotektmobileapp.http.IGetArtikuls
import com.example.pojarprotektmobileapp.http.RetrofitInstance
import com.example.pojarprotektmobileapp.preferences.PreferencesManager
import com.example.pojarprotektmobileapp.screens.MainActivity
import com.example.pojarprotektmobileapp.screens.MyCallback
import com.example.pojarprotektmobileapp.utils.MyDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 open class MyViewModel : ViewModel() {

    open fun getTableName() : String { return "ArtikulsDB" }
    open fun isGrey() : Boolean { return false }
     open fun getType() : Int {
         return 1;
     }

    private val artikuls = mutableStateListOf<ArtikulModel>()
    private val extinguishers = mutableStateListOf<ExtinguisherModel>()
    private val clientsList = mutableStateListOf<Client>()
    var searchArtikulText = mutableStateOf("")
    private val isArtikulsProgressActive = mutableStateOf(true)
    private val isExtinguishersProgressActive = mutableStateOf(true)
    private val tokenIsValid = mutableStateOf(false)

    fun setTokenIsValid(isValid: Boolean) {
        tokenIsValid.value = isValid
    }
    fun isTokenValid() : Boolean {
        return tokenIsValid.value
    }

    fun setExtinguishersProgressActive(isProgressActive: Boolean) {
        this.isExtinguishersProgressActive.value = isProgressActive
    }
    fun isExtinguishersProgressActive() : Boolean {
        return this.isExtinguishersProgressActive.value
    }

    fun setArtikulsProgressActive(isProgressActive : Boolean) {
        this.isArtikulsProgressActive.value = isProgressActive;
    }
    fun isArtikulsProgressActive() : Boolean {
        return isArtikulsProgressActive.value
    }

    fun getClientList() : MutableList<Client> {
        return clientsList
    }
    fun getEmptyArtikulsList() : MutableList<ArtikulModel> {
        return artikuls
    }
    fun getEmptyExtinguishersList() : MutableList<ExtinguisherModel> {
        return extinguishers;
    }


    fun renameArtikul(context: Context, oldName : String, newName : String, res : (HttpResponse) -> Unit) {


        val accessToken = PreferencesManager().getToken(context)
        val valInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)

        valInterface.renameArtikul(getTableName(),oldName, newName, accessToken).enqueue(object :
            Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                res.invoke(HttpResponse(response.code(),response.message()))
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                res.invoke(HttpResponse(504,t.message!!))
            }

        })
    }
    fun deleteArtikul(context: Context,  artikul: String, kontragent: String, invoiceByKontragent: String
    , res: (HttpResponse) -> Unit) {
        val accessToken = PreferencesManager().getToken(context)
        val valInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)
        valInterface.deleteArtikul(getTableName() ,artikul,kontragent,invoiceByKontragent,
            accessToken)
            .enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    res.invoke(HttpResponse(response.code(),response.message()))
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    res.invoke(HttpResponse(500,t.message!!))
                }

            })
    }
    fun editArtikulPrice(context: Context, newValue : String, percentProfit: String, artikul: String,
                         kontragent: String, invoiceByKontragent: String, res : (HttpResponse) -> Unit) {
        val accessToken = PreferencesManager().getToken(context)
        val valInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)

        val table = getTableName()

        valInterface.editArtikulPrice(getTableName(),newValue,percentProfit, artikul,kontragent, invoiceByKontragent,
            accessToken
        ).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                res.invoke(HttpResponse(response.code(),response.message()))
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                res.invoke(HttpResponse(500,t.message!!))
            }

        })
    }
    fun editArtikulQuantity(context: Context, artikul : String,
                            kontragent: String, invoiceByKontragent: String, newQuantity: String,
                            res : (HttpResponse) -> Unit) {

        val accessToken = PreferencesManager().getToken(context)

        val valInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)

        valInterface.editArtikulQuantity(getTableName(), artikul,kontragent, invoiceByKontragent, newQuantity,
           accessToken
        ).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                res.invoke(HttpResponse(response.code(),response.message()))
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                res.invoke(HttpResponse(500,t.message!!))
            }

        })
    }
    fun insertArtikul(context : Context, model : ArtikulModel, res : (HttpResponse) -> Unit) {
        val accessToken = PreferencesManager().getToken(context)
        val valInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)

        valInterface.insertArtikul(getTableName() ,model, accessToken).enqueue(object :
            Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                res.invoke(HttpResponse(response.code(),response.message()))
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
               res.invoke(HttpResponse(500,t.message!!))
            }

        })
    }

    fun getArtikuls2(context: Context, result: MutableList<ArtikulModel>, lambda1: (Boolean) -> Unit) {
        result.clear()

        val accessToken = PreferencesManager().getToken(context)
        val apiInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)

        apiInterface.getArtikuls(isGrey(), false, accessToken)
            .enqueue(object : Callback<List<ArtikulModel>?> {
                override fun onResponse(
                    call: Call<List<ArtikulModel>?>,
                    response: Response<List<ArtikulModel>?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result.addAll(it) }

                    }
                    lambda1.invoke(true)
                }

                override fun onFailure(call: Call<List<ArtikulModel>?>, t: Throwable) {
                    Toast.makeText(context, "Can not connect to server", Toast.LENGTH_LONG).show()
                    lambda1.invoke(false)
                }


            })
    }
    suspend fun getArtikuls(context : Context, result : MutableList<ArtikulModel>, callback : MyCallback) {
        withContext(Dispatchers.Default) {
            result.clear()

            val apiInterface = RetrofitInstance.getInstance().create(IEditArtikuls::class.java)
            val accessToken = PreferencesManager().getToken(context)

            apiInterface.getArtikuls(isGrey() , false, accessToken)
                .enqueue(object : Callback<List<ArtikulModel>?> {
                    override fun onResponse(
                        call: Call<List<ArtikulModel>?>,
                        response: Response<List<ArtikulModel>?>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { result.addAll(it) }
                        }
                        callback.finished(true)
                    }

                    override fun onFailure(call: Call<List<ArtikulModel>?>, t: Throwable) {
                        Toast.makeText(context, "Can not connect to server", Toast.LENGTH_LONG).show()
                        callback.finished(true)
                    }
                })
        }
    }
    suspend fun getClients(context: Context, clients : MutableList<Client>) {
        withContext(Dispatchers.IO) {
            val accessToken = PreferencesManager().getToken(context)
            val apiInterface = RetrofitInstance.getInstance().create(IGetClients::class.java)
            apiInterface.getClients(accessToken).enqueue(object : Callback<List<Client>> {
                override fun onResponse(
                    call: Call<List<Client>>,
                    response: Response<List<Client>>
                ) {
                    if (response.isSuccessful) {
                        clients.clear()
                        response.body()?.let { clients.addAll(it) }
                    }
                }

                override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                    Toast.makeText(context, "Can not connect to server", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
    suspend fun getExtinguishers(context: Context, models : MutableList<ExtinguisherModel>, finished: (Boolean) -> Unit) {
         withContext(Dispatchers.Default) {
             val accessToken = PreferencesManager().getToken(context)
             val valInterface = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
             valInterface.getExtinguishers(accessToken).enqueue(object : retrofit2.Callback<List<ExtinguisherModel>> {
                 override fun onResponse(
                     call: Call<List<ExtinguisherModel>>,
                     response: Response<List<ExtinguisherModel>>
                 ) {
                     if(response.isSuccessful) {
                         models.clear()
                         response.body()?.let { models.addAll(it) }
                         finished.invoke(true)
                     } else {
                         Toast.makeText(context,response.code().toString(),Toast.LENGTH_LONG).show()
                         finished.invoke(true)
                     }
                 }

                 override fun onFailure(call: Call<List<ExtinguisherModel>>, t: Throwable) {
                     Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                     finished.invoke(true)
                 }

             })
         }

    }


     fun getExtinguishersPrice(context: Context, type:String, weight:String,
                               category:String, brand:String, res: (Double) -> Unit) {
         val token = PreferencesManager().getToken(context);
         val retrofitInstance = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
         retrofitInstance.getExtinguisherPrice("NewExtinguishersDB3",type,weight,category,brand,token)
             .enqueue(object : Callback<Double>{
                 override fun onResponse(call: Call<Double>, response: Response<Double>) {
                      res.invoke(response.body()!!)
                 }

                 override fun onFailure(call: Call<Double>, t: Throwable) {
                      res.invoke(Double.NaN)
                 }

             })
     }

     fun getArtikulValue(context: Context, table: String, type: String, res : (Double) -> Unit) {
         val token  = PreferencesManager().getToken(context)
         val retrofitInstance = RetrofitInstance.getInstance().create(IGetArtikuls::class.java)
         retrofitInstance.getArtikulValue(table,type,token).enqueue(object : Callback<Double> {
             override fun onResponse(call: Call<Double>, response: Response<Double>) {
                 res.invoke(response.body()!!)
             }

             override fun onFailure(call: Call<Double>, t: Throwable) {
                 res.invoke(Double.NaN)
             }

         })
     }

     fun createExtinguisher(context: Context, type: String, weight: String, category: String,
                            brand: String,quantity:String, price:String, invoice:String,
                            kontragent: String, date:String, res : (HttpResponse) -> Unit) {
         val model = ExtinguisherModel(type,weight,category,brand,quantity,price,invoice
         , kontragent,date)
         val token = PreferencesManager().getToken(context)
         val retrofitInstance = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
         retrofitInstance.createExtinguisher(model, token).enqueue(object : Callback<Int> {
             override fun onResponse(call: Call<Int>, response: Response<Int>) {
                 res.invoke(HttpResponse(response.code(),response.message()))
             }

             override fun onFailure(call: Call<Int>, t: Throwable) {
                 res.invoke(HttpResponse(500,t.message!!))
             }

         })
     }

     fun editExtinguisherPrice(context: Context, price: String, percentProfit: String,
                               type: String, weight: String, category: String, brand: String,
                               kontragent: String, invoice: String, res : (HttpResponse) -> Unit) {
         val retrofitInstance = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
         val token = PreferencesManager().getToken(context)
         retrofitInstance.editExtinguisherPrice(price,percentProfit,type,weight,
             category,brand,kontragent,invoice,token).enqueue(object : Callback<Int> {
             override fun onResponse(call: Call<Int>, response: Response<Int>) {
                 res.invoke(HttpResponse(response.code(),response.message()))
             }

             override fun onFailure(call: Call<Int>, t: Throwable) {
                 res.invoke(HttpResponse(500,t.message!!))
             }

         })
     }

     fun editExtinguisherQuantity(context: Context, quantity: String, kontragent: String, invoice: String,
                                  type: String, weight: String, category: String, brand: String
     , res : (HttpResponse) -> Unit) {
         val retrofitInstance = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
         val token = PreferencesManager().getToken(context)
         retrofitInstance.editExtinguisherQuantity(quantity,kontragent,invoice,
             type,weight,category,brand,token).enqueue(object : Callback<Int> {
             override fun onResponse(call: Call<Int>, response: Response<Int>) {
                 res.invoke(HttpResponse(response.code(),response.message()))
             }

             override fun onFailure(call: Call<Int>, t: Throwable) {
                 res.invoke(HttpResponse(500,t.message!!))
             }

         })
     }

     fun deleteExtinguisher(context: Context, type: String, weight: String, category: String,
                            brand: String, invoice: String, kontragent: String
     , res : (HttpResponse) -> Unit) {
         val retrofitInstance = RetrofitInstance.getInstance().create(IEditExtinguishers::class.java)
         val token = PreferencesManager().getToken(context)
         retrofitInstance.deleteExtinguisher(type,weight,category,brand,invoice,kontragent,token)
             .enqueue(object : Callback<Int> {
                 override fun onResponse(call: Call<Int>, response: Response<Int>) {
                     res.invoke(HttpResponse(response.code(),response.message()))
                 }

                 override fun onFailure(call: Call<Int>, t: Throwable) {
                     res.invoke(HttpResponse(500,t.message!!))
                 }

             })
     }

}