package interfaces;

import java.util.List;

public interface Implementation {
    List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size);
}
