package Creatures;
import Structure.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Animal extends Creature implements Cloneable{
    protected int hunger;
    protected boolean didEat; //Один з параметрів, по якому тварина вирішує, чи переміщатись у нову клітину
    protected boolean isSmall;//Маленькі тварини швидше розмножуються.
    public boolean toDelete;

    public Animal(Cell cell, Island island) {
        super(cell, island);
        hunger = 1;
        didEat = true;
        toDelete = false;
    }

    public abstract boolean eat();

    public abstract int getMoveDistance();

    public abstract double getNeedToEat();

    public boolean getDidEat(){
        return didEat;
    }

    public void move() {
        if (didEat && (isFemale || getIsReproduced())) {
            return;
        }
        Cell tempCell = cell; //Якщо нове місце не знайдено, тварина залишиться на старому.
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for (int i = cell.y - getMoveDistance(); i <= cell.y + getMoveDistance(); i++) {
            for (int j = cell.x - getMoveDistance(); j <= cell.x + getMoveDistance(); j++) {
                if (i > 0 && i < island.height && j > 0 && j < island.width && island.cells[i][j].allAnimals.get(getName()).size() + island.cells[i][j].newAnimals.get(getName()).size() < getMaximum()) {
                    possibleCells.add(island.cells[i][j]); //Формуємо пул клітин, куди тварина може переміститись
                }
            }
        }
        possibleCells.remove(cell); //Видаляємо з нього поточну
        if (!possibleCells.isEmpty()) {
            tempCell = possibleCells.get(rand.nextInt(possibleCells.size())); //Випадково обираємо, куди тварина переміститься
            synchronized (tempCell.newAnimals.get(getName())) {
                cell = tempCell;
                tempCell.newAnimals.get(getName()).add(this);
                //cell.allAnimals.get(getName()).remove(this);
                toDelete = true;
            }
            if (cell == tempCell) {
                System.out.println("In turn " + island.getCurrentTurn() + " " + getName() + " failed to find a new place to live");
            } else {
                System.out.println("In turn " + island.getCurrentTurn() + " " + getName() + " moved from cell [" + cell.y + "][" + cell.x + "] to cell [" + tempCell.y + "][" + tempCell.x + "]");
            }
        }
    }

    public void reproduce() {
        ArrayList<Animal> animals = cell.allAnimals.get(getName());
        if(animals.size() + cell.newAnimals.get(getName()).size() >= this.getMaximum() || animals.isEmpty()){
            return;
        }
            Iterator<Animal> iterator = animals.iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                if (animal.isFemale && !animal.getIsReproduced()) {
                    this.setReproduced(true);
                    animal.setReproduced(true);
                    cell.newAnimals.get(getName()).add(makeNew(animal.getName(), cell, island));
                    if (isSmall && animals.size() + cell.newAnimals.get(getName()).size() < getMaximum()) {
                        cell.newAnimals.get(getName()).add(makeNew(getName(), cell, island));
                    }
                break;
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