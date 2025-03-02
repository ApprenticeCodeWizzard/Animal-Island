package Creatures;
import Structure.*;

public class Duck extends Herbivore {
    public static final int maximum = 200;
    private static final String name = "Duck";
    public static final boolean isSmall = true;
    protected static final double mass = 1;
    public final int moveDistance = 4;
    private static final double needToEat = 0.15;

    public Duck(Cell cell, Island island){
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
    public double getMass(){
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
                hunger--;
            }
        }
        if (!cell.plants.isEmpty()) {
                hunger = hunger - 2;
                didEat = true;
        }
        if(hunger < 0) {
            hunger = 0;
        }
        if (hunger >= 10){
            return true;
        }else {
            return false;
        }
    }
}