package interfaces;

import java.util.List;

public interface Implementation {
    List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size, Selector selector1, Selector selector2, double b);
}
