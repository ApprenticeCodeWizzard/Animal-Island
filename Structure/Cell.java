package Structure;
import java.util.*;
import Creatures.*;

public class Cell implements Runnable {
    private final Island island;
    public final int y;
    public final int x;
    public final ArrayList<Plant> plants = new ArrayList<>();
    public final HashMap<String, ArrayList<Animal>> allAnimals = new HashMap<>();
    public final HashMap<String, ArrayList<Animal>> newAnimals = new HashMap<>();
    private boolean isBusy;

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
                continue;
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
            synchronized (animalType) {
                Iterator<Animal> iterator = animalType.iterator();
                while (iterator.hasNext()) {
                    Animal animal = iterator.next();
                    animal.toDelete = animal.eat();
                }
            }
        }
    }

    private void allMove() {
        for (ArrayList<Animal> animalType : allAnimals.values()) {
                Iterator<Animal> iterator = animalType.iterator();
                while (iterator.hasNext()) {
                    Animal animal = iterator.next();
                    animal.move();
                }
        }
    }

    @Override
    public void run() {
        allEat();
        allReproduce();
        allMove();

        for (Map.Entry<String, ArrayList<Animal>> entry : newAnimals.entrySet()) {
                    ArrayList<Animal> newAnimalsArray = newAnimals.get(entry.getValue());
                    if(newAnimalsArray == null) {
                        return;
                    }
                    ArrayList<Animal> oldAnimalsArray = allAnimals.get(entry.getKey());
                    Iterator<Animal> iterator = oldAnimalsArray.iterator();
                    while(iterator.hasNext()){
                        Animal animalToDelete = iterator.next();
                        if(animalToDelete.toDelete){
                            iterator.remove();
                        }
                    }
                    Iterator<Animal> iterator2 = newAnimalsArray.iterator();
                    while(iterator2.hasNext()){
                        Animal animalMoved = iterator2.next();
                        animalMoved.toDelete = false;
                    }
                    oldAnimalsArray.addAll(newAnimalsArray);
                    entry.getValue().clear();
        }
    }

    public boolean getIsBusy(){
        return isBusy;
    }
}