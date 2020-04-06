import java.util.Random;

public class RoleGame {
    public static void main(String[] args){

    }

    private double generateRandomHeight(){
        double lower = 1.3;
        double upper = 2.0;
        return Math.random() * (upper - lower) + lower;
    }
}
