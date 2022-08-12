package com.shrouk.pato.MVP

import com.shrouk.pato.API.RetrofitFactory
import com.shrouk.pato.PatoModel.Data
import com.shrouk.pato.PatoModel.Products
import retrofit2.Call
import retrofit2.Response

class ProductsPresenter(var productsview: ProductsView) {
    fun getProducts() {
        RetrofitFactory().apiInterface().getData()
            .enqueue(object : retrofit2.Callback<Products> {
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    if (response.code() == 200) {
                        productsview.setProducts(response.body()?.data!! as ArrayList<Data>)
                    } else {
                        productsview.setError(response.errorBody()!!.toString())
                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    productsview.setError(t.message.toString())
                }

            })
    }
}