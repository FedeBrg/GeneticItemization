package utilities;

import interfaces.Character;

public class CharacterWithFixedPerformance {
    private Character character;
    private double fixedPerformance;

    public CharacterWithFixedPerformance(Character character, double fixedPerformance) {
        this.character = character;
        this.fixedPerformance = fixedPerformance;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public double getFixedPerformance() {
        return fixedPerformance;
    }

    public void setFixedPerformance(double fixedPerformance) {
        this.fixedPerformance = fixedPerformance;
    }
}
