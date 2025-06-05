package com.khiet.form

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            val firstName = findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.editTextLastName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val confirmPassword =
                findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()
            val genderRadioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)
            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            val termsChecked = findViewById<CheckBox>(R.id.checkBoxTerms).isChecked

            // Check if passwords match
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!termsChecked) {
                Toast.makeText(
                    this,
                    "You must agree to the Terms and Conditions",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val gender = findViewById<RadioButton>(selectedGenderId)?.text.toString()

            val intent = Intent(this, ThankYou_Screen::class.java).apply {
                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("email", email)
                putExtra("gender", gender)
                putExtra("password", password)
            }
            startActivity(intent)
        }
    }
}