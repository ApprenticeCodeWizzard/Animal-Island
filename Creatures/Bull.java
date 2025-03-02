package Creatures;
import Structure.*;

public class Bull extends Herbivore {
    public static final int maximum = 10;
    private static final String name = "Bull";
    public static final boolean isSmall = false;
    protected static final double mass = 700;
    public final int moveDistance = 3;
    private static final double needToEat = 100;

    public Bull(Cell cell, Island island){
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