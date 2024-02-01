package com.hbg.reethcast.data.entities

data class User(
    val id: String?,
    val userId: String,
    val displayName: String,
    val avatarUrl : String,
    val quote: String,
){
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "quote" to this.quote,
            "avatar_url" to this.avatarUrl,
            )
    }
}

