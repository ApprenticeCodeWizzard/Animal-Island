package Structure;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import Creatures.*;

public class Cell implements Runnable{
    private final Island island;
    public final int y;
    public final int x;
    public final ArrayList<Plant> plants = new ArrayList<>();
    public final ConcurrentHashMap<String, ArrayList<Animal>> allAnimals = new ConcurrentHashMap<>();
    public boolean isBusy;

    public Cell(int y, int x, Island island) {
        this.island = island;
        this.y = y;
        this.x = x;
        isBusy = false;
    }

    private void allReproduce() {
        Plant.reproducePlants(this, this.island);
        for (List<Animal> animalType : allAnimals.values()) {
            if (animalType.isEmpty()) {
                break;
            }
            for (Animal animal : animalType) {
                animal.setReproduced(false);
            }
            for (Animal animal : animalType) {
                if (!animal.getIsReproduced() && !animal.isFemale) {
                    animal.reproduce();
                }
            }
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
        for (List<Animal> animalType : allAnimals.values()) {
            for (Animal animal : animalType) {
                animal.move();
            }
        }
    }

    @Override
    public void run() {
        isBusy = true;
        allEat();
        allReproduce();
        allMove();
        isBusy = false;
    }
}