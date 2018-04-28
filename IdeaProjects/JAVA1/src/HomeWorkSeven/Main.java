package HomeWorkSeven;

public class Main {
    public static void main(String[] args) {

        Cat[] cats = {new Cat("Bars", 5), new Cat("Murz", 8), new Cat("Cthulhu", 50)};
        Plate plate = new Plate(50);

        plate.infoPlate();

        for (Cat c : cats) {
            c.eat(plate);
            if (c.isFull()) {
                System.out.println(c.getName() + " full");
            } else System.out.println(c.getName() + " is hungry");

        }
        plate.infoPlate();

    }
}
