package HomeWork1;

public class Animal implements Competitor {

    String type;
    String name;

    int maxRun;
    int maxJump;
    int maxSwim;

    boolean onDistance;

    public boolean isOnDistance() {
        return onDistance;
    }

    public Animal(String type, String name, int maxRun, int maxJump, int maxSwim) {
        this.type = type;
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.maxSwim = maxSwim;
        this.onDistance = true;

    }


    @Override
    public void run(int dist) {

        if (dist <= maxRun) {
            System.out.println(type + " " + name + " success");
        } else {
            System.out.println(type + " " + name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwim) {
            System.out.println(type + " " + name + " success");
        } else {
            System.out.println(type + " " + name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void jump(int height) {
        if (height <= maxJump) {
            System.out.println(type + " " + name + " success");
        } else {
            System.out.println(type + " " + name + " fail");
            onDistance = false;

        }

    }

    @Override
    public void info() {

        System.out.println(type + " " + name);

    }
}
