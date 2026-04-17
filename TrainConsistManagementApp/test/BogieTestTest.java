import java.util.*;
import java.util.stream.Collectors;

public class BogieTestTest {
    public static void main(String[] args) {

        // Step 1: Create list of bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("B1", "Sleeper"));
        bogies.add(new Bogie("B2", "AC Chair"));
        bogies.add(new Bogie("B3", "Sleeper"));
        bogies.add(new Bogie("B4", "First Class"));
        bogies.add(new Bogie("B5", "AC Chair"));

        // Step 2: Convert list to stream and group
        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getType));

        // Step 3: Display grouped result
        System.out.println("Grouped Bogies by Type:");
        groupedBogies.forEach((type, list) -> {
            System.out.println(type + " -> " + list);
        });

        // Step 4: Program continues...
    }
}