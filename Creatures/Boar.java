package Creatures;
import Structure.*;
import java.util.Random;

public class Boar extends Herbivore {
    public static final int maximum = 50;
    private static final String name = "Boar";
    public static final boolean isSmall = false;
    protected static final double mass = 400;
    private final int moveDistance = 2;
    private static final double needToEat = 50;

    public Boar(Cell cell, Island island){
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
    public synchronized boolean eat() {
        if (!cell.allAnimals.get("Caterpillar").isEmpty()) {
            synchronized (this.cell.allAnimals.get("Caterpillar")) {
                if (rand.nextInt(100) < 90) {
                    cell.allAnimals.get("Caterpillar").removeLast();
                }
            }
        }
        if(!cell.allAnimals.get("Mouse").isEmpty()) {
            synchronized (this.cell.allAnimals.get("Mouse")) {
                if (rand.nextInt(100) < 50) {
                    cell.allAnimals.get("Mouse").removeLast();
                }
            }
        }
        return super.eat();
    }
}