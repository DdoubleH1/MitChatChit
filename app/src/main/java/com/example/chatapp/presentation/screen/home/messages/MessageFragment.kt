package com.example.chatapp.presentation.screen.home.messages

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentMessageBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.domain.model.ChatRoom
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.navigation.AppNavigation
import com.example.chatapp.presentation.screen.adapter.MessageListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding, MessageViewModel>(R.layout.fragment_message) {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val chatRooms = listOf(
        ChatRoom(senderUser = User("User1", "user1@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 1, lastMessage = "Hello!", messageType = 1, sendDate = LocalDateTime.now()),
        ChatRoom(senderUser = User("User2", "user2@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 0, lastMessage = "How are you?", messageType = 1, sendDate = LocalDateTime.now().minusDays(1)),
        ChatRoom(senderUser = User("User3", "user3@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 2, lastMessage = "Good morning", messageType = 1, sendDate = LocalDateTime.now().minusHours(2)),
        ChatRoom(senderUser = User("User4", "user4@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 1, lastMessage = "See you soon", messageType = 1, sendDate = LocalDateTime.now().minusMinutes(30)),
        ChatRoom(senderUser = User("User5", "user5@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 3, lastMessage = "Thank you!", messageType = 1, sendDate = LocalDateTime.now().minusWeeks(1)),
        ChatRoom(senderUser = User("User6", "user6@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 0, lastMessage = "Let's meet up", messageType = 1, sendDate = LocalDateTime.now().minusMonths(1)),
        ChatRoom(senderUser = User("User7", "user7@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 1, lastMessage = "Happy Birthday!", messageType = 1, sendDate = LocalDateTime.now().minusYears(1)),
        ChatRoom(senderUser = User("User8", "user8@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 2, lastMessage = "Congratulations", messageType = 1, sendDate = LocalDateTime.now().minusDays(10)),
        ChatRoom(senderUser = User("User9", "user9@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 0, lastMessage = "Good night", messageType = 1, sendDate = LocalDateTime.now().minusHours(5)),
        ChatRoom(senderUser = User("User10", "user10@example.com", "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg"), read = 1, lastMessage = "See you tomorrow", messageType = 1, sendDate = LocalDateTime.now().minusMinutes(10))
    )

    private val viewModel: MessageViewModel by viewModels()
    override fun getVM() = viewModel

    companion object {
        fun newInstance() = MessageFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.rvMessageList.adapter = MessageListAdapter(chatRooms)
        binding.rvMessageList.layoutManager = LinearLayoutManager(context)
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.primary_color_light)
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.ivCreateNewMsg.setOnClickListener {
            appNavigation.openHomeScreenToCreateGroupScreen()
        }
    }

}