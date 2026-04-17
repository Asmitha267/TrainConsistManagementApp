import java.util.*;

class Bogie {
    String type;
    int capacity;

    Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

public class UC10_TotalSeats {
    public static void main(String[] args) {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 40)
        );

        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalSeats);
    }
}