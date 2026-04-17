import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a Bogie in the Train Consist.
 */
class Bogie {
    private String type;
    private String category; // e.g., Passenger or Goods

    public Bogie(String type, String category) {
        this.type = type;
        this.category = category;
    }

    public Bogie(String sleeper) {
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("[%s]", type);
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {
        // --- STEP 1: Initialization ---
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", "Passenger"));
        bogies.add(new Bogie("AC Chair", "Passenger"));
        bogies.add(new Bogie("Sleeper", "Passenger"));
        bogies.add(new Bogie("First Class", "Passenger"));
        bogies.add(new Bogie("AC Chair", "Passenger"));
        bogies.add(new Bogie("Rectangular", "Goods"));
        bogies.add(new Bogie("Cylindrical", "Goods"));

        System.out.println("Executing UC9: Grouping Bogies by Type...");
        System.out.println("------------------------------------------");

        // --- STEP 2 & 3: Stream Pipeline & Grouping ---
        // We use Collectors.groupingBy to transform the flat list into a structured Map
        Map<String, List<Bogie>> groupedByType = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // --- STEP 4 & 5: Display Result ---
        System.out.println("Grouped Bogies Report:");
        groupedByType.forEach((type, list) -> {
            System.out.println(String.format("Type: %-12s | Count: %d | Bogies: %s",
                    type, list.size(), list));
        });

        // --- VERIFICATION / TEST CASES ---
        runVerificationTests(bogies, groupedByType);
    }

    /**
     * Simulates the verification logic described in the Test Case Examples.
     */
    private static void runVerificationTests(List<Bogie> original, Map<String, List<Bogie>> grouped) {
        System.out.println("\n--- Verification Results ---");

        // 1. testGrouping_BogiesGroupedByType & testGrouping_MapContainsCorrectKeys
        boolean hasSleeper = grouped.containsKey("Sleeper");
        System.out.println("Test: Map contains 'Sleeper' key? " + (hasSleeper ? "PASS" : "FAIL"));

        // 2. testGrouping_MultipleBogiesInSameGroup & testGrouping_GroupSizeValidation
        long sleeperCount = grouped.getOrDefault("Sleeper", Collections.emptyList()).size();
        System.out.println("Test: Sleeper group count is 2? " + (sleeperCount == 2 ? "PASS" : "FAIL"));

        // 3. testGrouping_DifferentBogieTypes
        System.out.println("Test: Total unique categories identified: " + grouped.keySet().size());

        // 4. testGrouping_OriginalListUnchanged
        System.out.println("Test: Original list size remains 7? " + (original.size() == 7 ? "PASS" : "FAIL"));

        // 5. testGrouping_EmptyBogieList (Logic check)
        Map<String, List<Bogie>> emptyMap = new ArrayList<Bogie>().stream()
                .collect(Collectors.groupingBy(Bogie::getType));
        System.out.println("Test: Empty list produces empty map? " + (emptyMap.isEmpty() ? "PASS" : "FAIL"));
    }
}