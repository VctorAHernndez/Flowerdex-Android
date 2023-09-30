package com.example.floradex20.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.floradex20.ApiManager
import com.example.floradex20.R
import com.example.floradex20.models.User
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        if(sharedPref.getString("user_id", "no se")!! != "no se"){
            val intent = Intent(this, DiscoverActivity::class.java)
            startActivity(intent)
            return
        }
        Log.i("preferences", sharedPref.getString("user_id", "no se").toString())
        //Boton para el usuario que ya se registro
        val log_in_button: Button = findViewById(R.id.to_login_activity)

        //Si el usuario ya se registro abre un intent para hacer login
        log_in_button.setOnClickListener {
//            Log.i("Debug", "Desde el clicl del register thing")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        //Boton para registrar usuario
        val register_button: Button = findViewById(R.id.btn_register)

        //cuando quickear el register button
        register_button.setOnClickListener {

            //Extrae los fields
            val full_name: String =  (findViewById(R.id.et_name) as TextView).getText().toString()
            val user_email: String = (findViewById(R.id.et_email) as TextView).getText().toString()
            val user_password: String = (findViewById(R.id.et_password) as TextView).getText().toString()
            val user_password_confirm: String = (findViewById(R.id.et_repassword) as TextView).getText().toString()

            //Verifica que no esten vacios
            if(full_name == "" || user_email == "" || user_password == "" || user_password_confirm == ""){
                Snackbar.make(
                        it, "All Fields Required",
                        Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()
            }

            //Verifica que passwords match
            else if(user_password != user_password_confirm){
                Snackbar.make(
                        it, "Passwords don't match",
                        Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()
            }

            //Manda el info a la base de datos
            else {

                val user_info = User(full_name, user_email, user_password, null, null)
                val apimanager = ApiManager(it)
                val thisActivity = it

                //Manda el info al
                apimanager.addUser(user_info) {

                    Snackbar.make(
                            thisActivity, "Succesfully registered",
                            Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show()

                    //Se guarda el username y id
                    val user_id: String? = it?.id
                    val user_name: String? = it?.username
                    val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("user_id", user_id)
                    editor.putString("user_name", user_name)
                    editor.apply()


                    //Guardar Id y mover a discover page

                    val intent = Intent(this, DiscoverActivity::class.java)
                    startActivity(intent)

                }

            }


        }

    }


}