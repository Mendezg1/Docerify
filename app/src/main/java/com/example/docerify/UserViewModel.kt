package com.example.docerify

import android.renderscript.Sampler.Value
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class UserViewModel: ViewModel() {
    val tutores : MutableLiveData<MutableList<String>> by lazy { MutableLiveData<MutableList<String>>() }
    private lateinit var dbref : DatabaseReference

    fun getTutores(clase: String) {
        dbref = FirebaseDatabase.getInstance().reference
        val user_ref = dbref.child("users")
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp : MutableList<String> = mutableListOf()
                dbref.child("users").get().addOnSuccessListener{
                    for(user in it.children){
                        if(user.child("tutor").value.toString() == "true"){
                            for(item in user.child("tags").children){

                                if(item.value.toString() == clase){
                                    temp.add(user.child("username").value.toString())
                                    Log.i("ENTRA", user.child("username").value.toString())
                                }
                            }
                        }
                    }
                    tutores.value = temp
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        user_ref.addValueEventListener(listener)

    }
}