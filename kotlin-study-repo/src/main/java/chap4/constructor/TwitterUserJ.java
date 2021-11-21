package chap4.constructor;

public class TwitterUserJ extends UserJ{
    private final String secondName;
    // UserJ 상위 클래스에 정의된 nickname 으로 덮어씌우게 된다.
    private final String nickname;

    public TwitterUserJ(String secondName) {
        super("first");
        this.nickname = secondName;
        this.secondName = secondName;
    }

    public TwitterUserJ(String secondName, Boolean isSubscribed) {
        super("first", isSubscribed);
        this.nickname = secondName;
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }
}
