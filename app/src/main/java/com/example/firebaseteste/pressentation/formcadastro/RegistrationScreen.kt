package com.example.firebaseteste.pressentation.formcadastro

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.firebaseteste.R
import com.example.firebaseteste.databinding.ActivityRegistrationScreenBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class registrationScreen : AppCompatActivity() {


    private lateinit var binding: ActivityRegistrationScreenBinding
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregister.setOnClickListener { view ->


            val email = binding.edtemail.text.toString()
            val password = binding.edtpassword.text.toString()


            if (email.isEmpty() || password.isEmpty()) {

                val snackbar =
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()


            } else {


                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    // esse it é o context do add O Complete Listner
                    if (it.isSuccessful) {


                        val snackbar =
                            Snackbar.make(view, "Usuário Cadastrado", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                        binding.edtemail.setText("")
                        binding.edtpassword.setText("")

                    }

                }.addOnFailureListener { exception ->


                    val ErrorMensage = when (exception) {


                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres."
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email válido."
                        is FirebaseAuthUserCollisionException -> "E-mail já cadastrado."
                        is FirebaseNetworkException -> "Sem conexão com a internet."
                        else -> "Erro ao cadastrar usuário."


                    }

                    val snackbar =
                        Snackbar.make(view, ErrorMensage, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()


                }


            }


        }


    }


}