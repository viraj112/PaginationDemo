package com.neosoft.paginationdemo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricConstants.ERROR_NEGATIVE_BUTTON
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import java.util.concurrent.Executor


class BiometricActivity : AppCompatActivity() {
    private var biometricPrompt: BiometricPrompt? = null
    private val executor: Executor = MainThreadExecutor()
    val callback: BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {
            @SuppressLint("RestrictedApi")
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode == ERROR_NEGATIVE_BUTTON && biometricPrompt != null) biometricPrompt?.cancelAuthentication()
                toast(errString.toString())
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                toast("Authentication succeed")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                toast("Application did not recognize the placed finger print. Please try again!")
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.neosoft.paginationdemo.R.layout.activity_biometric)
        if (biometricPrompt == null) biometricPrompt = BiometricPrompt(this, executor, callback)

        findViewById<View>(com.neosoft.paginationdemo.R.id.selectFingerPrintButton).setOnClickListener { view: View? ->
            val promptInfo: PromptInfo = buildBiometricPrompt()!!
            biometricPrompt?.authenticate(promptInfo)
        }

    }
    private fun buildBiometricPrompt(): PromptInfo? {
        return PromptInfo.Builder()
            .setTitle("Login")
            .setSubtitle("Login into your account")
            .setDescription("Touch your finger on the finger print sensor to authorise your account.")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}