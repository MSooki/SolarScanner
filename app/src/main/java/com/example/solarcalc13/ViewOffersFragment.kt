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

    private var amount:String? = null
    private var priceAsk:String? = null
    private var roofType:String? = null
    private var pottery:String? = null
    private var direction:String? = null
    private var angle:String? = null

    private var roof:Boolean? = null

    private var pala:Boolean? = null
    private var cserep:Boolean? = null
    private var fem:Boolean? = null
    private var zsin:Boolean? = null
    private var beton:Boolean? = null

    override fun onClick(v: View?) {
        val email:String = "sooki.mihaly@mszk.bme.hu"
        val subject:String = "Ajánlat kérés napelemekre"
        val text:String = "Tisztelt Hölgyem/Uram! Szeretnék ajánlatot kérni Önöktől. Az alábbi paraméterekre kaptam" +
                " egy $priceAsk-os ajánlatot:" +
                " havonta sporolnék $amount Ft elektromos áramot," +
                " a tetőm típusa $roofType," +
                " a cserép típusa $pottery," +
                " a ház tájolása x," +
                " a tető bezárt szöge $angle"

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
        beton = arguments!!.getBoolean("beton")

        angle = arguments!!.getString("angle")
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
        priceAsk = formatValue(priceSkySystem)

        if(roof == true) {
            roofType = "ferde"
        }
        else {
            roofType = "lapos"
        }
        if(pala == true){
            pottery = "pala"
        }
        if(cserep == true){
            pottery = "cserép"
        }
        if(fem == true){
            pottery = "fém"
        }
        if(zsin == true){
            pottery = "zsindely"
        }
        if(beton == true){
            pottery = "beton"
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
