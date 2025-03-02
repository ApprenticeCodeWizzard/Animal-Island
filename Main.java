import Creatures.Creature;
import Structure.*;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(20, 100, 10);
        island.setAnimals();
        Statistics statistics = new Statistics(island);
        island.cycle(statistics);
    }
}