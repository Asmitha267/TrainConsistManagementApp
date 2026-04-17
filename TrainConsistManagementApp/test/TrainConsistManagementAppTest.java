import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    private List<Bogie> bogies;

    @BeforeEach
    void setUp() {
        // Initialize with a standard set of bogies before each test
        bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper"));
        bogies.add(new Bogie("AC Chair"));
        bogies.add(new Bogie("Sleeper"));
        bogies.add(new Bogie("First Class"));
        bogies.add(new Bogie("AC Chair"));
    }

    @Test
    void testGrouping_BogiesGroupedByType() {
        // Act
        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Assert
        assertTrue(result.containsKey("Sleeper"), "Map should contain 'Sleeper' key");
        assertTrue(result.containsKey("AC Chair"), "Map should contain 'AC Chair' key");
        assertTrue(result.containsKey("First Class"), "Map should contain 'First Class' key");
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        // Act
        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Assert
        assertEquals(2, result.get("Sleeper").size(), "Sleeper group should have 2 bogies");
        assertEquals(2, result.get("AC Chair").size(), "AC Chair group should have 2 bogies");
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        // Act
        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Assert: Ensure they are separated (3 distinct keys for 5 bogies)
        assertEquals(3, result.keySet().size(), "There should be exactly 3 distinct categories");
    }

    @Test
    void testGrouping_EmptyBogieList() {
        // Arrange
        List<Bogie> emptyList = new ArrayList<>();

        // Act
        Map<String, List<Bogie>> result = emptyList.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Grouping an empty list should return an empty map");
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        // Arrange
        List<Bogie> singleType = List.of(new Bogie("Sleeper"), new Bogie("Sleeper"));

        // Act
        Map<String, List<Bogie>> result = singleType.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        // Arrange
        int originalSize = bogies.size();

        // Act
        bogies.stream().collect(Collectors.groupingBy(Bogie::getType));

        // Assert
        assertEquals(originalSize, bogies.size(), "The original list must remain unchanged after streaming");
    }
}