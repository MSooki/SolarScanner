package com.example.solarcalc13


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calculate.*

class CalculateFragment : Fragment(), View.OnClickListener{

    lateinit var navController: NavController
    lateinit var emailText:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.view_offers_btn).setOnClickListener(this)
    }

    fun getAmount():String {
        emailText = amount.text.toString()
        return emailText
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.view_offers_btn ->
            {
                if(!TextUtils.isEmpty(amount.text.toString())){
                    val bundle = bundleOf(
                        "amount" to amount.text.toString(),
                        "roof" to ferde_radio_btn.isChecked,
                        "cserep" to cserep_btn.isChecked,
                        "pala" to pala_btn.isChecked,
                        "beton" to beton_btn.isChecked,
                        "zsin" to zsin_btn.isChecked,
                        "fem" to fem_btn.isChecked
                    )
                    navController.navigate(
                        R.id.action_calculateAccurateFragment_to_viewOffersFragment,
                        bundle
                    )
                }
                else {
                    Toast.makeText(activity, "Adjon meg összeget", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
