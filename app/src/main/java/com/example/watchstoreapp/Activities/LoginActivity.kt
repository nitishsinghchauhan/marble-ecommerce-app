package com.example.watchstoreapp.Activities

import android.content.Intent
import android.os.Bundle
//import android.support.v7.widget.AppCompatEditText
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.watchstoreapp.databinding.ActivityLoginBinding
import com.example.watchstoreapp.utils.SharedPreferenceManager
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rilixtech.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.loginform.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val storeViewModel: StoreViewModel by viewModels()
    lateinit var sharedPreferenceManager: SharedPreferenceManager
    lateinit var userId: String
    lateinit var mobileInput: String


    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferenceManager = SharedPreferenceManager(this)
        var ccp: CountryCodePicker = binding.loginForm.ccp
        var edtMobileNumber: EditText = binding.loginForm.etmobilelogin
        ccp.registerPhoneNumberTextView(edtMobileNumber)


        binding.loginForm.createnewac.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent) }



        auth = Firebase.auth
        // [END initialize_auth]


        etotplogin.visibility= View.INVISIBLE
        btnlogin.visibility= View.INVISIBLE
        etotplogin.isEnabled= false
        btnlogin.isEnabled= false

        btnOTPlogin.setOnClickListener {
            // Do some work here
             var phoneNumber= ccp.getFullNumberWithPlus().replace(" ", "")

            if (ccp.isValid) {

                checkUser(phoneNumber)


            } else {
                numbererror()
                Toast.makeText(
                this@LoginActivity,
                "Enter Valid Mobile No.!",
                Toast.LENGTH_SHORT
            ).show()
                }
        }





        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {

                    Toast.makeText(applicationContext, "Invalid Request", Toast.LENGTH_SHORT).show()
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(applicationContext, "SMS Quota Exceeded", Toast.LENGTH_SHORT).show()
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Toast.makeText(this@LoginActivity, "OTP sent!", Toast.LENGTH_LONG).show()
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                etotplogin.visibility= View.VISIBLE
                btnlogin.visibility= View.VISIBLE
                etotplogin.isEnabled= true
                btnlogin.isEnabled= true
                btnlogin.setOnClickListener {
                    var code: String = etotplogin.toString().replace(" ", "")
                    verifyPhoneNumberWithCode(verificationId,code)
                }


            }
        }
        // [END phone_auth_callbacks]





    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in
            updateUI(currentUser)
        } else {
            // No user is signed in
        }

    }
    // [END on_start_check_user]

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    updateUI(user)

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this@LoginActivity, "The verification code entered was invalid", Toast.LENGTH_LONG).show()
                    }
                    // Update UI
                }
            }
    }
    // [END sign_in_with_phone]

    private fun updateUI(user: FirebaseUser? = auth.currentUser) {

        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
        startActivity(intent)
        finish()

    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
    }

    fun numbererror(){
        binding.loginForm.etmobilelogin.error = "Please Enter Registered mobile"
        binding.loginForm.etmobilelogin.requestFocus()
        return
    }


    fun checkUser(mobile: String) {
        if (TextUtils.isEmpty(mobile)) {
            binding.loginForm.etmobilelogin.error = "Please Enter Registered mobile No."
            binding.loginForm.etmobilelogin.requestFocus()
            return
        }

        progressbar.visibility = View.VISIBLE
        storeViewModel.getUserDetails(mobile)
        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            //if(storeViewModel.user !=null) {
            storeViewModel.user.observe(this@LoginActivity, Observer { user ->
                if (user.mobile=="0") {
                    Log.i("Invalid User", "")
                    progressbar.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "You have not signed up!!!", Toast.LENGTH_LONG).show()
                    val intent = Intent(baseContext, SignUpActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Log.i("User Data in Login", user.toString())
                    if (user.mobile != ""){
                        sharedPreferenceManager.addUserData(user)
                        progressbar.visibility = View.GONE
                        startPhoneNumberVerification(mobile)
                    }


                }
            })


        }

    }




}






























