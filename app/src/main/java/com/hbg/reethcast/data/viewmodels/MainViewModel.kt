package com.hbg.reethcast.data.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hbg.reethcast.data.entities.Song
import com.hbg.reethcast.sealed.DataState

class MainViewModel : ViewModel() {

    val response:MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val tempList = mutableListOf<Song>()
        response.value = DataState.Loading
        FirebaseDatabase.getInstance().getReference("songs")
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val songItem = DataSnap.getValue(Song::class.java)
                        if (songItem != null) {
                            tempList.add(songItem)
                        }
                    }
                    response.value = DataState.Success(tempList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failures(error.message)
                }

            })
    }


}