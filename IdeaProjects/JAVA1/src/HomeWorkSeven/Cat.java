package HomeWorkSeven;

public class Cat {

    private String name;
    private int appetite;
    private boolean full = false;

    public String getName() {
        return name;
    }

    public boolean isFull() {
        return full;

    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        if (plate.getFood() > appetite) {
            plate.decreaseFood(appetite);
            full = true;
        } else System.out.println(name + " - not enough food");

    }

}
