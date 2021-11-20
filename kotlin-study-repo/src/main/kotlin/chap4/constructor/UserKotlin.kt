package chap4.constructor

open class User(
    val nickname: String,
    val isSubscribed: Boolean = true
)

class TwitterUser(nickname: String) : User(nickname)