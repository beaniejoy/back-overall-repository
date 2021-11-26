package chap8.functype

/*
* FunctionN 이름으로 인터페이스 생성
* 여기서는 파라미터 한 개짜리 함수 타입을 선언했으므로 **Function1<in P1, out R>** 인자 하나짜리 자바 인터페이스 생성
*
*   public static final void processTheAnswer(@NotNull *Function1* f) {
      Intrinsics.checkNotNullParameter(f, "f");
      int var1 = ((Number)f.invoke(42)).intValue();
      System.out.println(var1);
    }
*
* Function interface 내부에 invoke 메소드가 정의되어 있어 이것을 호출하게 됨
*/
fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

// 자바에서 호출할 수 있다.

fun main() {
    val list = listOf("add", "minus")
    list.forEach { i -> println(i) }
}