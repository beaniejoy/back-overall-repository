package chap4.companion

class User private constructor(val nickname: String) {
    // 이름을 붙이지 않으면 기본으로 Companion 으로 동반 객체 이름 설정
    // User.Companion.newSubscribingUser()
    // User.newSubscribingUser()
    companion object {
        fun newSubscribingUser(email: String) =
            User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) {
            User(getFacebookName(accountId))
        }

        private fun getFacebookName(accountId: Int): String = "${accountId}"
    }
}

fun main() {
    val beanie = User.newSubscribingUser("beaniejoy@example.com")
}