package com.shrouk.pato.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView
import com.shrouk.pato.MVP.ViewProductPresenter
import com.shrouk.pato.MVP.ViewProductView
import com.shrouk.pato.PatoModel.Data
import com.shrouk.pato.R


class DetailsFragment : Fragment(), ViewProductView {
    private lateinit var itemid: TextView
    private lateinit var imagedetails: CircularImageView
    private lateinit var namedetails: TextView
    private lateinit var pricedatails: TextView
    private lateinit var quantitydetails: TextView

    private lateinit var datalist: ArrayList<Data>
    private lateinit var args: DetailsFragmentArgs
    private lateinit var productid: String
    private lateinit var viewproductpresenter: ViewProductPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        viewproductpresenter = ViewProductPresenter(this)
        args = DetailsFragmentArgs.fromBundle(requireArguments())
        productid = args.productId.toString()
        viewproductpresenter.viwProduct(productid = productid.toInt())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)

    }


    private fun initView(view: View) {
        itemid = view.findViewById(R.id.itemid)
        imagedetails = view.findViewById(R.id.details_image)
        namedetails = view.findViewById(R.id.datails_name)
        pricedatails = view.findViewById(R.id.details_price)
        quantitydetails = view.findViewById(R.id.details_quantity)

    }

    override fun setViewProduct(viewdata: ArrayList<Data>) {
        datalist = viewdata
        // itemid.text = "Product Id : " + datalist[0].id
        Glide.with(View(requireContext())).load(datalist[0].image)
            .into(imagedetails)
        namedetails.text = datalist[0].name
        pricedatails.text = "Price :${datalist[0].price}$"
        quantitydetails.text = "Quantity: ${datalist[0].quantity}"
    }

    override fun setViewError(errormessage: String) {
        Toast.makeText(requireContext(), errormessage, Toast.LENGTH_SHORT).show()
    }

}
