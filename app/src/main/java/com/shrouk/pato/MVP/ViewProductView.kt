package com.shrouk.pato.MVP

import com.shrouk.pato.PatoModel.Data

interface ViewProductView {
    fun setViewProduct(viewdata: ArrayList<Data>)
    fun setViewError(errormessage: String)


}