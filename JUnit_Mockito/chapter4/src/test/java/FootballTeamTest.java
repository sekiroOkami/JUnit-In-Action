import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FootballTeamTest {

    private static final int THREE_GAMES_WON = 3;
    private static final int ANY_NUMBER = 123;

    FootballTeam footballTeam ;

    @BeforeEach
    void init() {
        footballTeam = new FootballTeam(THREE_GAMES_WON);
    }

    @Test
    void constructorShouldSetGameWon() {
        assertEquals(3, footballTeam.getGamesWon());
    }

    @ParameterizedTest
    @MethodSource("nbOfGamesWon")
    void constructorShouldSetGameWon(int nbOfGamesWon) {
        footballTeam = new FootballTeam(nbOfGamesWon);
        assertEquals(nbOfGamesWon, footballTeam.getGamesWon());
    }

    @ParameterizedTest
    @MethodSource("illegalNbOfGamesWon")
    void constructorShouldThrowExceptionForIllegalGamesNb(int illegalArgument) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new FootballTeam(illegalArgument)
        );
    }

    @Test
    void shouldBePossibleToCompareTeams() {
        footballTeam = new FootballTeam(ANY_NUMBER);
        assertTrue(footballTeam instanceof Comparable, "FootballTeam should implement Comparable");
    }

    @Test
    void teamsWithMoreMatchesWonShouldBeGreater() {
        var team2 = new FootballTeam(2);
        var team3 = new FootballTeam(3);
        assertTrue(team3.compareTo(team2) > 0);
    }

    @Test
    void teamWithLessMatchesWonShouldBelesser() {
        var team2 = new FootballTeam(2);
        var team3 = new FootballTeam(3);
        assertTrue( team2.compareTo(team3) < 0, "team with " + team2.getGamesWon()
                        + " games won should be ranked after the team with "
                        + team3.getGamesWon() + " games won"
        );
    }

    @Test
    void teamWithSameNumberOfMatchesWonShouldBeEqual() {
        var teamA = new FootballTeam(2);
        var teamB = new FootballTeam(2);
        assertTrue( teamA.compareTo(teamB) == 0, "team with " + teamA.getGamesWon()
                + " games won should be ranked the same as the team with "
                + teamB.getGamesWon() + " games won"
        );
    }


    static Stream<Arguments> illegalNbOfGamesWon() {
        return Stream.of(
                Arguments.arguments(-10),
                Arguments.arguments(-1)
        );
    }

    static Stream<Arguments> nbOfGamesWon() {
        return Stream.of(
                Arguments.arguments(0),
                Arguments.arguments(1),
                Arguments.arguments(2)
        );
    }
}
