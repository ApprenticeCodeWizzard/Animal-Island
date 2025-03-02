package Creatures;
import Structure.*;

public class Eagle extends Carnivore {
    public static final int maximum = 20;
    private static final String name = "Eagle";
    public static final boolean isSmall = false;
    protected static final double mass = 6;
    public final int moveDistance = 4;
    private static final double needToEat = 1;

    public Eagle(Cell cell, Island island){
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