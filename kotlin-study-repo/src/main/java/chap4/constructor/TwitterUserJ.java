package chap4.constructor;

public class TwitterUserJ extends UserJ{
    private final String secondName;

    public TwitterUserJ(String secondName) {
//        super("first");
        this.secondName = secondName;
    }

    public TwitterUserJ(String secondName, Boolean isSubscribed) {
        super("first", isSubscribed);
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }
}
