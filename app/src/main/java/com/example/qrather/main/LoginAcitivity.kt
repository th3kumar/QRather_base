package com.example.qrather.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

class LoginAcitivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    var isRemebered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acitivity)

        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)

        isRemebered = sharedPreferences.getBoolean("CHECKBOX",false)

        if (isRemebered) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

       val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val et_login_email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val et_login_password = findViewById<EditText>(R.id.editTextTextPassword)
        val back_image  = findViewById<ImageView>(R.id.back_image)
        val login_main_button = findViewById<Button>(R.id.login_main_button)

        val to_signup_activity = findViewById<TextView>(R.id.to_signup_activity)


        back_image.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        login_main_button.setOnClickListener {

            val checked: Boolean = checkBox.isChecked
            val loginid: String = et_login_email.text.toString()
            val loginpassword: String = et_login_password.text.toString()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("LOGINID", loginid)
            editor.putString("LOGINPASSWORD", loginpassword)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(this,"Information Saved", Toast.LENGTH_LONG).show()

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()

            when{
                TextUtils.isEmpty(et_login_email.text.toString().trim {  it <= ' ' }) -> {
                    Toast.makeText(this,"Please enter email.",Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_login_password.text.toString().trim {  it <= ' ' }) -> {
                    Toast.makeText(this,"Please enter password.",Toast.LENGTH_SHORT).show()
                }



                else -> {
                    val email: String = et_login_email.text.toString().trim { it <= ' '}
                    val password: String = et_login_password.text.toString().trim { it <= ' '}


                    //create an instance and create a register a user with email and password
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                //if the registration is successful done
                                if (task.isSuccessful) {
                                    //Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(this, "you were logged in successfully.",Toast.LENGTH_SHORT).show()




                                    val intent =
                                        Intent(this,MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id",firebaseUser.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //If the logging is not successful then show error message.
                                    Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                }
                            })
                }

            }
        }


        //handle click open signup activity
        to_signup_activity.setOnClickListener {
            val intent = Intent(this,SignupAcitivity::class.java)
            startActivity(intent)
        }





    }

}