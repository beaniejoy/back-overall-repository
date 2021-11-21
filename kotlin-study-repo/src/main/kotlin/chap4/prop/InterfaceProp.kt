package chap4.prop

/*
* 코틀린에서는 자바와 달리 interface 에서도 필드를 정의할 수 있다.
* 메소드 사용법과 마찬가지로 override 구현해야하는 필드와 안해도되는 default 필드를 정의할 수 있다.
* */

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

// nickname 뒷받침하는 필드가 없다. (커스텀 getter)
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