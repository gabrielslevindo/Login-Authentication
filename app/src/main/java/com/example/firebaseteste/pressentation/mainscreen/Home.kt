package com.example.firebaseteste.pressentation.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseteste.R
import com.example.firebaseteste.databinding.ActivityHomeBinding
import com.example.firebaseteste.databinding.ActivityLoginScreenBinding
import com.example.firebaseteste.pressentation.formcadastro.registrationScreen
import com.example.firebaseteste.pressentation.formlogin.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvback.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            navegationLogin()
        }


        binding.btnGravarDB.setOnClickListener {


            val UsersMap = hashMapOf(

               "name" to "Gabriel",
                "surname" to "Levindo",
                "Years" to 22


            )

            db.collection("Users").document("Gabriel").set(UsersMap).addOnCompleteListener {void ->

                Log.d("db", "sucesso ao salvar os dados")


            }





        }

        binding.btnLerdb.setOnClickListener {

            db.collection("Users").document("Gabriel").addSnapshotListener { document, error ->

                if (document != null){

                    binding.txtLeitura.text = document.getString("name")

                }




            }







        }



    }

    private fun navegationLogin() {


        val intent = Intent(this, LoginScreen::class.java)
        startActivity(intent)
        finish()

    }


}