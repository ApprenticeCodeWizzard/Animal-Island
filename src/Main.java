import Structure.*;

public class Main {
    public static void main(String[] args) {
        //Island island = new Island();
        //island.cycle();
        FoodChain foodChain = new FoodChain();
        for (int i = 0; i < FoodChain.eatTable.length; i++){
            System.out.println(FoodChain.animalNames[i] + " weights " + FoodChain.animalMass[i] + " and needs to eat " + FoodChain.foodNeed[i] + " kg.");
        }
        System.out.println(foodChain.getFoodPriority("Fox"));
        System.out.println(foodChain.getFoodPriority("Wolf"));
    }
}