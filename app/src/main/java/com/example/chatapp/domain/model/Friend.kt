package com.example.chatapp.domain.model

data class Friend(
    val uid: String? = null,
    val userName: String? = null,
    val profileImageUrl: String? = null,
    val friendState: FriendState = FriendState.NONE
)

fun generateFriendList(): List<Friend> {
    return listOf(
        Friend("1", "Alice", "https://randomuser.me/api/portraits/women/1.jpg"),
        Friend("2", "Bob", "https://randomuser.me/api/portraits/men/2.jpg"),
        Friend("3", "Charlie", "https://randomuser.me/api/portraits/men/3.jpg"),
        Friend("4", "David", "https://randomuser.me/api/portraits/men/4.jpg"),
        Friend("5", "Eve", "https://randomuser.me/api/portraits/women/5.jpg"),
        Friend("6", "Frank", "https://randomuser.me/api/portraits/men/6.jpg"),
        Friend("7", "Grace", "https://randomuser.me/api/portraits/women/7.jpg"),
        Friend("8", "Hank", "https://randomuser.me/api/portraits/men/8.jpg"),
        Friend("9", "Ivy", "https://randomuser.me/api/portraits/women/9.jpg"),
        Friend("10", "Jack", "https://randomuser.me/api/portraits/men/10.jpg")
    )
}

enum class FriendState {
    FRIEND,
    ADDED,
    REQUEST,
    NONE;

    override fun toString(): String {
        return when (this) {
            FRIEND -> "FRIEND"
            ADDED -> "ADDED"
            REQUEST -> "REQUEST"
            NONE -> "NONE"
        }
    }

    companion object {
        fun fromString(string: String): FriendState {
            return when(string) {
                "friend" -> FRIEND
                "added" -> ADDED
                "request" -> REQUEST
                else -> NONE
            }
        }
    }

}
