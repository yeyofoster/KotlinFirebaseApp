package eddercid.com.kotlinfirebaseapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import eddercid.com.kotlinfirebaseapp.databinding.ActivityForgotPasswordBinding
import eddercid.com.kotlinfirebaseapp.utils.Validator
import eddercid.com.kotlinfirebaseapp.utils.goToActivity
import eddercid.com.kotlinfirebaseapp.utils.hideKeyboard
import eddercid.com.kotlinfirebaseapp.utils.showToast

class ForgotPasswordActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)

        binding.buttonResetPassword.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            hideKeyboard()

            if (Validator.isValidEmail(email)) {
                resetPasswordWithEmail(email)
            } else {
                showToast("Invalid email")
            }
        }

        binding.buttonGoBack.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }

        setContentView(binding.root)
    }

    private fun resetPasswordWithEmail(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                showToast("Recovery password email sent")
            } else {
                showToast("Error sending recovery password email")
            }
        }
    }

}