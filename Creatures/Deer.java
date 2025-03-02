package Creatures;
import Structure.*;

public class Deer extends Herbivore {
    public static final int maximum = 20;
    private static final String name = "Deer";
    public static final boolean isSmall = false;
    protected static final double mass = 300;
    public final int moveDistance = 4;
    private static final double needToEat = 50;

    public Deer(Cell cell, Island island){
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