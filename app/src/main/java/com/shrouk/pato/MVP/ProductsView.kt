package com.shrouk.pato.MVP

import com.shrouk.pato.PatoModel.Data

interface ProductsView {
    fun setProducts(datalist: ArrayList<Data>)
    fun setError(errormessage: String)
}