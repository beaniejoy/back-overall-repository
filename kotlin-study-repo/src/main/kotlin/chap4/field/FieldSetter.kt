package chap4.field

class User(val name: String) {
    // 뒷받침하는 필드 존재
    var address: String = "unspecified"
        set (value: String) {
            println("""
                Address was changed for ${name}:
                "${field}" -> "${value}".""".trimIndent())
            field = value
        }
}

// getter, setter 에 field 없으면 뒷받침하는 필드 존재 X
class UserNoneField(val name: String) {
    var address: String
        get() {
            //println("address getter")
            return "address getter"
        }
        set (value: String) {
            println("address setter")
        }
}

fun main() {
    val user = User("beanie")
    println(user.address) // unspecified
    user.address = "address input"

    val nonUser = UserNoneField("joy")
    println(nonUser.address)
}