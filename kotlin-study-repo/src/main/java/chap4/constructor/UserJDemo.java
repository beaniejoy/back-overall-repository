package chap4.constructor;

public class UserJDemo {
    public static void main(String[] args) {
        TwitterUserJ user = new TwitterUserJ("Beanie");

        // TwitterUserJ 클래스에서 secondName 생성자 안에 super를 따로 정의하지 않으면
        // User default 생성자(super())가 작동
        // 여기서는 nickname 과 isSubscribed 가 null 이 된다.
        System.out.println("user name: " + user.getNickname());
//        System.out.println("user second: " + user.getSecondName());
        System.out.println("user isSubscribed: " + user.getSubscribed());

        User userKt = new User("beanie", false);
    }
}
