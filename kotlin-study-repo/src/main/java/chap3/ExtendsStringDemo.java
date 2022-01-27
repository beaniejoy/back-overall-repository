package chap3;

public class ExtendsStringDemo {
    public static void main(String[] args) {
        // java 에서는 ExtendsStringKt.java class의 static final method로 생성
        System.out.println(ExtendsStringKt.lastChar("hello world java"));
        System.out.println(ExtendsStringKt.getLastChar(new StringBuilder("Java!")));
    }
}
