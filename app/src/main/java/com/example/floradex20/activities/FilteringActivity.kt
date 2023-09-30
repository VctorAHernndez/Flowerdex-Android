package com.example.floradex20.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import com.example.floradex20.R

class FilteringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtering)

        title = "Filter"

        val sciTxt = findViewById<EditText>(R.id.editTextNombreSci)
        val colorTxt = findViewById<EditText>(R.id.editTextFlowerColor)
        val edibleToggle = findViewById<ToggleButton>(R.id.toggleButtonEdible)
        val vegetableToggle = findViewById<ToggleButton>(R.id.toggleButtonVegetable)
        val apply = findViewById<Button>(R.id.buttonApply)

        //Los filter values
        var edible: String = ""
        var vegetable: String = ""
        var nom_cientifico: String = ""
        var color_flor: String = ""

        edibleToggle.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked){
                edible = "true"
            } else {
                edible = "false"
            }
        }
        vegetableToggle.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked){
                vegetable = "true"
            } else {
                vegetable = "false"
            }
        }

        //Cuando usuario le da apply se envia los filter values a main activity
        apply.setOnClickListener {

            color_flor = colorTxt.text.toString()
            nom_cientifico = sciTxt.text.toString()
            //Log.d("variables", edible + vegetable + nom_cientifico + color_flor)

            val returnIntent = Intent()
            returnIntent.putExtra("edible", edible)
            returnIntent.putExtra("vegetable", vegetable)
            returnIntent.putExtra("cientifico", nom_cientifico)
            returnIntent.putExtra("color", color_flor)

            //Log.d("retintent", returnIntent.toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}