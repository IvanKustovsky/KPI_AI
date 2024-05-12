import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

@Getter
@Setter
@RequiredArgsConstructor
public class Cell {
    private final char cell;
    private Stack<Integer> stack = new Stack<>();

    public Cell(char cellName, List<Integer> elements) {
        cell = cellName;
        stack.addAll(elements);
    }

    public String getStackAsString() {
        return stack.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cell).append(": ");
        Stack<Integer> tempStack = new Stack<>();
        tempStack.addAll(stack);
        while (!tempStack.empty()) {
            stringBuilder.append(tempStack.pop()).append(", ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell1)) return false;
        return getCell() == cell1.getCell()
                && Objects.equals(getStack(), cell1.getStack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCell(), getStack());
    }
}
