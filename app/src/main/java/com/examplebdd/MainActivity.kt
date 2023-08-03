package com.examplebdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginBtn: Button
    private lateinit var status: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        loginBtn = findViewById(R.id.loginBtn)
        status = findViewById(R.id.status)

        loginBtn.setOnClickListener {
            if(isValidCredential(emailText.text.toString(), passwordText.text.toString())){
                changeStatus("Success")
            }else{
                changeStatus("Failed")
            }
        }
    }

    private fun changeStatus(statusValue: String){
        status.text = statusValue
    }

    private fun isValidCredential(email: String, password: String): Boolean{
        return email == "exampleEmail123@gmail.com" && password == "1234567"
    }
}