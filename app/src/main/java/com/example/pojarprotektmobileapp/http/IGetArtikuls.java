package com.example.pojarprotektmobileapp.http;



import com.example.pojarprotektmobileapp.models.ArtikulModel;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IGetArtikuls {

    @GET("/artikuls_data")
    Call<List<ArtikulModel>> getArtikuls(@Query("grey") boolean grey,
                                         @Query("order_by_date") boolean order_by_date,
                                         @Header("Authorization") String accessToken);

    @POST("/insert_artikul/{table}")
    Call<Integer> insertArtikul(@Path("table") String table, @Body ArtikulModel body,@Header("Authorization") String accessToken);

    @DELETE("/delete_artikul/{table}/{artikul}/{kontragent}/{invoiceByKontragent}")
    Call<Integer> deleteArtikul(@Path("table") String table, @Path("artikul") String artikul, @Path("kontragent") String kontragent, @Path("invoiceByKontragent") String invoiceByKontragent,
                                @Header("Authorization") String accessToken);

    @PUT("/rename_artikul/{table}/{oldName}/{newName}")
    Call<Integer> renameArtikul(@Path("table") String table,@Path("oldName") String oldName, @Path("newName") String newName,
                                @Header("Authorization") String accessToken);


    @PUT("/edit_artikul_quantity/{table}/{artikul}/{kontragent}/{invoiceByKontragent}/{newQuantity}")
    Call<Integer> editArtikulQuantity(@Path("table") String table,@Path("artikul") String artikul,
                                      @Path("kontragent") String kontragent,
                                      @Path("invoiceByKontragent") String invoiceByKontragent,
                                      @Path("newQuantity") String newQuantity,@Header("Authorization") String accessToken);

    @PUT("/edit_artikul_price/{table}/{newValue}/{percentProfit}/{artikul}/{kontragent}/{invoiceByKontragent}")
    Call<Integer> editArtikulPrice(@Path("table") String table, @Path("newValue") String newValue, @Path("percentProfit") String percentProfit, @Path("artikul") String artikul,
                                   @Path("kontragent") String kontragent, @Path("invoiceByKontragent") String invoiceByKontragent,
                                   @Header("Authorization") String accessToken);

    @PUT("/update_parts_price/{price}/{part}/{type}/{weight}/{category}")
    Call<Integer> updatePartPrice(@Path("price") String price, @Path("part") String part,
                                  @Path("type") String type, @Path("weight") String weight, @Path("category") String category,
                                  @Header("Authorization") String accessToken);

    @GET("/get_part_price")
    Call<Double> getPartPrice(@Query("part") String part, @Query("type") String type,
                              @Query("category") String category, @Query("weight") String weight,
                              @Header("Authorization") String accessToken);

    @GET("/artikul_value/{table}")
    Call<Double> getArtikulValue(@Path("table") String table, @Query("artikul") String artikul,
                                 @Header("Authorization") String accessToken);

    @GET("/extinguisher_value/{type}/{weight}/{category}/{brand}")
    Call<Double> getExtinguisherValue(@Path("type") String type, @Path("weight") String weight,
                                      @Path("category") String category, @Path("brand") String brand,
                                      @Header("Authorization") String accessToken);


}
