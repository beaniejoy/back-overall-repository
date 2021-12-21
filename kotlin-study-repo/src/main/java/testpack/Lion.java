package testpack;

public class Lion extends AbstractAnimal {
    private String character;

    public Lion(String name, String character) {
        super(name);
        this.character = character;
    }

    @Override
    public void cry() {
        System.out.println("Lion bark");
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
