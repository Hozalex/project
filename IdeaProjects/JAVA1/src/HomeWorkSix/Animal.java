package HomeWorkSix;

public abstract class Animal {

    private int runDistance;
    private int swimDistance;
    private float height;

    public Animal(int runDistance, int swimDistance, float height) {
        if (runDistance < 0 || swimDistance < 0 || height < 0) {
            this.runDistance = 0;
            this.swimDistance = 0;
            this.height = 0;
        }
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        this.height = height;
    }

    public void run(int runDistance) {
        if (runDistance <= 0 || runDistance > this.runDistance) {
            System.out.println("run: false");
        } else System.out.println("run: true");
    }

    public void swim(int swimDistance) {
        if (swimDistance <= 0 || swimDistance > this.swimDistance) {
            System.out.println("swim: false");
        } else System.out.println("swim: true");
    }

    public void jump(float height) {
        if (height <= 0 || height > this.height) {
            System.out.println("jump: false");
        } else System.out.println("jump: true");
    }
}
