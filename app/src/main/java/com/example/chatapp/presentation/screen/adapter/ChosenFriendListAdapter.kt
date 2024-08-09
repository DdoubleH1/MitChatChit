package com.example.chatapp.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chatapp.databinding.ChooseFriendItemBinding
import com.example.chatapp.domain.model.Friend

class ChosenFriendListAdapter : RecyclerView.Adapter<ChosenFriendListAdapter.ChosenFriendListAdapterViewHolder> () {

    private var chosenFriendList: ArrayList<Friend> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChosenFriendListAdapter.ChosenFriendListAdapterViewHolder {
        val chosenFriendItem = ChooseFriendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChosenFriendListAdapterViewHolder(chosenFriendItem)
    }

    override fun onBindViewHolder(holder: ChosenFriendListAdapter.ChosenFriendListAdapterViewHolder, position: Int) {
        val item = chosenFriendList[position]
        item.profileImageUrl?.let {
            Glide.with(holder.ivProfile.context)
                .load(it)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivProfile)
        }
    }

    override fun getItemCount(): Int {
        return chosenFriendList.size
    }

    fun updateData(newList: ArrayList<Friend>) {
        val diffResult = DiffUtil.calculateDiff(ChosenFriendListCallBack(chosenFriendList, newList))
        chosenFriendList.clear()
        chosenFriendList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class ChosenFriendListAdapterViewHolder(itemView: ChooseFriendItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val ivProfile = itemView.ivProfile
    }
}

class ChosenFriendListCallBack(private val oldList: ArrayList<Friend>, private val newList: ArrayList<Friend>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid == newList[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].uid != newList[newItemPosition].uid -> false
            oldList[oldItemPosition].userName != newList[newItemPosition].userName -> false
            oldList[oldItemPosition].profileImageUrl != newList[newItemPosition].profileImageUrl -> false
            else -> true

        }
    }
}