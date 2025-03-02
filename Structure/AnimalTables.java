package Structure;
import java.util.*;
import java.util.HashMap;

public class AnimalTables {
    public static String[] animalNames = {"Wolf", "Boa", "Fox", "Bear", "Eagle", "Horse", "Deer", "Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Bull", "Duck", "Caterpillar"};
    public static String[] carnivores = {"Wolf", "Boa", "Fox", "Bear", "Eagle", "Boar"};

    public static final int[][] eatProbabilityTable = new int[][]{ //Таблиця вірогідності хто кого зїсть
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0},
            {0, 0, 15, 0, 0, 0, 0, 30, 50, 0, 0, 0, 0, 10, 0},
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40},
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0},
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    public static double[] animalMass = {50, 15, 8, 500, 6, 400, 300, 2, 0.05, 60, 70, 400, 700, 1, 0.01, 1};
    public static double[] foodNeed = {8, 2, 2, 80, 1, 60, 50, 0.45, 0.01, 10, 15, 50, 100, 0.15, 0, 0};
    public static double[] maxOfEach = {30, 30, 30, 5, 20, 20, 20, 150, 500, 140, 140, 50, 10, 200, 1000};

    public static final HashMap<String, LinkedHashMap<String, Integer>> foodPriorityTable = getFoodPriority();

    public static HashMap<String, LinkedHashMap<String, Integer>> getFoodPriority() { //Ця функція видає для кожного хижака таблицю пріорітетності полювання.
        HashMap<String, LinkedHashMap<String, Integer>> result = new HashMap<>();
        for (String animalType : carnivores) {
            int carnivoreIndex = Arrays.asList(animalNames).indexOf(animalType);
            int indexOfFood;
            LinkedHashMap<String, Integer> tempMap = new LinkedHashMap<>();
            for (int i = 0; i < animalNames.length; i++) {
                tempMap.put(animalNames[i], eatProbabilityTable[carnivoreIndex][i]);
            }
            tempMap = sortByValue(tempMap, false);
            LinkedHashMap<String, Integer> priorityMap = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
                indexOfFood = Arrays.asList(animalNames).indexOf(entry.getKey());
                if (entry.getValue() > 25 && foodNeed[carnivoreIndex] <= animalMass[indexOfFood]) {
                    priorityMap.put(entry.getKey(), entry.getValue()); //Спочатку додаємо тварин, якими хижак повністю наїсться і вполює хоча б з шансом 1/4.
                    tempMap.remove(entry); //Щоб не було повторів
                }
            }
            for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
                if (entry.getValue() > 0) { //Потім додаємо неоптимальні цілі для полювання, на які тварина полюватиме якщо немає альтернатив.
                    priorityMap.put(entry.getKey(), entry.getValue());
                }
            }
            result.put(animalType, priorityMap);
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(LinkedHashMap<K, V> map, boolean ascending) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> {
            if (ascending) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}