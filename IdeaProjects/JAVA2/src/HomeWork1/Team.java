package HomeWork1;

public class Team {

    private String teamName;
    private Competitor[] competitorTeam;

    public Competitor[] getCompetitorTeam() {
        return competitorTeam;
    }

    public Team(String teamName, Competitor[] competitorTeam) {
        this.teamName = teamName;
        this.competitorTeam = competitorTeam;
    }

    public void teamInfo() {
        System.out.println("Team " + " \"" + teamName + "\"" + " - ");

        for (Competitor c : competitorTeam) {
            c.info();
        }
        System.out.println();

    }

    public void showResults() {

        System.out.println("Result: ");

        for (Competitor c : competitorTeam) {
            if (c.isOnDistance()) {
                System.out.printf("Winner is ");
                c.info();
            }

        }


    }

}
