package com.example.latihanauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.latihanauth.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        insertAcc()
    }
    fun insertAcc(){
        binding.RegistBtn.setOnClickListener {
            var getEmail = binding.getEmail.text.toString()
            var getPassword = binding.pass.text.toString()
            var confirmPassword = binding.cPass.text.toString()

            if (getPassword == confirmPassword){
                createUserWithEmailAndPassword(email = getEmail, password = getPassword)
            }
        }
    }
    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Register Berhasil", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Register Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}