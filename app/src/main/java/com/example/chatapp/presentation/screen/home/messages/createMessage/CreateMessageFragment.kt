package com.example.chatapp.presentation.screen.home.messages.createMessage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentCreateMessageBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.domain.model.Friend
import com.example.chatapp.domain.model.generateFriendList
import com.example.chatapp.presentation.navigation.AppNavigation
import com.example.chatapp.presentation.screen.adapter.AddFriendListAdapter
import com.example.chatapp.presentation.screen.adapter.ChosenFriendListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateMessageFragment : BaseFragment<FragmentCreateMessageBinding, CreateMessageViewModel>(R.layout.fragment_create_message) {

    @Inject
    lateinit var appNavigation: AppNavigation

    companion object {
        fun newInstance() = CreateMessageFragment()
    }

    private val chosenFriendList: ArrayList<Friend> = ArrayList()
    private lateinit var chosenFriendAdapter: ChosenFriendListAdapter


    private val viewModel: CreateMessageViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        chosenFriendAdapter = ChosenFriendListAdapter()

        binding.rvFriendList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(
                generateFriendList(),
                onChoseFriend = { friend: Friend ->
                    chosenFriendList.add(friend)
                    viewModel.setFriendList(chosenFriendList)
                },
                onRemoveFriend = { friend ->
                    chosenFriendList.remove(friend)
                    viewModel.setFriendList(chosenFriendList)
                }
            )
        }

        binding.rvChooseFriend.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = chosenFriendAdapter

        }

    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.chosenFriendList.observe(viewLifecycleOwner) { chosenFriendList ->
            when (chosenFriendList.isEmpty()) {
                true -> binding.llChosenFriend.visibility = View.GONE
                else -> binding.llChosenFriend.visibility = View.VISIBLE
            }
            chosenFriendAdapter.updateData(chosenFriendList)
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.apply {
            tvCancel.setOnClickListener {
                appNavigation.openCreateGroupScreenToHomeScreen()
            }
        }
    }
}