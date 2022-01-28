package eddercid.com.kotlinfirebaseapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import eddercid.com.kotlinfirebaseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            signInWithEmailAndPassword(email, password)
        }

        binding.buttonLoginWithGoogle.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            singInWithGoogle(email, password)
        }

        setContentView(binding.root)
    }

    private fun singInWithGoogle(email: String, password: String) {
        Log.i("Values", "Email: $email Password: $password")
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        Log.i("Values", "Email: $email Password: $password")
    }
}