package Creatures;
import Structure.*;

public class Horse extends Herbivore {
    public static final int maximum = 20;
    private static final String name = "Horse";
    public static final boolean isSmall = false;
    protected static final double mass = 400;
    public final int moveDistance = 4;
    private static final double needToEat = 60;

    public Horse(Cell cell, Island island){
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
}