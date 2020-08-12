import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int sequences = Integer.parseInt(scanner.nextLine());
        int[][] numbers = new int[sequences][];
        for (int i = 0; i < sequences; i++) {
            int[] sequence = Stream.of(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            numbers[i] = Arrays.copyOfRange(sequence, 1, sequence.length);
        }
        int[] result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = merge(result, numbers[i]);
        }
        for (int i : result) {
            System.out.print(i + " ");
        }
//        System.out.println(size - 1);
    }

    private static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < first.length && j < second.length) {
            if (first[i] >= second[j]) {
                result[k] = first[i];
                i++;
            } else {
                result[k] = second[j];
                j++;
            }
            k++;
        }

        for (; i < first.length; i++, k++) {
            result[k] = first[i];
        }

        for (; j < second.length; j++, k++) {
            result[k] = second[j];
        }

        return result;
    }
}