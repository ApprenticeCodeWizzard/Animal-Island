package Creatures;
import Structure.*;

public class Caterpillar extends Herbivore {
    public static final int maximum = 1000;
    private static final String name = "Caterpillar";
    public static final boolean isSmall = true;
    protected static final double mass = 0.01;
    public final int moveDistance = 0;
    private static final double needToEat = 0;

    public Caterpillar(Cell cell, Island island){
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
    public boolean eat(){
        if (cell.plants.isEmpty()) {
            hunger = hunger + 4;
        } else {
            hunger--;
        }
        if(hunger < 0){
            hunger = 0;
        }
        if(hunger >= 10) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void move(){}
}