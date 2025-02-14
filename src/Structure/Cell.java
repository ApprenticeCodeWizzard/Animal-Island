package Structure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Creatures.*;

public class Cell implements Runnable {
    private final Island island;
    public final int y;
    public final int x;
    public final ArrayList<Plant> plants = new ArrayList<>();
    public final ConcurrentHashMap<String, ArrayList<Animal>> allAnimals = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<String, ArrayList<Animal>> newAnimals = new ConcurrentHashMap<>();

    public Cell(int y, int x, Island island) {
        this.island = island;
        this.y = y;
        this.x = x;
    }

    private void allReproduce() {
        Plant.reproducePlants(this, this.island);
        for (List<Animal> animalType : allAnimals.values()) {
            if (animalType.isEmpty()) {
                continue;
            }
            List<Animal> newAnimals = new ArrayList<>();
            for (Animal animal : animalType) {
                animal.setReproduced(false);
            }
            for (Animal animal : animalType) {
                if (!animal.getIsReproduced() && !animal.isFemale) {
                    animal.reproduce();
                }
            }
            animalType.addAll(newAnimals);
        }
    }

    private void allEat() {
        for (List<Animal> animalType : allAnimals.values()) {
            for (Animal animal : animalType) {
                boolean toRemove = animal.eat();
                if (toRemove) {
                    synchronized (allAnimals.get(animal.getName())) {
                        System.out.println("In turn " + island.getCurrentTurn() + " " + animal.getName() + " in [" + this.y + "][" + this.x + "] had died of starvation");
                        Statistics.increaseAnimalsDied();
                        allAnimals.get(animal.getName()).remove(animal); //Тварина помирає з голоду.
                    }
                }
            }
        }
    }

    private void allMove() {
        Iterator<ArrayList<Animal>> iterator1 = this.allAnimals.values().iterator();
        while (iterator1.hasNext()) {
            ArrayList<Animal> animalType = iterator1.next();
            Iterator<Animal> iterator2 = animalType.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().move();
            }
        }
    }

    @Override
    public void run() {
        allEat();
        allReproduce();
        allMove();
        synchronized (allAnimals) {
            for (Map.Entry<String, ArrayList<Animal>> entry : newAnimals.entrySet()) {
                allAnimals.get(entry.getKey()).addAll(newAnimals.get(entry.getValue()));
            }
        }
    }
}