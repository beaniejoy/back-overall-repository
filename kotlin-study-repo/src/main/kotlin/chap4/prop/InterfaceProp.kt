package chap4.prop

// 인터페이스에도 커스텀 게터 사용가능
interface User {
    // override 필수
    val nickname: String
    // override 안해도됨
    val anotherName: String
        get() = "another ${nickname}"
}

// 뒷받침하는 필드가 존재
class PrivateUser(override val nickname: String): User

// nickname 뒷받침하는 필드가 없다.
// nickname 호출할 때마다 getter 계속 활용
class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@')
}

// nickname 뒷받침하는 필드가 있다.
// 뒷받침하는 필드에 저장하고 불러오는 방식으로 활용
class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookName(accountId)

    fun getFacebookName(accountId: Int) = "${accountId}"
}