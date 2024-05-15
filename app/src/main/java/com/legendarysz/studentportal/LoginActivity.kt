package com.legendarysz.studentportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.legendarysz.studentportal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister2.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        binding.btnLogin1.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.etEmail.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "Please Enter Email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etPassword.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "Please Enter Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = binding.etEmail.text.toString().trim() { it <= ' ' }
                    val password: String = binding.etPassword.text.toString().trim() { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this, "Logged In Successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this, "Invalid Email/Password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )

                }

            }
        }
    }
}