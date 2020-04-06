public class Warrior implements Class {
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
        return 0.6*attack + 0.6*defense;
    }
}
