package com.example.docerify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.docerify.databinding.VistaClasesBinding
import com.example.docerify.view.ClassesFragmentDirections

class ClassAdapter(private val classList: List<String>): RecyclerView.Adapter<ClassAdapter.ClassHolder>() {

    inner class ClassHolder(val binding: VistaClasesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        val binding = VistaClasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        val item = classList[position]
        holder.binding.nombreClase.text = item

        val action = ClassesFragmentDirections.toSearch()
        holder.binding.root.setOnClickListener(){
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return classList.size
    }
}