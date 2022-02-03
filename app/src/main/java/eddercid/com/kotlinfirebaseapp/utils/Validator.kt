package eddercid.com.kotlinfirebaseapp.utils

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS

class Validator {
    companion object {

        fun isValidEmail(email: String): Boolean {
            return email.isNotBlank() && EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}