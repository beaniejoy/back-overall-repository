package chap6.nullable

fun sendEmailTo(email: String) {
    println("Sending Email to ${email}")
}

fun main() {
    var email: String? = "beanie.joy@kakaomobility.com"
    email?.let { sendEmailTo(it)}

//     컴파일 단계에서 에러 발생
//     let 함수는 수신객체 (this)에 대해서 따로 null 체크를 하지 않는다.
//    email.let { sendEmailTo(it) }
}
