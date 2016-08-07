package au.com.dius.bowling;

public class BowlingGameApp {

    public static void main(String[] args) {

        BowlingGame bowlingGame = new BowlingGame(10, 10);

        // strike
        bowlingGame.roll(10);   // Frame 1
        bowlingGame.roll(10);   // Frame 2
        bowlingGame.roll(10);   // Frame 3

        // followed by two spare
        bowlingGame.roll(5);    // Frame 4
        bowlingGame.roll(5);
        bowlingGame.roll(1);    // Frame 5
        bowlingGame.roll(9);

        // followed by 1 pin rolls in each frame
        bowlingGame.roll(1);    // Frame 6
        bowlingGame.roll(1);
        bowlingGame.roll(1);    // Frame 7
        bowlingGame.roll(1);
        bowlingGame.roll(1);    // Frame 8
        bowlingGame.roll(1);
        bowlingGame.roll(1);    // Frame 9
        bowlingGame.roll(1);

        // followed by a strike in the last Frame
        bowlingGame.roll(10);   // Frame 10
        bowlingGame.roll(5);
        bowlingGame.roll(5);

        System.out.println("BowlingGame score : " + bowlingGame.score()); // we expect 125
    }

}