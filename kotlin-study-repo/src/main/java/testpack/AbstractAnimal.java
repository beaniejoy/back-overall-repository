package testpack;

public abstract class AbstractAnimal {

    private final String secret;

    protected String name;

    public AbstractAnimal(String name) {
        this.name = name;
        secret = "secret character";
    }

    public abstract void cry();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }
}