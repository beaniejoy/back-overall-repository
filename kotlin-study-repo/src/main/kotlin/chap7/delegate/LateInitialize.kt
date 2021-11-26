package chap7.delegate

class Email(val address: String)

fun loadEmails(person: Person): List<Email> =
    listOf(Email("beaniejoy@example.com"), Email("joy@example.com"))

interface Person

class LazyPerson(val name: String): Person {
    private var _emails: List<Email>? = null
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }

            // _email 변수는 nullable type 인데 return type은 not null type이다.
            // !!를 통해 not null을 확정해야 한다.
            return _emails!!
        }
}

// by lazy 를 통해 위의 번잡한 코드를 한 줄로 줄일 수 있다.
class LazyPersonByDelegation(val name: String): Person {
    val emails by lazy { loadEmails(this) }
}