package Creatures;
import Structure.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {
    LinkedHashMap<String, Integer> eatChance;
    public boolean didEat;

    public Carnivore(Cell cell, Island island) {
        super(cell, island);
        didEat = true;
    }

    public boolean eat() {
        didEat = false;
        hunger = hunger + 2;
        for (String species : eatChance.keySet()) {
            ArrayList<Animal> type = cell.allAnimals.get(species);
            if (type.isEmpty()) {
                continue;
            }
                Animal prey = type.getLast();
                if (ThreadLocalRandom.current().nextInt(100) > eatChance.get(species)) {
                    System.out.println("In turn " + island.getCurrentTurn() + " " + this.getName() + " in [" + cell.y + "][" + cell.x + "] tried to catch " + species + ", but failed");
                    break;
                }
                if (prey.getMass() > getNeedToEat()) {
                    hunger = hunger - 3;
                    didEat = true;
                    System.out.println("In turn " + island.getCurrentTurn() + " " + prey.getName() + " in [" + cell.y + "][" + cell.x + "] was eaten by a " + this.getName());
                    Statistics.increaseAnimalsDied();
                    prey.toDelete = true;
                    if (hunger < 0) {
                        hunger = 0;
                    }
                    break;
                }
        }
            if (hunger >= 10) {
                return true;
            }else{
                return false;
            }
    }
}