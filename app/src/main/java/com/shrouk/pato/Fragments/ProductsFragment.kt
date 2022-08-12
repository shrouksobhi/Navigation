package com.shrouk.pato.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.pato.Interfaces.ProductsOnClick
import com.shrouk.pato.MVP.ProductsPresenter
import com.shrouk.pato.MVP.ProductsView
import com.shrouk.pato.PatoAdapter.PatoResAdapter
import com.shrouk.pato.PatoModel.Data
import com.shrouk.pato.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ProductsFragment : Fragment(), ProductsOnClick, ProductsView {
    // TODO: Rename and change types of parameters
    private lateinit var recyclerview: RecyclerView
    private lateinit var productadapter: PatoResAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var products: ArrayList<Data>
    private lateinit var productspresenter: ProductsPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        productspresenter = ProductsPresenter(this)
        productspresenter.getProducts()
    }

    private fun initView(view: View?) {
        recyclerview = view?.findViewById(R.id.recyclviewid)!!

    }


    private fun installViews() {
        layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerview.layoutManager = layoutManager
        productadapter = PatoResAdapter(products, requireContext(), this)
        recyclerview.adapter = productadapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)

    }


    override fun productClick(productId: Int) {
        Log.i("ProductId", "$productId")
        view?.findNavController()?.navigate(
            ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(productId)
        )

    }

    override fun setProducts(datalist: ArrayList<Data>) {
        products = datalist
        installViews()


    }

    override fun setError(errorMessage: String) {
        Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_LONG).show()
    }


}

