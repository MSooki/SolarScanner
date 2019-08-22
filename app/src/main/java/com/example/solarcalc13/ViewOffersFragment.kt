package com.example.solarcalc13


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_view_offers.*
import java.text.NumberFormat

class ViewOffersFragment : Fragment(), View.OnClickListener{

    var amount:String? = null
    var priceAsk:String? = null
    var roofType:String? = null
    var pottery:String? = null
    var direction:String? = null
    var digree:String? = null

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

    override fun onClick(v: View?) {
        val email:String = "sooki.mihaly@mszk.bme.hu"
        val subject:String = "Ajánlat kérés napelemekre"
        val text:String = "Tisztelt Hölgyem/Uram! Szeretnék ajánlatot kérni Önöktől. Az alábbi paraméterekre kaptam" +
                " egy $priceAsk forintnyi ajánlatot:" +
                " havonta sporolnék $amount Ft-nyi villanyt," +
                " a tetőm típusa $roofType," +
                " a cserép típusa $pottery," +
                " a ház tájolása x," +
                " a tető bezárt szöge x"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_EMAIL,arrayOf<String>(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            type = "text/plain"
        }

        button2.setOnClickListener {
            startActivity(sendIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        amount = arguments!!.getString("amount")

        roof = arguments!!.getBoolean("roof")

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
        priceAsk = priceSkySystem.toString()

        if(roof == true) {
            roofType = msg2
        }
        else {
            roofType = msg3
        }
        if(pala == true){
            pottery = msg4
        }
        if(cserep == true){
            pottery = msg5
        }
        if(fem == true){
            pottery = msg6
        }
        if(zsin == true){
            pottery = msg7
        }
    }

    /*
    private fun sendEmail(){
        startActivity(sendIntent)
    }
    */

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
