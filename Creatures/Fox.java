package Creatures;
import Structure.*;

public class Fox extends Carnivore{
    public static final int maximum = 30;
    private static final String name = "Fox";
    public static final boolean isSmall = false;
    protected static final double mass = 8;
    public final int moveDistance = 2;
    private static final double needToEat = 2;

    public Fox(Cell cell, Island island){
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
