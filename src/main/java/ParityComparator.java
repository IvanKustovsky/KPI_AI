import java.util.Comparator;

public class ParityComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer num1, Integer num2) {
        return Integer.compare(num1 % 2, num2 % 2);
    }
}
