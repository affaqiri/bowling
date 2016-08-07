package au.com.dius.bowling;

/**
 * Created by affaqiri on 06/08/2016.
 */
public class BowlingGame {

    private static final int NUMBER_ROLLS_PER_FRAME = 2;

    private int numberOfFrames;
    private int numberOfPinsPerFrame;

    private int currentRoll = 0;
    private int[] rolls;

    public BowlingGame(int numberOfFrames, int numberOfPinsPerFrame) {
        this.numberOfFrames = numberOfFrames;
        this.numberOfPinsPerFrame = numberOfPinsPerFrame;

        this.rolls = new int[(numberOfFrames * NUMBER_ROLLS_PER_FRAME) + 1];
    }

    public void roll(int numberOfPinsDown) {
        rolls[currentRoll++] = numberOfPinsDown;
    }

    public int score() {
        int score = 0;

        int currentRollIndex = 0;

        for (int currentFrameIndex = 0; currentFrameIndex < numberOfFrames; currentFrameIndex++, currentRollIndex += 2) {
            if (isStrike(currentRollIndex)) {
                score += numberOfPinsPerFrame + strikeBonus(currentRollIndex);
                currentRollIndex--;
            } else if (isSpare(currentRollIndex)) {
                score += numberOfPinsPerFrame + spareBonus(currentRollIndex);
            } else {
                score += sumOfRollsInFrame(currentRollIndex);
            }
        }

        return score;
    }

    protected boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == numberOfPinsPerFrame;
    }

    protected boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == numberOfPinsPerFrame;
    }

    protected int spareBonus(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    protected int strikeBonus(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    protected int sumOfRollsInFrame(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }

}