package com.hbg.reethcast.data.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hbg.reethcast.data.entities.Song
import kotlinx.coroutines.tasks.await

class SongViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = Firebase.firestore

    var songs: List<Song> by mutableStateOf(emptyList())
        private set

    suspend fun loadSongs() {
        try {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val songsCollection = firestore.collection("songs")
                val querySnapshot = songsCollection.get().await()

                val songsList = querySnapshot.documents.mapNotNull {
                    it.toObject(Song::class.java)
                }
                songs = songsList
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
