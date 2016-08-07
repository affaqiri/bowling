package au.com.dius.bowling;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by affaqiri on 06/08/2016.
 */
public class BowlingGameTest {

    public BowlingGame bowlingGame;

    @Before
    public void init() {
        this.bowlingGame = new BowlingGame(10, 10);
    }

    @Test
    public void rollingZeroScoresZero() {
        bowlingGame.roll(0);
        assertThat(bowlingGame.score(), is(0));
    }

    @Test
    public void rollingOneScoresOne() {
        bowlingGame.roll(1);
        assertThat(bowlingGame.score(), is(1));
    }

    @Test
    public void rollingOneThenOneScoresTwo() {
        roll(2, 1);
        assertThat(bowlingGame.score(), is(2));
    }

    @Test
    public void rollingAllZerosScoresZero() {
        roll(20, 0);
        assertThat(bowlingGame.score(), is(0));
    }

    @Test
    public void rollingAllOnesScoresTwenty() {
        roll(20, 1);
        assertThat(bowlingGame.score(), is(20));
    }

    @Test
    public void rollingASpareThenOneScoresTwelve() {
        rollASpare();
        roll(1, 1);
        roll(17, 0);
        assertThat(bowlingGame.score(), is(12));
    }

    @Test
    public void rollingAStrikeThenOneThenOneScoresFourteen() {
        rollAStrike();
        roll(2, 1);
        assertThat(bowlingGame.score(), is(14));
    }

    @Test
    public void rollingAllStrikesScoresThreeHundred() {
        roll(12, 10);
        assertThat(bowlingGame.score(), is(300));
    }

    // For completeness, not really needed in a TDD approach
    @Test
    public void rollingAllSparesScoresHundredFifty() {
        roll (21, 5);
        assertThat(bowlingGame.score(), is(150));
    }

    private void rollASpare() {
        roll(2, 5);
    }

    private void rollAStrike() {
        roll(1, 10);
    }

    private void roll(int times, int numberOfPinsDown) {
        for (int rollTurn = 0; rollTurn < times; rollTurn++) {
            bowlingGame.roll(numberOfPinsDown);
        }
    }

}