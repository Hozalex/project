package HomeWork1;

public class Main {

    public static void main(String[] args) {


        Course c = new Course(new Obstacle[]{new Wall(1), new Cross(300), new Water(2)});
        Team team = new Team("Dream Team", new Competitor[]{new Human("John"), new Cat("Tom"), new Dog("Sparky")});

        team.teamInfo();

        c.doIt(team);

        team.showResults();

    }

}
