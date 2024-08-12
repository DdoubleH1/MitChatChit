package com.example.chatapp.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chatapp.databinding.AddFriendItemBinding
import com.example.chatapp.domain.model.Friend
import com.example.chatapp.domain.model.FriendState

class AddFriendListAdapter(
    private val friendList: List<Friend>,
    private val onChoseFriend: ((friend: Friend) -> Unit)? = null,
    private val onRemoveFriend: ((friend: Friend) -> Unit)? = null
) : RecyclerView.Adapter<AddFriendListAdapter.AddFriendViewHolder>() {
    inner class AddFriendViewHolder(itemView: AddFriendItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        init {
            itemView.cbAddFriend.setOnCheckedChangeListener { _, isCheck ->
                if (!isCheck) {
                    onRemoveFriend?.invoke(friendList[adapterPosition])
                } else {
                    onChoseFriend?.invoke(friendList[adapterPosition])
                }
            }
        }

        val profileImage = itemView.ivProfile
        val userName = itemView.tvUserName
        val addFriendButton = itemView.cbAddFriend
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendListAdapter.AddFriendViewHolder {
        val addFriendItem = AddFriendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddFriendViewHolder(addFriendItem)
    }

    override fun onBindViewHolder(holder: AddFriendListAdapter.AddFriendViewHolder, position: Int) {
        val item = friendList[position]
        item.profileImageUrl?.let {
            Glide.with(holder.profileImage.context)
                .load(it)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.profileImage)
        }
        holder.userName.text = item.userName
    }

    override fun getItemCount(): Int {
        return friendList.size
    }
}