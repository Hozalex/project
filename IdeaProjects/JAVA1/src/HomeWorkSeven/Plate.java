package HomeWorkSeven;

public class Plate {

    private int food;


    public Plate(int food) {
        if (food < 0) {
            this.food = 0;
        }
        this.food = food;

    }

    public void addFood(int food) {
        this.food += food;
    }

    public int getFood() {
        return food;
    }


    public void decreaseFood(int i) {
        food -= i;
    }

    public void infoPlate() {
        System.out.println("Plate: " + food);

    }


}
