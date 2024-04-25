import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter
@Setter
public class Cell {

    private final char cell;
    private final Stack<Integer> stack;

    public Cell(char cellName) {
        stack = new Stack<>();
        cell = cellName;
    }
    public String getStackAsString() {
        return stack.toString(); // Повертає рядок з вмістом стеку
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cell).append(": ");
        while (!stack.empty()) {
            stringBuilder.append(stack.pop()).append(", ");
        }
        return stringBuilder.toString();
    }
}
