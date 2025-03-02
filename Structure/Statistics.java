package Structure;
import Creatures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final Island island;
    private static int animalsDied = 0;
    private static int animalsBorn = 0;
    private final HashMap<String, Integer>[][] numberOfCreatures;

    public Statistics(Island island) {
        this.island = island;
        numberOfCreatures = new HashMap[island.height][island.width];
        for(int i = 0; i < numberOfCreatures.length; i++){
            for(int j = 0; j < numberOfCreatures[i].length; j++){
                numberOfCreatures[i][j] = new HashMap<>();
            }
        }
    }

    public static void increaseAnimalsDied() {
        animalsDied++;
    }

    public void printAnimalsDied() {
        System.out.println("In the end of turn " + island.getCurrentTurn() + " there are total " + animalsDied + " animals died.");
    }

    public static void increaseAnimalsBorn() {
        animalsBorn++;
    }

    public void printAnimalsBorn() {
        System.out.println("In the end of turn " + island.getCurrentTurn() + " there are total " + animalsBorn + " animals born.");
    }

    public void updateNumberOfAnimals() {
        for (int i = 0; i < numberOfCreatures.length; i++) {
            for (int j = 0; j < numberOfCreatures[i].length; j++) {
                numberOfCreatures[i][j].put("Plant", island.cells[i][j].plants.size());
                for (Map.Entry<String, ArrayList<Animal>> entry : island.cells[i][j].allAnimals.entrySet()) {
                    numberOfCreatures[i][j].put(entry.getKey(), entry.getValue().size());
                }
            }
        }
    }

    public void showNumberOfAnimals() {
        for (int i = 0; i < numberOfCreatures.length; i++) {
            for (int j = 0; j < numberOfCreatures[i].length; j++) {
                for (Map.Entry<String, Integer> entry : numberOfCreatures[i][j].entrySet()) {
                    System.out.println("In the end of turn " + island.getCurrentTurn() + " in a cell [" + i + "][" + j + "] there are " + entry.getValue() + " creatures of " + entry.getKey() + " type.");
                }
            }
        }
    }
}