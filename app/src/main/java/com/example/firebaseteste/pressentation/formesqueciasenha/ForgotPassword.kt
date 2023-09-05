package com.example.firebaseteste.pressentation.formesqueciasenha

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseteste.R
import com.example.firebaseteste.databinding.ActivityForgotPasswordBinding
import com.example.firebaseteste.databinding.ActivityLoginScreenBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val Auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnreset.setOnClickListener {


        val emailPassword  =  binding.edtemailLogin.text.toString()

           Auth.sendPasswordResetEmail(emailPassword)
               .addOnSuccessListener {void->


                   val snackbar =
                       Snackbar.make(it, "Verifique seu E-mail", Snackbar.LENGTH_SHORT)
                   snackbar.setBackgroundTint(Color.BLUE)
                   snackbar.show()



               }.addOnFailureListener{exception ->

                   val snackbar =
                       Snackbar.make(it, "ServerError", Snackbar.LENGTH_SHORT)
                   snackbar.setBackgroundTint(Color.RED)
                   snackbar.show()




               }





        }







    }
}