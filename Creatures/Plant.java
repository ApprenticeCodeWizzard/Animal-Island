package Creatures;
import Structure.*;

public class Plant extends Creature {
    public static final int maximum = 200;
    private static final double mass = 1;
    private static final String name = "Plant";

    public Plant(Cell cell, Island island){
        super(cell, island);
    }

    public static void reproducePlants(Cell cell, Island island){
        int howMany = (cell.plants.size() * 2) + 5;//+5 щоб рослини потруху відростали якщо їх не лишилось взагалі.
        if (howMany > maximum - cell.plants.size()){
            howMany = maximum - cell.plants.size();
        }
        for (int i = 0; i < howMany; i++){
            cell.plants.add(new Plant(cell, island));
        }
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
}