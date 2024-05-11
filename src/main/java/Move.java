import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Move extends ParityComparator {
    private Cell cellFrom;
    private Cell cellTo;

    public void makeMove() {
        if(isValidMove()) {
            cellTo.getStack().push(cellFrom.getStack().pop());
        } else {
            System.out.println("Can't move number " + (isCellEmpty(cellFrom) ? "EMPTY" : cellFrom.getStack().peek())
                    + " from " + cellFrom.getCell() + " to " + cellTo.getCell());
        }
    }

    public boolean isValidMove() {
        if (isCellEmpty(cellFrom)) {
            return false;
        } else if (isCellEmpty(cellTo)) {
            return true;
        }
        return isSameParity() && isCellFromIsSmallerThanCellTo();
    }

    private boolean isCellEmpty(Cell cell) {
        return cell.getStack().isEmpty();
    }

    private boolean isSameParity() {
        return compare(cellFrom.getStack().peek(), cellTo.getStack().peek()) == 0;
    }

    private boolean isCellFromIsSmallerThanCellTo() {
        return cellFrom.getStack().peek() < cellTo.getStack().peek();
    }
}
