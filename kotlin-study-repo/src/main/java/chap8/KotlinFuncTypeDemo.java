package chap8;

import chap8.functype.FunctionTypeKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;

import java.util.ArrayList;

public class KotlinFuncTypeDemo {
    public static void main(String[] args) {
        // chap8.functype.FunctionType.kt 에서 정의한 processTheAnswer 최상위 함수 호출 방식
        FunctionTypeKt.processTheAnswer(num -> num + 1);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("testpack");

        // kotlin -- strings.forEach{ i -> println(i) }
        CollectionsKt.forEach(strings, s -> {
            System.out.println(s);
            return Unit.INSTANCE;
        });
    }
}
