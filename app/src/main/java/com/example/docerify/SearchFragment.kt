package com.example.docerify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.docerify.databinding.FragmentMainBinding
import com.example.docerify.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.relativeLayout1.setOnClickListener(){
            val action = SearchFragmentDirections.toContact()
            this.findNavController().navigate(action)
        }

        binding.relativeLayout4.setOnClickListener(){
            val action = SearchFragmentDirections.toContact()
            this.findNavController().navigate(action)
        }

        binding.relativeLayout3.setOnClickListener(){
            val action = SearchFragmentDirections.toContact()
            this.findNavController().navigate(action)
        }



        binding.back.setOnClickListener(){
            val action = SearchFragmentDirections.toMain()
            this.findNavController().navigate(action)
        }



    }

}