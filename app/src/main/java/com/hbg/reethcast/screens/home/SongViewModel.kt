package com.hbg.reethcast.screens.home

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hbg.reethcast.data.entities.Song

class SongViewModel : ViewModel () {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val songsRef: DatabaseReference = database.getReference("songs")

    private val _songs = mutableListOf<Song>()
    val songs: List<Song>
        get() = _songs

    fun loadSongs() {
        songsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val songsFromFirebase = mutableListOf<Song>()
                for (songSnapshot in snapshot.children) {
                    val song = songSnapshot.getValue(Song::class.java)
                    if (song != null) {
                        songsFromFirebase.add(song)
                    }
                }
                _songs.clear()
                _songs.addAll(songsFromFirebase)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}