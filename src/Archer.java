public class Archer implements Class {
    private double attack;
    private double defense;

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public double getPerformance() {
        return 0.9*attack + 0.1*defense;
    }
}
