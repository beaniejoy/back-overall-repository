package io.beaniejoy.modifier;

public class ProtectedDemo {
    public static void main(String[] args) {
        // ProtectedClass 가 속한 패키지와 다른 곳에 위치한 클래스에서는
        // 직접 protected 생성자 호출 불가
        ProtectedClass protectedClass = new ProtectedClass();
    }
}
