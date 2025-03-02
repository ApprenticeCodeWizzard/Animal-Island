package Structure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

import Creatures.*;

public class Island {
    public final int maxTurns;
    private int turn;
    public final int height;
    public final int width;
    public volatile Cell[][] cells;
    ExecutorService executor;

    public Island(int height, int width, int maxTurns) {
        turn = 0;
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        this.maxTurns = maxTurns;
    }

    public void setAnimals() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j, this);
                Cell currentCell = cells[i][j];
                for (int iter = 0; iter < Plant.maximum / 2; iter++) {
                    currentCell.plants.add(new Plant(currentCell, this));
                }
                for (String animalName : AnimalTables.animalNames) {
                    currentCell.allAnimals.put(animalName, new ArrayList<Animal>());
                    currentCell.newAnimals.put(animalName, new ArrayList<Animal>());
                    for (int iter = 0; iter < AnimalTables.maxOfEach[Arrays.asList(AnimalTables.animalNames).indexOf(animalName)]; iter++) {
                        currentCell.allAnimals.get(animalName).add(Animal.makeNew(animalName, currentCell, this));
                    }
                }
            }
        }
    }

    public void cycle(Statistics statistics) {
        turn = 1;
        while (turn < maxTurns) {
            executor = Executors.newFixedThreadPool(4);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    executor.execute(cells[i][j]);
                }
            }
            executor.shutdown();
            try {
                boolean didOver = executor.awaitTermination(60, TimeUnit.SECONDS); // Timeout to prevent indefinite blocking
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
            statistics.updateNumberOfAnimals();
            statistics.showNumberOfAnimals();
            statistics.printAnimalsBorn();
            statistics.printAnimalsDied();
            turn++;
        }
    }

    public int getCurrentTurn(){
        return turn;
    }
}