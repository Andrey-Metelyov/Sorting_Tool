import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int[] numbers = Stream.of(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::valueOf)
                .toArray();
        System.out.println(size - 1);
    }
}