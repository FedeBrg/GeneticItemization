package utilities;

import classes.Character;

public class CharacterWithRelativePerformance {
    private Character character;
    private double relativePerformance;

    public CharacterWithRelativePerformance(Character character, double relativePerformance) {
        this.character = character;
        this.relativePerformance = relativePerformance;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public double getRelativePerformance() {
        return relativePerformance;
    }

    public void setRelativePerformance(double relativePerformance) {
        this.relativePerformance = relativePerformance;
    }
}
