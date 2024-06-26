package com.legendarysz.studentportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.legendarysz.studentportal.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin2.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister1.setOnClickListener {
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

                TextUtils.isEmpty(binding.etFName.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "Please Enter Name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etLName.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "Please Enter City.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etSeat.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "Please Enter Internship.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = binding.etEmail.text.toString().trim() { it <= ' ' }
                    val password: String = binding.etPassword.text.toString().trim() { it <= ' ' }
                    val fname: String = binding.etFName.text.toString()
                    val lname: String = binding.etLName.text.toString()
                    val seat: String = binding.etSeat.text.toString()

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this, "Registered Successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("email_id", email)
                                    intent.putExtra("fname", fname)
                                    intent.putExtra("lname", lname)
                                    intent.putExtra("seat", seat)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this, task.exception!!.message.toString(),
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