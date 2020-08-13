package sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        menu(args);
    }

    private static void menu(String[] args) {
        Map<String, String> parameters = parseArgs(args);
        String dataType = parameters.getOrDefault("-dataType", "word");
        String sortingType = parameters.getOrDefault("-sortingType", "natural");
        switch (dataType) {
            case "long":
                processLongs(sortingType);
                break;
            case "line":
                processLines(sortingType);
                break;
            case "word":
            default:
                processWords(sortingType);
        }
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < args.length / 2; i++) {
            result.put(args[2 * i], args[2 * i + 1]);
        }
        if (result.containsKey("-sortingType")) {
            if (result.get("-sortingType").isEmpty()) {
                System.out.println("No sorting type defined!");
            }
        }
        if (result.containsKey("-dataType")) {
            if (result.get("-dataType").isEmpty()) {
                System.out.println("No data type type defined!");
            }
        }
        return result;
    }

//    private static void sortNumbers() {
//        List<Long> numbers = new ArrayList<>();
//        while (scanner.hasNextLong()) {
//            long number = scanner.nextLong();
//            numbers.add(number);
//        }
//        LongSummaryStatistics statistics = numbers.stream().mapToLong(Long::longValue).summaryStatistics();
//        long count = statistics.getCount();
//        System.out.println("Total numbers: " + count);
//        System.out.print("Sorted data:");
//        numbers.stream()
//                .sorted()
//                .forEach(number -> System.out.print(" " + number));
//    }

    private static void processWords(String sortingType) {
        List<String> words = new ArrayList<>();
        scanner.useDelimiter("\\s+");
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        int count = words.size();
//        String longest = words.stream()
//                .max((s1, s2) -> s1.length() > s2.length() ? 1 : s1.length() < s2.length() ? -1 : s1.compareTo(s2)).get();
//        int freq = Collections.frequency(words, longest);
        System.out.printf("Total words: %d.\n", count);
        if ("byCount".equals(sortingType)) {
            words.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
//                    .sorted(Map.Entry.comparingByValue())
                    .sorted(Comparator.comparing((Map.Entry<String, Long> e) -> e.getValue())
                            .thenComparing(Map.Entry::getKey))
                    .forEach(e -> System.out.printf("%s: %d time(s), %d%%\n", e.getKey(), e.getValue(), e.getValue() * 100 / count));
        } else {
            System.out.print("Sorted data:");
            words.stream()
                    .sorted()
                    .forEach(word -> System.out.print(" " + word));
        }
    }

    private static void processLines(String sortingType) {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        int count = lines.size();
//        String longest = lines.stream()
//                .max((s1, s2) -> s1.length() > s2.length() ? 1 : s1.length() < s2.length() ? -1 : s1.compareTo(s2)).get();
//        int freq = Collections.frequency(lines, longest);
        System.out.println("Total lines: " + count + ".");
        if ("byCount".equals(sortingType)) {
            lines.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
//                    .sorted(Map.Entry.comparingByValue())
                    .sorted(Comparator.comparing((Map.Entry<String, Long> e) -> e.getValue())
                            .thenComparing(Map.Entry::getKey))
                    .forEach(e -> System.out.printf("%s: %d time(s), %d%%\n", e.getKey(), e.getValue(), e.getValue() * 100 / count));
        } else {
            System.out.print("Sorted data:");
            lines.stream()
                    .sorted()
                    .forEach(System.out::println);
//        System.out.println("The longest line:");
//        System.out.println(longest);
//        System.out.println("(" + freq + " time(s), " + freq * 100 / count + "%).");
        }
    }

    private static void processLongs(String sortingType) {
        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNextLong()) {
            try {
                long number = scanner.nextLong();
                numbers.add(number);
            } catch (InputMismatchException e) {
                System.out.println("error");
            }
        }
//        LongSummaryStatistics statistics = numbers.stream().mapToLong(Long::longValue).summaryStatistics();
        int count = numbers.size();
        System.out.println("Total numbers: " + count);
        if ("byCount".equals(sortingType)) {
            numbers.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream().sorted(Map.Entry.comparingByValue())
                    .forEach(e -> System.out.printf("%d: %d time(s), %d%%\n", e.getKey(), e.getValue(), e.getValue() * 100 / count));
        } else {
            System.out.print("Sorted data:");
            numbers.stream()
                    .sorted()
                    .forEach(number -> System.out.print(" " + number));
//        System.out.println("The greatest number: " + max + " (" + freq + " time(s)).");
        }
    }
}
