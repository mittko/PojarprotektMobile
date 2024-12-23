package com.example.pojarprotektmobileapp.http

import com.example.pojarprotektmobileapp.models.ArtikulModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface IEditArtikuls {
    @GET("/artikuls_data")
    fun getArtikuls(@Query("grey") grey : Boolean, @Query("order_by_date") order_by_date: Boolean,
    @Header("Authorization") accessToken : String) : Call<List<ArtikulModel>>


    @POST("/insert_artikul/{table}")
    fun insertArtikul(@Path("table") table : String, @Body body : ArtikulModel, @Header("Authorization") accessToken : String)
    : Call<Int>

    @PUT("/edit_artikul_quantity/{table}/{artiukl}/{kontragent}/{invoiceByKontragent}/{newQuantity}")
    fun editArtikulQuantity(@Path("table") table : String, @Path("artiukl") artikul : String, @Path("kontragent") kontragent : String,
    @Path("invoiceByKontragent") invoiceByKontragent : String, @Path("newQuantity") newQuantity : String,
    @Header("Authorization") accessToken: String) : Call<Int>

    @PUT("/edit_artikul_price/{table}/{newValue}/{percentProfit}/{artikul}/{kontragent}/{invoiceByKontragent}")
    fun editArtikulPrice(@Path("table") table : String, @Path("newValue") newValue : String, @Path("percentProfit") percentProfit : String,
    @Path("artikul") artikul : String, @Path("kontragent") kontragent: String, @Path("invoiceByKontragent") invoiceByKontragent: String,
    @Header("Authorization") accessToken: String) : Call<Int>

    @DELETE("/delete_artikul/{table}/{artikul}/{kontragent}/{invoiceByKontragent}")
    fun deleteArtikul(@Path("table") table : String, @Path("artikul") artikul : String, @Path("kontragent") kontragent: String,
    @Path("invoiceByKontragent") invoiceByKontragent: String, @Header("Authorization") accessToken: String) : Call<Int>

    @PUT("/rename_artikul/{table}/{oldName}/{newName}")
    fun renameArtikul(@Path("table") table: String, @Path("oldName") oldName : String, @Path("newName") newName : String,
    @Header("Authorization") accessToken: String) : Call<Int>


}
