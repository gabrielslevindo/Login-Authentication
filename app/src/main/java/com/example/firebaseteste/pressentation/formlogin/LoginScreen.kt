package com.example.firebaseteste.pressentation.formlogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseteste.R
import com.example.firebaseteste.databinding.ActivityLoginScreenBinding
import com.example.firebaseteste.pressentation.formcadastro.registrationScreen
import com.example.firebaseteste.pressentation.formesqueciasenha.ForgotPassword
import com.example.firebaseteste.pressentation.mainscreen.Home
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvpassword.setOnClickListener {

            navegationForgetPassword()

        }

        binding.tvregistration.setOnClickListener {

            navegationRegistration()

        }
        binding.btnlogin.setOnClickListener { view ->

            val email = binding.edtemailLogin.text.toString()
            val password = binding.edtpasswordLogin.text.toString()

            if (email.isEmpty() || password.isEmpty()) {


                val snackbar =
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {


                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { autentic ->


                    if (autentic.isSuccessful) {

                        navagationHome()


                    }


                }.addOnFailureListener { exeption ->


                    val ErrorMensage = when (exeption) {


                        is FirebaseAuthInvalidCredentialsException -> "Senha invalida."
                        is FirebaseAuthInvalidUserException -> "E-mail invalido."
                        is FirebaseNetworkException -> "Sem conexão com a internet."
                        else -> "Server Error."

                    }

                    val snackbar =
                        Snackbar.make(view, ErrorMensage, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()


                }


            }


        }


    }

    private fun navagationHome() {

        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    private fun navegationRegistration() {


        val intent = Intent(this, registrationScreen::class.java)
        startActivity(intent)


    }

    private fun navegationForgetPassword() {


        val intent = Intent(this, ForgotPassword::class.java)
        startActivity(intent)


    }


    override fun onStart() {
        super.onStart()

        val userAtual = FirebaseAuth.getInstance().currentUser

        if (userAtual != null) {

            navagationHome()

        }

    }


}