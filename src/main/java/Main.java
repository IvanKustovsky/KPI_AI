
import java.util.*;

public class Main {
    public static void main(String[] args) {
        State initialState = getInitialState();
        State targetState = getTargetState();
        List<State> states = bfs(initialState, targetState);
    }

    private static List<State> bfs(State initialState, State targetState) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new LinkedHashSet<>();

        queue.add(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            if (currentState.equals(targetState)) {
                return reconstructPath(currentState, visited);
            }

            // Generate all valid moves from current state
            for (char from = 'E'; from >= 'A'; from--) {
                for (char to = 'A'; to <= 'E'; to++) {
                    if (from != to) {
                        State newState = currentState.copy();
                        Cell cellFrom = newState.cellOf(from);
                        Cell cellTo = newState.cellOf(to);
                        Move move = new Move(cellFrom, cellTo);
                        if (move.isValidMove()) {
                            move.makeMove();
                            if (!visited.contains(newState)) {
                                visited.add(newState);
                                queue.add(newState);
                                currentState = newState;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("No solution found for the given puzzle.");
        //printAllPaths(visited);
        return new ArrayList<>();
    }

    private static void printAllPaths(Set<State> visited) {
        for (State state : visited) {
            if(state.getCellA().getStack().size() > 2 && state.getCellA().getStack().pop() == 1
                    && state.getCellA().getStack().pop() == 3 && state.getCellA().getStack().pop() == 7) {
                state.printBoard();;
            }
        }
    }

    // Helper method to reconstruct the path from target state back to initial state
    private static List<State> reconstructPath(State targetState, Set<State> visited) {
        for (State state : visited) {
            state.printBoard();
            if(state == targetState) {
                break;
            }
        }
        List<State> path = new ArrayList<>();
        /*State current = targetState.copy(); // Створюємо копію цільового стану
        while (current != null) {
            path.add(current);
            boolean foundParent = false;
            for (State parent : visited) {
                if (current.equals(parent)) {
                    current = parent.copy(); // Створюємо копію батька
                    foundParent = true;
                    break;
                }
            }
            if (!foundParent) {
                break; // Якщо батько не знайдений, виходимо з циклу
            }
        }*/
        return path;
    }

    private static State getTargetState() {
        Cell cellA = new Cell('A', List.of(7, 3, 1));
        Cell cellB = new Cell('B', List.of(8, 6, 4, 2));
        Cell cellC = new Cell('C', List.of(5));
        Cell cellD = new Cell('D');
        Cell cellE = new Cell('E');
        return new State(cellA, cellB, cellC, cellD, cellE);
    }
    private static State getInitialState() {
        Cell cellA = new Cell('A');
        Cell cellB = new Cell('B');
        Cell cellC = new Cell('C');
        Cell cellD = new Cell('D');
        Cell cellE = new Cell('E', List.of(8, 7, 6, 5, 4, 3, 2, 1));
        return new State(cellA, cellB, cellC, cellD, cellE);
    }

}
