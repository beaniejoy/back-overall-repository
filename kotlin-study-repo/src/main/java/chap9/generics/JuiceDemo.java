package chap9.generics;

public class JuiceDemo {
    public static void main(String[] args) {
        // 두 개의 FruitBox 제너릭 클래스는 다른 타입이다. (convert 불가능)
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();

        System.out.println(Juicer.makeJuice(fruitBox));
        // makeJuice(FruitBox<? extends Fruit> box) <- 와일드카드로 확장할 수 있다.
        System.out.println(Juicer.makeJuice(appleBox)); // 와일드카드가 아닌 <Fruit>면 에러발생

    }
}
