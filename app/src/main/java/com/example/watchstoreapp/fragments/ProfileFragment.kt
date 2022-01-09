package com.example.watchstoreapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchstoreapp.Activities.LoginActivity
import com.example.watchstoreapp.R
import com.example.watchstoreapp.databinding.FragmentDetailsBinding
import com.example.watchstoreapp.databinding.FragmentProfileBinding
import com.example.watchstoreapp.utils.SharedPreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferenceManager: SharedPreferenceManager
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferenceManager = SharedPreferenceManager(requireActivity())
        auth= Firebase.auth
        val userData:Array<String> = sharedPreferenceManager.getUserData()
        binding.buttonmaterial.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.name.visibility=View.GONE
        binding.mobile.visibility=View.GONE
        binding.buttonmaterial.visibility=View.GONE
        if (auth.currentUser==null){
            binding.buttonmaterial.visibility= View.VISIBLE
        } else{
            binding.apply {
            name.text = userData[0]
                name.visibility=View.VISIBLE
            mobile.text = userData[1]
                mobile.visibility=View.VISIBLE
             }
        }
    }

}