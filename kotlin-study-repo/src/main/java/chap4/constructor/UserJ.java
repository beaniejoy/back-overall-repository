package chap4.constructor;

public class UserJ {
    private String nickname;
    private Boolean isSubscribed;

    public UserJ() {

    }

    public UserJ(String nickname) {
        this.nickname = nickname;
        this.isSubscribed = true;
    }

    public UserJ(String nickname, Boolean isSubscribed) {
        this.nickname = nickname;
        this.isSubscribed = isSubscribed;
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }
}
