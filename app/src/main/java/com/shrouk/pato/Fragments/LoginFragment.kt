package com.shrouk.pato.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.shrouk.pato.API.RetrofitFactory
import com.shrouk.pato.PatoModel.LoginResponse
import com.shrouk.pato.R
import com.shrouk.pato.Storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Response


class LoginFragment : Fragment() {

    private lateinit var btnsignin: Button
    private lateinit var memail: EditText
    private lateinit var mpassword: EditText
    private lateinit var btnsignup: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        btnsignin.setOnClickListener {

            val email = memail.text.toString().trim()
            val password = mpassword.text.toString().trim()

            if (email.isEmpty()) {
                memail.error = "Email required"
                memail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                mpassword.error = "Password required"
                mpassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitFactory().apiInterface().userLogin(email, password)
                .enqueue(object : retrofit2.Callback<LoginResponse> {

                    override fun onResponse(
                        call: Call<LoginResponse>, response: Response<LoginResponse>
                    ) {

                        if (response.body()?.status == true) {

                            response.body()!!.errors?.let { it1 ->
                                SharedPrefManager.getInstance(requireContext())
                                    .saveError(it1)
                                btnsignin.setOnClickListener(
                                    Navigation.createNavigateOnClickListener(
                                        R.id.action_loginFragment_to_productsFragment
                                    )
                                )
                            }
                        }
                        if (response.code() == 200) {
                            // "${response.body()?.message}".toast(this@LoginFragment)

                            view.findNavController().navigate(
                                LoginFragmentDirections.actionLoginFragmentToProductsFragment()
                            )
                        } else {
                            "${response.body()?.errors}".toast(requireContext())
                            Log.d("Debuuuuuuuug", "${response.body()} ")
                        }
                    }


                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        "${t.message}".toast(requireContext())
                    }

                })

        }






        btnsignup.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_signUpFragment)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun initViews(view: View?) {
        memail = view?.findViewById(R.id.email)!!
        mpassword = view.findViewById(R.id.password)!!
        btnsignin = view.findViewById(R.id.signinbutton)!!
        btnsignup = view.findViewById(R.id.signupbt)

    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(requireContext()).isLoggedIn) {
            view?.findNavController()?.navigate(
                LoginFragmentDirections.actionLoginFragmentToProductsFragment()
            )

        }

    }

}

//Toast extension function..
fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    return Toast.makeText(context, this.toString(), duration).show()
}

