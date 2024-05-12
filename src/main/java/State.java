import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class State {
    private Cell cellA;
    private Cell cellB;
    private Cell cellC;
    private Cell cellD;
    private Cell cellE;
    private Map<Character, Cell> cells = new HashMap<>();
    public State(Cell cellA, Cell cellB, Cell cellC, Cell cellD, Cell cellE) {
        this.cellA = cellA;
        this.cellB = cellB;
        this.cellC = cellC;
        this.cellD = cellD;
        this.cellE = cellE;
        initializeCells();
    }

    public static State getTargetState() {
        Cell cellA = new Cell('A', List.of(7, 3, 1));
        Cell cellB = new Cell('B', List.of(8, 6, 4, 2));
        Cell cellC = new Cell('C', List.of(5));
        Cell cellD = new Cell('D');
        Cell cellE = new Cell('E');

        return new State(cellA, cellB, cellC, cellD, cellE);
    }

    public static State getInitialState() {
        Cell cellA = new Cell('A');
        Cell cellB = new Cell('B');
        Cell cellC = new Cell('C');
        Cell cellD = new Cell('D');
        Cell cellE = new Cell('E', List.of(8, 7, 6, 5, 4, 3, 2, 1));
        return new State(cellA, cellB, cellC, cellD, cellE);
    }

    public State copy() {
        Cell copiedCellA = new Cell(this.cellA.getCell(), new ArrayList<>(this.cellA.getStack()));
        Cell copiedCellB = new Cell(this.cellB.getCell(), new ArrayList<>(this.cellB.getStack()));
        Cell copiedCellC = new Cell(this.cellC.getCell(), new ArrayList<>(this.cellC.getStack()));
        Cell copiedCellD = new Cell(this.cellD.getCell(), new ArrayList<>(this.cellD.getStack()));
        Cell copiedCellE = new Cell(this.cellE.getCell(), new ArrayList<>(this.cellE.getStack()));

        Map<Character, Cell> copiedCells = new HashMap<>();
        copiedCells.put('A', copiedCellA);
        copiedCells.put('B', copiedCellB);
        copiedCells.put('C', copiedCellC);
        copiedCells.put('D', copiedCellD);
        copiedCells.put('E', copiedCellE);

        return new State(copiedCells);
    }

    public Cell cellOf(char cellName) {
        return cells.get(cellName);
    }

    public State(Map<Character, Cell> cells) {
        this.cells = cells;
        this.cellA = cells.get('A');
        this.cellB = cells.get('B');
        this.cellC = cells.get('C');
        this.cellD = cells.get('D');
        this.cellE = cells.get('E');
    }

    private void initializeCells() {
        cells.put('A', cellA);
        cells.put('B', cellB);
        cells.put('C', cellC);
        cells.put('D', cellD);
        cells.put('E', cellE);
    }

    public void printBoard() {
        System.out.println("---------------------------");
        var entrySet = cells.entrySet();
        for (var entry : entrySet) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getStackAsString());
        }
        System.out.println("---------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State state)) return false;
        return Objects.equals(getCellA().getStack(), state.getCellA().getStack())
                && Objects.equals(getCellB().getStack(), state.getCellB().getStack())
                && Objects.equals(getCellC().getStack(), state.getCellC().getStack())
                && Objects.equals(getCellD().getStack(), state.getCellD().getStack())
                && Objects.equals(getCellE().getStack(), state.getCellE().getStack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellA, cellB, cellC, cellD, cellE);
    }
}
