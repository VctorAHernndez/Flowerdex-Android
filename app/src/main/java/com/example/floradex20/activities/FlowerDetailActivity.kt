package com.example.floradex20.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.floradex20.R
import com.squareup.picasso.Picasso

class FlowerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_detail)

        val plant = intent.getStringArrayExtra("plant_info")

        val nom_planta = findViewById(R.id.plant_name) as TextView
        val nom_sci = findViewById(R.id.sci_name) as TextView
        val year = findViewById(R.id.year) as TextView
        val author = findViewById(R.id.author) as TextView
        val imagen_planta = findViewById(R.id.plantview) as ImageView

        nom_planta.text = "Salu2"

        if (plant != null) {

            nom_planta.text = plant.get(0)
            nom_sci.text = plant.get(1)
            year.text = plant.get(2)
            author.text = plant.get(3)
            Picasso.get().load(plant.get(4)).into(imagen_planta)
        }

        else {
            Log.i("Desde ventana kt", "Llega null el valor. Te la mamaste")
        }

    }
}