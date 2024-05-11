import lombok.Getter;

import java.util.*;

@Getter
public class Board {
    private final Map<Character, Cell> cells = new HashMap<>();

    public Board(){
        initializeCells();
    }

    public static Board copyOf(Board currentBoard){
        Board newBoard = new Board();
        for (var entry : currentBoard.getCells().entrySet()) {
            char cellName = entry.getKey();
            Cell originalCell = entry.getValue();
            Cell copiedCell = new Cell(cellName);
            copiedCell.getStack().addAll(originalCell.getStack());
            newBoard.getCells().put(cellName, copiedCell);
        }
        return newBoard;
    }

    public void initializeCells() {
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

    public void printBoard() {
        System.out.println("---------------------------");
        var entrySet = cells.entrySet();
        for(var entry : entrySet){
            System.out.println(entry.getKey() + ": " + entry.getValue().getStackAsString());
        }
        System.out.println("---------------------------");
    }

    public void makeMove(Move move) {
        move.makeMove();
    }
}