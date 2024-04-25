import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Character, Cell> cells;
    public Board() {
        cells = new HashMap<>();
        initializeCells();
    }

    private void initializeCells() {
        cells.put('A', new Cell('A'));
        cells.put('B', new Cell('B'));
        cells.put('C', new Cell('C'));
        cells.put('D', new Cell('D'));
        cells.put('E', new Cell('E'));
        fillInitialCell(cells.get('E'));
    }

    private void fillInitialCell(Cell cellToFill) {
        cellToFill.getStack().addAll(List.of(8, 7, 6, 5, 4, 3, 2, 1));
    }

    public void makeMoves() {
        makeMove(new Move(cells.get('E'), cells.get('A')));
        makeMove(new Move(cells.get('E'), cells.get('B')));
        makeMove(new Move(cells.get('E'), cells.get('C')));
        makeMove(new Move(cells.get('A'), cells.get('C')));
    }
    public void makeMove(Move move) {
        move.makeMove();
    }

    public void printBoard() {
        System.out.println("---------------------------");
        var entrySet = cells.entrySet();
        for(var entry : entrySet){
            System.out.println(entry.getKey() + ": " + entry.getValue().getStackAsString());
        }
        System.out.println("---------------------------");
    }
}