package Creatures;
import Structure.*;

public class Rabbit extends Herbivore {
    public static final int maximum = 150;
    private static final String name = "Rabbit";
    public static final double mass = 2;
    public static final boolean isSmall = true;
    public final int moveDistance = 2;
    private static final double needToEat = 0.45;

    public Rabbit(Cell cell, Island island){
        super(cell, island);
    }

    public Rabbit makeNew(Cell cell, Island island) {
        System.out.println("In cell [" + cell.y + "][" + cell.x + "] a new " + name + " was born");
        return new Rabbit(cell, island);
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
    public int getMaximum(){
        return maximum;
    }

    @Override
    public double getMass(){
        return mass;
    }

    @Override
    public double getNeedToEat(){
        return needToEat;
    }
}