package example;

import java.util.Locale;

public enum HelloJavaStrategy {
    KOREAN(convertLang(Locale.KOREAN)),
    ENGLISH(convertLang(Locale.ENGLISH)),
    JAPANESE(convertLang(Locale.JAPANESE));

    private final String hello;

    HelloJavaStrategy(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    private static String convertLang(Locale locale) {
        if (Locale.KOREAN.equals(locale)) {
            return "안녕하세요.";
        } else if (Locale.JAPANESE.equals(locale)) {
            return "おはよう。";
        } else if (Locale.ENGLISH.equals(locale)) {
            return "hello";
        } else {
            return "no lang";
        }
    }

    public static void main(String[] args) {
        System.out.println(HelloJavaStrategy.KOREAN.hello);
        System.out.println(HelloJavaStrategy.JAPANESE.hello);
        System.out.println(HelloJavaStrategy.ENGLISH.hello);
    }
}
