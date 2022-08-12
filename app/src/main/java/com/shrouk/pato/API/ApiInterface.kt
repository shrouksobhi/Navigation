package com.shrouk.pato.API

import com.shrouk.pato.PatoModel.Data
import com.shrouk.pato.PatoModel.DetailsModel
import com.shrouk.pato.PatoModel.LoginResponse
import com.shrouk.pato.PatoModel.Products
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("products")
    fun getData(): Call<Products>

    @GET("products")
    fun getId(
        @Query("id") id: Int?
    ): Call<DetailsModel>


    @FormUrlEncoded
    @POST("login")
    fun userLogin(

        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    @GET("products")
    fun getProductData(): Call<Data>
}