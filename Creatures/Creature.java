package Creatures;
import java.util.Random;
import Structure.*;

public abstract class Creature {
    protected Cell cell;
    protected Island island;
    public final boolean isFemale;
    protected boolean isReproduced;
    protected static final Random rand = new Random();

    public Creature(Cell cell, Island island){
        this.cell = cell;
        this.island = island;
        this.isFemale = rand.nextBoolean();
        this.isReproduced = true;
    }

    public abstract String getName();

    public boolean getIsReproduced(){
        return isReproduced;
    }
    public void setReproduced(boolean value){
        isReproduced = value;
    }

    public abstract int getMaximum();

    public abstract double getMass();
}