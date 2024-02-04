package com.hbg.reethcast.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.hbg.reethcast.data.entities.User
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword (email: String, password: String, home: ()-> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        Log.d("ReetCast", "LogIn: Usuario logueado")
                        home()
                    }
                    else {
                        Log.d("ReethCast", "LogIn: ${task.result.toString()}")
                    }
                }
        }
        catch (ex:Exception){
            Log.d("ReethCast", "LogIn: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email:String,
        password: String,
        home: () -> Unit
    ){
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }
                    else {
                        Log.d("ReethCast", "SignIn: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
            }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = User (
            userId= userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Probando ReethCast",
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("ReethCast", "Created ${it.id}")
            }.addOnFailureListener{
                Log.d("ReethCast", "Error: ${it}")
            }
    }
}