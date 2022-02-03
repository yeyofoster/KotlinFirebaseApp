package eddercid.com.kotlinfirebaseapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import eddercid.com.kotlinfirebaseapp.databinding.ActivitySignUpBinding
import eddercid.com.kotlinfirebaseapp.utils.Validator
import eddercid.com.kotlinfirebaseapp.utils.goToActivity
import eddercid.com.kotlinfirebaseapp.utils.hideKeyboard
import eddercid.com.kotlinfirebaseapp.utils.showToast

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
            hideKeyboard()

            if (isEmailAndPasswordValid(email, password, confirmPassword)) {
                createAccount(email, password)
            } else {
                showToast("Invalid info")
            }
        }

        binding.buttonGoBack.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }

        setContentView(binding.root)
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("User created! You can sign in now")
                    goToActivity<LoginActivity> {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                } else {
                    val exception = task.exception as FirebaseException
                    showToast(exception.message.toString())
                }
            }
    }

    private fun isEmailAndPasswordValid(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return Validator.isValidEmail(email) && password.isNotEmpty() && password == confirmPassword
    }
}