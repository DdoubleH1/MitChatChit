package com.example.chatapp.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chatapp.databinding.MessageRoomItemBinding
import com.example.chatapp.domain.model.ChatRoom

class MessageListAdapter (
    private val messageList: List<ChatRoom>
) : RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>()  {

    inner class MessageViewHolder(itemView: MessageRoomItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val profileImage = itemView.ivProfile
        val userName = itemView.tvUserName
        val lastMessage = itemView.tvLastMessage
        val sendDate = itemView.tvLastSendDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val messageItem = MessageRoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(messageItem)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = messageList[position]
        item.senderUser?.profileImageUrl?.let {
            Glide.with(holder.profileImage.context)
                .load(it)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.profileImage)
        }
        holder.userName.text = item.senderUser?.userName
        holder.lastMessage.text = item.lastMessage
        holder.sendDate.text = item.sendDate.toString()
    }

}