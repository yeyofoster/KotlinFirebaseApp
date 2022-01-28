package eddercid.com.kotlinfirebaseapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import eddercid.com.kotlinfirebaseapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()

            if (isEmailAndPasswordValid(email, password, confirmPassword)) {
                createAccount(email, password)
            } else {
                Toast.makeText(this, "Invalid info", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonGoBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User created", Toast.LENGTH_LONG).show()
                } else {
                    Log.e("Firebase Error", task.exception.toString())
                    Toast.makeText(this, "User can not be created", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun isEmailAndPasswordValid(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
    }
}