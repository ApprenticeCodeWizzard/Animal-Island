package Creatures;
import Structure.*;

public class Wolf extends Carnivore {
    public static final int maximum = 30;
    private static final String name = "Wolf";
    public static final boolean isSmall = false;
    protected static final double mass = 50;
    public final int moveDistance = 3;
    private static final double needToEat = 8;

    public Wolf(Cell cell, Island island){
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