package com.example.docerify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.docerify.databinding.VistaTutoresBinding
import com.example.docerify.view.SearchFragmentDirections

class TutorAdapter(private val tutorList: List<String>): RecyclerView.Adapter<TutorAdapter.TutorHolder>() {

    inner class TutorHolder(val binding: VistaTutoresBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorHolder {
        val binding = VistaTutoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TutorHolder(binding)
    }

    override fun onBindViewHolder(holder: TutorHolder, position: Int) {
        val item = tutorList[position]
        holder.binding.name.text = item

        holder.binding.photo.setBackgroundResource(R.drawable.persona)

        val action = SearchFragmentDirections.toContact()
        holder.binding.root.setOnClickListener(){
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return tutorList.size
    }

}