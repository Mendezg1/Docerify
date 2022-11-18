package com.example.docerify.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.docerify.databinding.FragmentMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
    private lateinit var gsc : GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gsc = GoogleSignIn.getClient(requireActivity(), gso)

        binding.loginbtn.setOnClickListener{
            val action = MainFragmentDirections.toClasses()
            val user: TextView = binding.usuario
            val pass : TextView = binding.password

            signIn()

            val acc : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireActivity())

            if(acc != null){
                val email = acc.email
                if (email != null) {
                    val inicio = email.indexOf('@')
                    if(email.substring(inicio) == "@uvg.edu.gt"){
                        Log.i("Substring", email.substring(inicio))
                        this.findNavController().navigate(action)
                    }
                    else{
                        Toast.makeText(context, "Cuenta no válida", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    private fun signIn(){
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                task.result
            }
            catch (e: ApiException){
                Toast.makeText(context, "Inicio de Sesión fallido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}















