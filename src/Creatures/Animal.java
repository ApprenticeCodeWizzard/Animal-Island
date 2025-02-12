package Creatures;
import Structure.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Animal extends Creature {
    protected int hunger;
    protected boolean didEat; //Один з параметрів, по якому тварина вирішує, чи переміщатись у нову клітину
    protected boolean isSmall;//Маленькі тварини швидше розмножуються.

    public Animal(Cell cell, Island island) {
        super(cell, island);
        hunger = 1;
        didEat = true;
    }

    public abstract boolean eat();

    public abstract int getMoveDistance();

    public abstract double getNeedToEat();

    public boolean getDidEat(){
        return didEat;
    }

    public void move() {
        if (this instanceof Herbivore) {
            if (this.cell.plants.isEmpty() || !didEat || (!isFemale && !getIsReproduced())) {
                cell = chooseDirection();
            }
        } else if (this instanceof Carnivore) {
            if (!didEat || (!isFemale && !getIsReproduced())) {
                cell = chooseDirection();
            }
        }
    }

    private Cell chooseDirection() {
        Cell tempCell = cell; //Якщо нове місце не знайдено, тварина залишиться на старому.
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for(int i = cell.y - getMoveDistance(); i <= cell.y + getMoveDistance(); i++){
            for(int j = cell.x - getMoveDistance(); j <= cell.x + getMoveDistance(); j++){
                if(i > 0 && i < island.height && j > 0 && j < island.width && !island.cells[i][j].isBusy && island.cells[i][j].allAnimals.get(getName()).size() < getMaximum()){
                        possibleCells.add(island.cells[i][j]); //Формуємо пул клітин, куди тварина може переміститись
                    }
                }
            }
        synchronized (cell.allAnimals.get(getName())) {
        possibleCells.remove(cell); //Видаляємо з нього поточну
        if(!possibleCells.isEmpty()) {
            tempCell = possibleCells.get(rand.nextInt(possibleCells.size())); //Випадково обираємо, куди тварина переміститься
            synchronized (tempCell.allAnimals.get(getName())) {
                tempCell.allAnimals.get(getName()).add(this);
                cell.allAnimals.get(getName()).remove(this);
            }
        }
            System.out.println("In turn " + island.getCurrentTurn() + " " + getName() + " moved from cell [" + cell.y + "][" + cell.x + "] to cell [" + tempCell.y + "][" + tempCell.x + "]");
        }
        if (cell == tempCell){
            System.out.println("In turn " + island.getCurrentTurn() + " " + getName() + " failed to find a new place to live");
        }
        return tempCell;
    }

    public void reproduce() {
        if(cell.allAnimals.get(getName()).size() >= this.getMaximum()){
            return;
        }
        synchronized (cell.allAnimals.get(getName())) {
            Iterator<Animal> iterator = this.cell.allAnimals.get(getName()).iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                if (animal.isFemale && !animal.getIsReproduced()) {
                    this.setReproduced(true);
                    animal.setReproduced(true);
                    cell.allAnimals.get(getName()).add(makeNew(animal.getName(), cell, island));
                    if (isSmall && cell.allAnimals.get(getName()).size() < getMaximum()) {
                        cell.allAnimals.get(getName()).add(makeNew(getName(), cell, island));
                    }
                }
            }
        }
    }

    public static Animal makeNew(String name, Cell cell, Island island){
        if (island.getCurrentTurn() > 0) {
            Statistics.increaseAnimalsBorn();
            System.out.println("In turn " + island.getCurrentTurn() + " a new " + name + " was born in cell [" + cell.y + "][" + cell.x + "]");
        }
        return switch (name) {
            case "Rabbit" -> new Rabbit(cell, island);
            case "Wolf" -> new Wolf(cell, island);
            case "Boa" -> new Boa(cell, island);
            case "Fox" -> new Fox(cell, island);
            case "Bear" -> new Bear(cell, island);
            case "Eagle" -> new Eagle(cell, island);
            case "Horse" -> new Horse(cell, island);
            case "Deer" -> new Deer(cell, island);
            case "Mouse" -> new Mouse(cell, island);
            case "Goat" -> new Goat(cell, island);
            case "Sheep" -> new Sheep(cell, island);
            case "Boar" -> new Boar(cell, island);
            case "Bull" -> new Bull(cell, island);
            case "Duck" -> new Duck(cell, island);
            case "Caterpillar" -> new Caterpillar(cell, island);
            default -> null;
        };
    }
}