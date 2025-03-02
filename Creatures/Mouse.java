package Creatures;
import Structure.*;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    private static final int maximum = 500;
    private static final String name = "Mouse";
    public static final boolean isSmall = true;
    protected static final double mass = 0.05;
    public final int moveDistance = 1;
    private static final double needToEat = 0.01;

    public Mouse(Cell cell, Island island) {
        super(cell, island);
    }

    @Override
    public int getMoveDistance() {
        return moveDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public double getNeedToEat() {
        return needToEat;
    }

    @Override
    public boolean eat() {
        didEat = false;
        hunger = hunger + 2;
        if (!cell.allAnimals.get("Caterpillar").isEmpty()) {
            if (rand.nextInt(100) < 90) {
                cell.allAnimals.get("Caterpillar").getLast().toDelete = true;
                hunger = hunger - 3;
                didEat = true;
            }
        }
        if (!cell.plants.isEmpty()) { //Миша не зїсть багато рослин.
            hunger = hunger - 3;
            didEat = true;
        }
        if (hunger >= 10) {
            return true;
        }else {
            if (hunger < 0) {
            hunger = 0;
        }
            return false;
        }
    }
}