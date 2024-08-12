package com.example.chatapp.domain.model

import java.time.LocalDateTime

data class ChatRoom(
    val senderUser: Friend,
    val read: Int? = null,
    val lastMessage: String? = null,
    val messageType: Int? = null,
    val sendDate: LocalDateTime? = null,
)
