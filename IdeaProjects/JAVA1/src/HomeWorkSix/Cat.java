package HomeWorkSix;

public class Cat extends Animal {

    public Cat() {
        super(200, 0, 2f);
    }

    public Cat(int runDistance, int swimDistance, int height) {
        super(runDistance, swimDistance, height);

    }

    @Override
    public void swim(int swimDistance) {
        System.out.println("swim: cat can't swim");

    }

}
