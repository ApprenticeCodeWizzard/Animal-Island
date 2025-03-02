package Creatures;
import Structure.*;

public class Boa extends Carnivore {
    public static final int maximum = 30;
    private static final String name = "Boa";
    public static final boolean isSmall = false;
    protected static final double mass = 15;
    public final int moveDistance = 1;
    private static final double needToEat = 2;

    public Boa(Cell cell, Island island){
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