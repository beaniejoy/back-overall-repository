package chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultParamDemo {
    public static void main(String[] args) {
        Integer[] intArr = {1, 2, 3};

        List<Integer> intList = new ArrayList<>(Arrays.asList(intArr));

        // DefaultParam.kt 코틀린 코드 참고
        // kotlin 코드에서 @JvmOverloads 어노테이션을 붙이지 않으면 아래 전부 에러 발생
        // 붙이지 않으면 joinToString 에 정의한 모든 파라미터에 대해서 인자를 넣어주어야 한다.
        // java 코드에서는 kotlin 과 다르게 prefix 건너뛰고 postfix 만 선언하는 방식 불가능한 듯
        System.out.println("one default param: " + DefaultParamKt.joinToString(intList, "1"));
        System.out.println("two default param: " + DefaultParamKt.joinToString(intList, "1", "{"));
        System.out.println("three default param: " + DefaultParamKt.joinToString(intList, "1", "", "}"));

    }
}
