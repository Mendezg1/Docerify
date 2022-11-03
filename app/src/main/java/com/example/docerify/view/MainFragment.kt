package com.example.docerify.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.docerify.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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

        binding.loginbtn.setOnClickListener{
            val action = MainFragmentDirections.toClasses()
            val user: TextView = binding.usuario
            val pass : TextView = binding.password

            if(user.text.toString() == "admin" && pass.text.toString() == "admin"){
                this.findNavController().navigate(action)
            }
            else
                Toast.makeText(activity,"INICIO DE SESIÓN FALLIDO",Toast.LENGTH_SHORT)
        }

    }
}