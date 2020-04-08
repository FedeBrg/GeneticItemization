package interfaces;

import java.util.List;
import character.Character;

public interface Selector {
    List<Character> select(List<Character> population, int limit);
}
