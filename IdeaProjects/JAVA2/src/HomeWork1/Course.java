package HomeWork1;

public class Course {

    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {

        Competitor[] c = team.getCompetitorTeam();

        for (Obstacle obs : obstacles) {
            System.out.println("Obstacle: " + obs.getName());
            for (Competitor competitor : c) {
                obs.doit(competitor);
            }
            System.out.println();

        }

    }

}
