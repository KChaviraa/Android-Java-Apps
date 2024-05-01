
package com.example.priceapp
/*
   In this app, the user will enter the price of an item and a discount percentage allowed
   on the price. The app will then compute the discounted price,
   sales tax and the total price including sales tax.
   App assumes that the sales tax = 7.5%
   Note:
   (1) how the user entered value is extracted and converted from an EditText field
   (2) how a TextView field is set to a value
   (3) how to round a quantity to two decimal precision (what I show is only one way, there are other ways)
   (4) how to format a decimal number for output using comma separation and two decimal precision
   (5) how an setText method is used is set to a formatted value
   (6) how to test if an EditText field content is empty or not using the TextUtils class
   App name: Price App
   @author: Kate Chavira
   minSDK version = 21
   targetSDK version = 34
   @version: 01/30/2024

 */
 
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val price: EditText = findViewById(R.id.price)
        val quantity: EditText = findViewById(R.id.quantity)
        val Tax: TextView = findViewById(R.id.Tax)
        val itemPrice: TextView = findViewById(R.id.itemPrice)
        val totalPrice: TextView = findViewById(R.id.totalPrice)
        val compute: Button = findViewById(R.id.compute)
        val reset: Button = findViewById(R.id.reset)
        compute.setOnClickListener {
            var tst: Toast
            if(!TextUtils.isEmpty(price.text.toString())){
                val pr= price.text.toString().toDouble()
                if (!TextUtils.isEmpty(quantity.text.toString())) {
                    val q = quantity.text.toString().toDouble()
                    var qt = q
                    quantity.setText(String.format("%,.2f", q))
                    var ip = pr * q
                    itemPrice.text = String.format("%,.2f", ip)
                    var itemTax = ip * 7.5 / 100
                    Tax.text = String.format("%,.2f", itemTax)
                    val totPrice = ip + itemTax
                    totalPrice.text = String.format("$%,.2f", totPrice)
                }
                else{
                    tst = Toast.makeText(this,
                        "You did not enter discount amount",
                        Toast.LENGTH_LONG)
                        tst.show()
                }
            }
            else{
                tst = Toast.makeText(this,
                "You did not enter price amount",
                Toast.LENGTH_LONG)
                tst.show()

            }
        }
        reset.setOnClickListener {
            price.text.clear()
            quantity.text.clear()
            itemPrice.text = ""
            Tax.text = ""
            totalPrice.text = ""
        }
    }
}