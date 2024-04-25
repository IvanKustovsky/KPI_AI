
public class Move extends ParityComparator {

    private final Cell cellFrom;
    private final Cell cellTo;

    public Move(Cell cellFrom, Cell cellTo) {
        this.cellFrom = cellFrom;
        this.cellTo = cellTo;
    }


    public void makeMove() {
        if(isValidMove()) {
            cellTo.getStack().push(cellFrom.getStack().pop());
        } else {
            System.out.println("Can't move number " + cellFrom.getStack().peek()
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
