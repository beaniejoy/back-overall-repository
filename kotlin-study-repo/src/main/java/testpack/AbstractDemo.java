package testpack;

public class AbstractDemo {
    public static void main(String[] args) {
        AbstractAnimal animal = new Lion("lion", "scary");
        Lion lion = new Lion("lion2", "happy");

        animal.cry();
        lion.cry();
        // AbstractAnimal 참조변수로 받으면 getCharacter() 메소드에 접근 불가
        System.out.println(animal.name + " " + animal.getSecret());
        // Lion 참조변수로 받으면 상속받은 추상클래스의 필드들에 접근할 수 있다. (private 필드는 getter를 통해 접근 가능)
        // 즉 추상클래스를 상속받으면 추상클래스에 있는 내용들도 사용가능
        System.out.println(lion.name + " " + lion.getCharacter() + " " + lion.getSecret());
    }
}
