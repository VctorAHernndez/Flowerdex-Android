package com.example.floradex20.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.floradex20.ApiManager
import com.example.floradex20.R
import com.example.floradex20.models.User
//import com.example.login.ApiManager
//import com.example.login.UserInfo
import com.google.android.material.snackbar.Snackbar

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val b: Button
        val user_email: EditText
        val user_password: EditText
        val login: String
        val password: String
        val email: String
        user_email = findViewById(R.id.email)
        user_password = findViewById(R.id.password)
        email = user_email.getText().toString()
        password = user_password.getText().toString()


        val button: Button = findViewById(R.id.login_button)


        //Coger estas cosas y mandarlas a la base de datos
        button.setOnClickListener {
            //Somehow hacer que esto llegue a la base de datos

            val user_email: String = (findViewById(R.id.email) as TextView).getText().toString()
            val user_password: String = (findViewById(R.id.password) as TextView).getText().toString()
//            val user_password_confirm: String = (findViewById(R.id.et_repassword) as TextView).getText().toString()


            //Hasta aqui funciona
            val user_info = User(null, user_email, user_password, null, null)
            val apimanager = ApiManager(it)
            val thisActivity = it
//            user_info.user_email?.let { it1 -> Log.i("check instance info", it1) }
//            user_info.user_password?.let { it1 -> Log.i("check instance info", it1) }

            if(user_email == "" || user_password == ""){
                Snackbar.make(
                        it, "All Fields Required",
                        Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()
            }
            else {
                //
                apimanager.logInUser(user_info) {

                    Snackbar.make(
                            thisActivity, "Succesfully logged in",
                            Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show()

                    val user_id: String? = it?.id
//                    val user_name: String? = it?.username
                    val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("user_id", user_id)
//                    editor.putString("user_name", username)
                    editor.apply()


                    //Guardar Id y mover a discover page

                    val intent = Intent(this, DiscoverActivity::class.java)
                    startActivity(intent)
                }

            }
        }

    }

}