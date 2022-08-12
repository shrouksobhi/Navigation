package com.shrouk.pato.MVP

import com.shrouk.pato.API.RetrofitFactory
import com.shrouk.pato.PatoModel.Data
import com.shrouk.pato.PatoModel.DetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewProductPresenter(var viewproductview: ViewProductView) {
    fun viwProduct(productid: Int) {

        val retrofit = RetrofitFactory().apiInterface()
        retrofit.getId(productid).enqueue(object : Callback<DetailsModel> {
            override fun onResponse(call: Call<DetailsModel>, response: Response<DetailsModel>) {

                when (response.code()) {
                    200 -> {
                        viewproductview.setViewProduct(response.body()!!.data as ArrayList<Data>)
                        //  datalist = response.body()!!.data as ArrayList<Data>
                        //  Log.i("DetailsResponse", "${ response.body()!!.data as ArrayList<Data>}")

                    }

                    else -> {
                        viewproductview.setViewError(response.errorBody()?.string().toString())
                        // val error = response.errorBody()?.string()
                        //Toast.makeText(, "$error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<DetailsModel>, t: Throwable) {
                viewproductview.setViewError("${t.message}")
                // Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }
}