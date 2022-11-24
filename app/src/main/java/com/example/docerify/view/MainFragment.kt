package com.example.docerify.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.docerify.R
import com.example.docerify.UserModel
import com.example.docerify.databinding.FragmentMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var gsc : GoogleSignInClient
    private lateinit var dbref : DatabaseReference

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

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        gsc = GoogleSignIn.getClient(requireActivity(), gso)
        gsc.signOut()

        binding.loginbtn.setOnClickListener{

            signInGoogle()


        }

    }

    private fun signInGoogle(){
        val signInIntent = gsc.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if(account != null)
                updateUI(account)
        }
        else
            Toast.makeText(requireActivity(), task.exception.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{ it ->
            if(it.isSuccessful){
                val uid = auth.currentUser?.uid
                val emailparts = account.email?.split("@")
                if(emailparts!![1]  == "uvg.edu.gt"){

                    if(uid != null){

                        val user = UserModel(uid, account.email.toString(), "none", account.displayName.toString(),
                            mutableListOf("Física", "vacio2"), true)
                        dbref = FirebaseDatabase.getInstance().getReference("users")
                        dbref.child(uid).setValue(user).addOnCompleteListener {
                            if(it.isSuccessful){
                                val action = MainFragmentDirections.toClasses()
                                binding.root.findNavController().navigate(action)

                            }else
                                Toast.makeText(requireActivity(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(requireActivity(), "CUENTA NO PERTENECIENTE A LA UVGw", Toast.LENGTH_SHORT).show()
                    gsc.signOut()
                }


            }else
                Toast.makeText(requireActivity(), it.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}















