package Creatures;
import Structure.*;

public class Bear extends Carnivore {
    public static final int maximum = 5;
    private static final String name = "Bear";
    public static final boolean isSmall = false;
    protected static final double mass = 500;
    public final int moveDistance = 2;
    private static final double needToEat = 80;

    public Bear(Cell cell, Island island){
        super(cell, island);
        eatChance = AnimalTables.foodPriorityTable.get(name);
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
}