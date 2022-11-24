package com.example.docerify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.docerify.R
import com.example.docerify.TutorAdapter
import com.example.docerify.UserViewModel
import com.example.docerify.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val args by navArgs<SearchFragmentArgs>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

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


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getTutores(args.clase)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        userViewModel.tutores.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = TutorAdapter(it)
        }


    }

}