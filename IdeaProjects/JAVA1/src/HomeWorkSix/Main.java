package HomeWorkSix;

public class Main {
    public static void main(String[] args) {


        Dog dog1 = new Dog(200, 100, 2);

        Cat cat2 = new Cat(400, 5, 30);


        System.out.println("cat2");
        cat2.jump(300);
        cat2.swim(3);
        cat2.run(5);
        System.out.println();
        System.out.println("Dog");
        dog1.run(150);
        dog1.swim(50);
        dog1.jump(3);


    }
}
