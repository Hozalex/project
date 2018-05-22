package HomeWork1;

public abstract class Obstacle {

    public abstract void doit(Competitor competitor);

    public abstract String getName();

}

class Wall extends Obstacle {

    int height;
    private String name = "Wall";

    @Override
    public String getName() {
        return name;
    }

    public Wall(int height) {
        this.height = height;

    }

    @Override
    public void doit(Competitor competitor) {

        competitor.jump(height);

    }
}

class Cross extends Obstacle {
    int distance;
    private String name = "Cross";

    @Override
    public String getName() {
        return name;
    }

    public Cross(int distance) {


        this.distance = distance;
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.run(distance);
    }
}

class Water extends Obstacle {

    int dist;
    private String name = "Water";

    @Override
    public String getName() {
        return name;
    }

    public Water(int dist) {

        this.dist = dist;

    }

    @Override
    public void doit(Competitor competitor) {

        competitor.swim(dist);

    }
}

