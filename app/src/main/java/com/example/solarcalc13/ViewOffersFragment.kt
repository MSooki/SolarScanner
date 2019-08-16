package com.example.solarcalc13


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.text.NumberFormat

class ViewOffersFragment : Fragment(), View.OnClickListener {

    var amount:String? = null

    var roof:Boolean? = null
    val msg2 = "ferde"
    val msg3 = "lapos"

    var pala:Boolean? = null
    val msg4 = "pala"

    var cserep:Boolean? = null
    val msg5 = "égetett cserép"

    var fem:Boolean? = null
    val msg6 = "fém"

    var zsin:Boolean? = null
    val msg7 = "zsindely"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roof = arguments!!.getBoolean("roof")
        amount = arguments!!.getString("amount")
        pala = arguments!!.getBoolean("pala")
        cserep = arguments!!.getBoolean("cserep")
        fem = arguments!!.getBoolean("fem")
        zsin = arguments!!.getBoolean("zsin")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button2).setOnClickListener(this)

        val message = "$amount"
        val price = message.toDouble()

        val priceSkySystem = ((((price/38)*12)/1000)*500000)

        view.findViewById<Button>(R.id.button2).text = formatValue(priceSkySystem)

        if(roof == true) {
            view.findViewById<Button>(R.id.button3).text = msg2
        }
        else {
            view.findViewById<Button>(R.id.button3).text = msg3
        }
        if(pala == true){
            view.findViewById<Button>(R.id.button4).text = msg4
        }
        if(cserep == true){
            view.findViewById<Button>(R.id.button4).text = msg5
        }
        if(fem == true){
            view.findViewById<Button>(R.id.button4).text = msg6
        }
        if(zsin == true){
            view.findViewById<Button>(R.id.button4).text = msg7
        }

    }

    private val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Tisztelt Hölgyem/Uram, szeretnék ajánlatot kérni Önöktől forintot sporolnék havontaa")
        putExtra(Intent.EXTRA_EMAIL, "sooki.mihaly@mszk.bme.hu")
        putExtra(Intent.EXTRA_SUBJECT, "Ajánlat kérés SolarScanner applikáción keresztül napelemekre")
        putExtra(Intent.EXTRA_USER, "user")
        putExtra(Intent.EXTRA_CC, "cc")
        type = "text/plain"
    }

    private fun sendEmail(){
        startActivity(sendIntent)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button2 -> sendEmail()
        }
    }

    /**
     * Adding 1234321 as double, this method will give back "1.234.321 HUF" in string
     */
    fun formatValue (value:Double) : String {
        val number = value
        val number3digits:Double = Math.round(number * 1000.0) / 1000.0
        val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
        val solution:Double = Math.round(number2digits * 10.0) / 10.0
        val solutionString:Int = solution.toInt()
        val pricefinal = NumberFormat.getInstance().format(solutionString) + " Ft";
        return pricefinal
    }
}
