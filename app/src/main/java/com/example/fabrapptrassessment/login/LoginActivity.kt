package com.example.fabrapptrassessment.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fabrapptrassessment.BASE_URL
import com.example.fabrapptrassessment.MainActivity
import com.example.fabrapptrassessment.R
import com.example.fabrapptrassessment.chat.network.ApiService
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    //    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // set action bar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // display the back -> button
        supportActionBar?.setTitle(R.string.login_status) // update status title

        initView()
    }

    private fun initView() {
        val emailEditText = findViewById<EditText>(R.id.edt_email)
        val pwdEditText = findViewById<EditText>(R.id.edt_pwd)
        val userLoginBtn = findViewById<Button>(R.id.user_login_btn)

        userLoginBtn.setOnClickListener {
//            val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            val emailValue = emailEditText.text.toString()
            val pwdValue = pwdEditText.text.toString()
//            loginViewModel.user.email = emailValue
//            loginViewModel.user.password = pwdValue
            processInputUsers(emailValue, pwdValue)// calling the process method
//            loginViewModel.loginRes.observe(this, Observer {
//                Log.i("resInObserver", "response $it")
//            })
        }
    }

    private fun processInputUsers(email: String, password: String) {
        // Create Retrofit
        val apiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        apiService.sendUsersInfo(email, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("resFromEndPoint", "response ${response.body()}")
                var code = response.body()?.code
                var message = response.body()?.message
                val requestTime = response.raw().sentRequestAtMillis() // request time
                val responseTime = response.raw().receivedResponseAtMillis()// response time
                val apiTime = responseTime - requestTime//time taken to receive the response after the request was sent

                if(!response.isSuccessful) {
                    code = "Error"
                    message = "Incorrect Email or Password!"
                    displayAlertDialog(code, message, apiTime) // show the custom dialog with error
                }
                displayAlertDialog(code, message, apiTime) // show the custom dialog with successful response
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("failureResponse", "Failed to send data")
                t.printStackTrace()
            }
        })
    }
    //custom Dialog
    private fun displayAlertDialog(code: String?, message: String?, apiTime: Long) {
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)
        // wire up widgets
        val okButton = dialogView.findViewById<Button>(R.id.btn_ok) // wire Login button
        val codeApi = dialogView.findViewById<TextView>(R.id.code_from_api) // write the code text view
        val msgApi = dialogView.findViewById<TextView>(R.id.message_from_api) // write the message text view
        val timeApi = dialogView.findViewById<TextView>(R.id.time_from_api) // write the code text view
        codeApi.text = code
        msgApi.text = message
        timeApi.text = apiTime.toString()
        android.app.AlertDialog.Builder(this)// display the dialog when the login btn is pressed
            .setView(dialogView)
            .setCancelable(false) // make the dialog not cancellable when the back button is pressed
            .show()

//         wire the ok button to onclick listener
        okButton.setOnClickListener {
            startActivity(MainActivity.startIntent(this))
        }
    }
    // object
    companion object {
        fun startIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
    // navigation
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}


