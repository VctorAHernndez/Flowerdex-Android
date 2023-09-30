package com.example.floradex20.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.floradex20.ApiManager
import com.example.floradex20.R
import com.example.floradex20.adapters.FlowerAdapter
import com.example.floradex20.models.Flower

class DiscoverActivity : AppCompatActivity(), FlowerAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var plants: List<Flower>


    //Para desplegar el boton de filtro en Discover Page
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        //Donde se guarda el input del search bar y el search button
        val enterButton = findViewById<Button>(R.id.search_enter)
        val input = findViewById<EditText>(R.id.search)

        // Get user_id
        val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val userID = sharedPref.getString("user_id", "")!!
        Log.i("preferences", userID)

        // Get flowers with user_id
//        val thisActivity = this@Discover_activity
        val apimanager = ApiManager(findViewById(R.id.discover_activity))
        apimanager.getPlants(userID) {
            recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            recyclerView.apply {

                setHasFixedSize(true)
                plants = it.data
                adapter = FlowerAdapter(plants, this@DiscoverActivity)
                layoutManager = LinearLayoutManager(this@DiscoverActivity)

                //Aqui hay que chequiar si el API response llega bien
                recyclerView.setAdapter(adapter)
                recyclerView.setLayoutManager(layoutManager)
            }
        }

        //Esto se ejecuta cuando el usuario hace un search con el search bar
        enterButton?.setOnClickListener {

            val search_input = input.text.toString()

            apimanager.getPlantsByName(userID, search_input) {

//                progress_bar.visibility = View.GONE
                recyclerView.apply {
//                    Log.i("velllllllllllllllllllll", response.body().toString())
                    setHasFixedSize(true)
                    plants = it.data
                    adapter = FlowerAdapter(plants, this@DiscoverActivity)
                    layoutManager = LinearLayoutManager(this@DiscoverActivity)

                    recyclerView.setAdapter(adapter)
                    recyclerView.setLayoutManager(layoutManager)
                }
            }

        }
    }

    override fun onItemClick(position: Int){
        //Empezar el activity de Jose
        val plantInfo = arrayOf<String>(plants[position].common_name.toString(), plants[position].scientific_name.toString()
                , plants[position].year.toString(), plants[position].author.toString(), plants[position].image_url.toString())
        val intent = Intent(this, FlowerDetailActivity::class.java).apply {
            putExtra("plant_info", plantInfo)
        }
        startActivity(intent)
        Log.i("Position", plantInfo.get(0))
    }


    //Esto es lo que se ejecuta cuando se preciona el boton de filter en el UI.
    //Ejecuta Filtering.kt
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filterIcon -> {
            val intent = Intent(this, FilteringActivity::class.java)
            startActivityForResult(intent, 1)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)   //Error handling?
        }
    }

    //Esta es la funcion que se ejecuta cuando se recibe los filtros de
    //filtering.kt. Hace un call al api con los filtros.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val edible = data?.getStringExtra("edible").toString().toLowerCase()
        val vegetable = data?.getStringExtra("vegetable").toString().toLowerCase()
        val scientificName = data?.getStringExtra("cientifico").toString().toLowerCase()
        val color = data?.getStringExtra("color").toString().toLowerCase()

        // Get user_id
        val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val userID = sharedPref.getString("user_id", "")!!
        Log.i("preferences", userID)

        // Get flowers (with filters)
        val apiManager = ApiManager(findViewById(R.id.discover_activity))
        apiManager.getFiltered(userID, edible, vegetable, color, scientificName) {

            recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            recyclerView.apply {

                setHasFixedSize(true)
                plants = it.data
                adapter = FlowerAdapter(plants, this@DiscoverActivity)
                layoutManager = LinearLayoutManager(this@DiscoverActivity)

                //Aqui hay que chequiar si el API response llega bien
                recyclerView.setAdapter(adapter)
                recyclerView.setLayoutManager(layoutManager)
            }
        }

    }

}

