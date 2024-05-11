import java.util.*;
public class StateSpaceSearch {

    private final Board initialBoard;
    private final List<Move> moves;

    public StateSpaceSearch(Board initialBoard, List<Move> moves) {
        this.initialBoard = initialBoard;
        this.moves = moves;
    }

    public void depthFirstSearch() {
        depthFirstSearch(initialBoard, new HashSet<>());
    }

    private void depthFirstSearch(Board currentBoard, Set<Board> visited) {
        visited.add(currentBoard);
        currentBoard.printBoard(); // Виведення поточного стану дошки

        // Перевірка, чи досягнуто цільового стану
        if (isGoalState(currentBoard)) {
            System.out.println("Goal state reached!");
            return;
        }

        // Генерування можливих ходів
        List<Move> possibleMoves = generatePossibleMoves(currentBoard);

        // Перехід до нового стану
        for (Move move : possibleMoves) {
            Board newBoard = applyMove(currentBoard, move);

            // Перевірка, чи цей стан вже був відвіданий
            if (!visited.contains(newBoard)) {
                depthFirstSearch(newBoard, visited);
            }
        }
    }

    private boolean isGoalState(Board board) {
        // Додайте вашу перевірку тут
        return board.getCells().get('A').getStack().containsAll(List.of(1, 3, 7)) &&
                board.getCells().get('B').getStack().containsAll(List.of(2, 4, 6, 8));
    }

    private List<Move> generatePossibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        // Додайте ваш код для генерації можливих ходів тут
        return possibleMoves;
    }

    private Board applyMove(Board currentBoard, Move move) {
        Board newBoard = Board.copyOf(currentBoard);
        newBoard.makeMove(move);
        return newBoard;
    }
}
