package HomeWork1;

public class Human implements Competitor {

    String name;

    int maxRun;
    int maxJump;
    int maxSwim;

    boolean onDistance;


    public boolean isOnDistance() {
        return onDistance;
    }

    public Human(String name) {

        maxJump = 1;
        maxRun = 5000;
        maxSwim = 100;
        this.name = name;
        onDistance = true;

    }

    @Override
    public void run(int dist) {

        if (dist <= maxRun) {
            System.out.println(name + " success");
        } else {
            System.out.println(name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwim) {
            System.out.println(name + " success");
        } else {
            System.out.println(name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void jump(int height) {
        if (height <= maxJump) {
            System.out.println(name + " success");
        } else {
            System.out.println(name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void info() {

        System.out.println(name);

    }
}
