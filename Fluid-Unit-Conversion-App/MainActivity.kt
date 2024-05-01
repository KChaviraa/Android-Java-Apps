package com.example.fluidunitconversion
/*
app name: Fluid Unit Conversion App
minSdk 21, targetSdk and compileSDK 34
author Kate Chavira
version 02/12/2024
*/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val UnitConv: TextInputEditText = findViewById(R.id.UnitConv)
        val conversionOption: RadioGroup = findViewById(R.id.conversionOption)
        val ShowUnitSwitch: SwitchMaterial = findViewById<SwitchMaterial>(R.id.ShowUnitsSwitch)
        val calculate: Button = findViewById(R.id.calculateButton)
        val Reset: Button = findViewById(R.id.ResetButton)
        calculate.setOnClickListener() {
            if (!TextUtils.isEmpty(UnitConv.text)) {
                val inputText = UnitConv.text.toString()
                val numericValue = inputText.substringBefore(" ").toDoubleOrNull()

                if (numericValue != null) {
                    var UnitAmount: Double = 0.0
                    var unit: String = ""

                    if (conversionOption.checkedRadioButtonId == R.id.MilltoOunce) {
                        UnitAmount = numericValue * 0.033814 // Convert milliliters to ounces
                        unit = "oz"
                    } else if (conversionOption.checkedRadioButtonId == R.id.OuncetoMill) {
                        UnitAmount = numericValue / 0.033814 // Convert ounces to milliliters
                        unit = "ml"
                    }
                    val convResult: TextView = findViewById(R.id.conversionResult)
                    if (ShowUnitSwitch.isChecked) {
                        val resultString = "%.4f $unit".format(UnitAmount)
                        convResult.text = resultString
                    } else {
                        // Display only the converted value
                        convResult.text = String.format("%.4f", UnitAmount)
                    }
                } else {
                    Toast.makeText(this, "Invalid input format. Please enter a numeric value followed by a unit.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "You did not enter a unit to convert", Toast.LENGTH_LONG).show()
            }
        }


        Reset.setOnClickListener {
            val convResult: TextView = findViewById(R.id.conversionResult)
            UnitConv.text = null
            convResult.text = "0.00"
        }
    }
}