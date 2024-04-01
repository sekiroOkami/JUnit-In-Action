public class FootballTeam implements Comparable<FootballTeam> {
    private int gamesWon;

    public FootballTeam(int gamesWon) {
        if (gamesWon < 0) throw new IllegalArgumentException("illegal argument: [" + gamesWon + "]");
        this.gamesWon = gamesWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }


    @Override
    public int compareTo(FootballTeam otherTeam) {
        return this.getGamesWon() - otherTeam.getGamesWon();
    }
}
