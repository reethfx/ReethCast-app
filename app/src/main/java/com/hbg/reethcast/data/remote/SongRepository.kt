package com.hbg.reethcast.data.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hbg.reethcast.data.entities.Song
import kotlinx.coroutines.tasks.await

class SongRepository {
    private val firestore = Firebase.firestore
    private val songsCollection = firestore.collection("songs")

    suspend fun getSongsFromFirestore(): List <Song> {
        return try {
            songsCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}