package eddercid.com.kotlinfirebaseapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import eddercid.com.kotlinfirebaseapp.databinding.ActivityLoginBinding
import eddercid.com.kotlinfirebaseapp.utils.Validator
import eddercid.com.kotlinfirebaseapp.utils.goToActivity
import eddercid.com.kotlinfirebaseapp.utils.hideKeyboard
import eddercid.com.kotlinfirebaseapp.utils.showToast

class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            hideKeyboard()

            if (isEmailAndPasswordValid(email, password)) {
                signInWithEmailAndPassword(email, password)
            } else {
                showToast("Invalid info", Toast.LENGTH_SHORT)
            }
        }

        binding.textViewForgotPassword.setOnClickListener {
            goToActivity<ForgotPasswordActivity>()
        }

        binding.buttonLoginWithGoogle.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            hideKeyboard()

            singInWithGoogle(email, password)
        }

        binding.buttonCreateAccount.setOnClickListener {
            goToActivity<SignUpActivity>()
        }

        setContentView(binding.root)
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        return Validator.isValidEmail(email) && password.isNotEmpty()
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                showToast("User found. Welcome ${task.result.user?.email}", Toast.LENGTH_SHORT)
            } else {
                showToast("User not found", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun singInWithGoogle(email: String, password: String) {
        Log.i("Values", "Email: $email Password: $password")
    }
}