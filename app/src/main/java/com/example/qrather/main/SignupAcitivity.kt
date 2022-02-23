package com.example.qrather.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.qrather.R
import com.example.qrather.login_ui.WelcomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignupAcitivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_acitivity)

        val back_image  = findViewById<ImageView>(R.id.back_image)
        val  signupMainButton = findViewById<Button>(R.id.signup_main_button)
        val to_login_activity = findViewById<TextView>(R.id.to_login_activity)
        val et_email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val et_password = findViewById<EditText>(R.id.editTextTextPassword)
        val  confirm_password  = findViewById<EditText>(R.id.editTextTextconfirmPassword)
        //handle click, begin signup

        to_login_activity.setOnClickListener {
            onBackPressed()
        }

        signupMainButton.setOnClickListener {
           when{
               TextUtils.isEmpty(et_email.text.toString().trim {  it <= ' ' }) -> {
                   Toast.makeText(this,"Please enter email.",Toast.LENGTH_SHORT).show()
               }

               TextUtils.isEmpty(et_password.text.toString().trim {  it <= ' ' }) -> {
                   Toast.makeText(this,"Please enter password.",Toast.LENGTH_SHORT).show()
               }

               TextUtils.isEmpty(confirm_password.text.toString().trim {  it <= ' ' }) -> {
                   Toast.makeText(this,"Make sure password and Confirm password are the same.",Toast.LENGTH_SHORT).show()
               }

               else -> {
                   val email: String = et_email.text.toString().trim { it <= ' '}
                   val password: String = et_password.text.toString().trim { it <= ' '}


                   //create an instance and create a register a user with email and password
                   FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(
                          OnCompleteListener<AuthResult> { task ->
                              //if the registration is successful done
                              if (task.isSuccessful) {
                                  //Firebase registered user
                                  val firebaseUser: FirebaseUser = task.result!!.user!!

                                  Toast.makeText(this, "you were registered successfully.",Toast.LENGTH_SHORT).show()




                                  val intent =
                                      Intent(this,MainActivity::class.java)
                                  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                  intent.putExtra("user_id",firebaseUser.uid)
                                  intent.putExtra("email_id",email)
                                  startActivity(intent)
                                  finish()
                              } else {
                                  //If the registering is not successful then show error message.
                                  Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                              }
                          })
               }

           }
        }





        back_image.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }




}