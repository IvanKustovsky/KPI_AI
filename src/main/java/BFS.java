import java.util.*;

public class BFS {

    public static List<State> bfs(State initialState, State targetState) {
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
        return new ArrayList<>();
    }

    private static List<State> reconstructPath(State targetState, Set<State> visited) {
        List<State> path = new ArrayList<>();
        int stepsCounter = 0;
        for (State state : visited) {
            stepsCounter++;
            path.add(state);
            if (state == targetState) {
                System.out.println(stepsCounter);
                break;
            }
        }
        return path;
    }
}
