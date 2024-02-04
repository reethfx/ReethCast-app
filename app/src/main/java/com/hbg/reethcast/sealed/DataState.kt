package com.hbg.reethcast.sealed

import com.hbg.reethcast.data.entities.Song

sealed class DataState {
    class Success(val data:MutableList<Song>):DataState()
    class Failures(val message:String):DataState()
    object Loading:DataState()
    object Empty:DataState()


}