package com.example.pojarprotektmobileapp.http

import com.example.pojarprotektmobileapp.models.ExtinguisherModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface IEditExtinguishers {

    @GET("/extinguisher_shop")
    fun getExtinguishers(@Header("Authorization") accessToken : String) : Call<List<ExtinguisherModel>>

    @POST("/create_new_extingusihser")
    fun createExtinguisher(@Body extinguisher : ExtinguisherModel, @Header("Authorization")
    accessToken: String) : Call<Int>

    @PUT("/update_extinguisher_quantity/{quantity}/{kontragent}/{invoiceByKontragent}/{type}/{weight}/{category}/{brand}")
    fun editExtinguisherQuantity(@Path("quantity") quantity: String, @Path("kontragent") kontragent : String,
                                 @Path("invoiceByKontragent") invoiceByKontragent: String, @Path("type") type: String,
    @Path("weight") weight : String, @Path("category") category : String, @Path("brand") brand: String,
    @Header("Authorization") accessToken: String) : Call<Int>

    @PUT("/update_extinguisher_price/{price}/{percentProfit}/{type}/{weight}/{category}/{brand}/{client}/{invoice}")
    fun editExtinguisherPrice(@Path("price") price: String, @Path("percentProfit") percentProfit:String,
                              @Path("type") type:String,
    @Path("weight") weight:String, @Path("category") category:String,@Path("brand") brand:String,
                              @Path("client") client:String,
    @Path("invoice") invoice:String, @Header("Authorization") accessToken:String) : Call<Int>

    @DELETE("/delete_extinguisher/{type}/{weight}/{category}/{brand}/{invoiceByKontragent}/{kontragent}")
    fun deleteExtinguisher(@Path("type") type:String,@Path("weight") weight: String, @Path("category") category: String,
    @Path("brand") brand: String, @Path("invoiceByKontragent") invoiceByKontragent: String,
                           @Path("kontragent") kontragent:String,
                           @Header("Authorization") accessToken:String) : Call<Int>
    @GET("/get_extinguisher_price/{table}")
    fun getExtinguisherPrice(@Path("table") table:String, @Query("type") type:String,
                                     @Query("weight") weight: String, @Query("category") category: String,
                                     @Query("brand") brand:String, @Header("Authorization") accessToken:String) : Call<Double>
}