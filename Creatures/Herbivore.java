package Creatures;
import Structure.*;

import java.util.Iterator;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell, Island island) {
        super(cell, island);
    }

    public boolean eat(){
        double eatenKg = 0;
        synchronized (cell.plants) {
            Iterator<Plant> iterator = this.cell.plants.iterator();
            while (iterator.hasNext()) {
                Plant plant = iterator.next();
                iterator.remove();
                eatenKg = eatenKg + plant.getMass();
                if (eatenKg >= getNeedToEat()) {
                    this.hunger = this.hunger - 1;
                    break;
                }
            }
        }
        if (eatenKg < getNeedToEat()){
            this.hunger++;
        }
        if(eatenKg <= 0){
            this.hunger++; //Якщо взагалі нічого не поїв, голоду більше.
        }
        if (this.hunger < 0){
            this.hunger = 0;
        }
        if (hunger >= 10) {
            return true;
            }else {
            return false;
        }
    }
}