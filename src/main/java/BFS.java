import java.util.*;

public class BFS {

    public static List<State> bfs(State initialState, State targetState) {
        Queue<State> queue = new LinkedList<>();
        Map<State, State> predecessorMap = new HashMap<>();

        queue.add(initialState);
        predecessorMap.put(initialState, null);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            if (currentState.equals(targetState)) {
                return reconstructPath(currentState, predecessorMap);
            }

            // Generate all valid moves from the current state
            for (char from = 'E'; from >= 'A'; from--) {
                for (char to = 'A'; to <= 'E'; to++) {
                    if (from != to) {
                        State newState = currentState.copy();
                        Cell cellFrom = newState.cellOf(from);
                        Cell cellTo = newState.cellOf(to);
                        Move move = new Move(cellFrom, cellTo);
                        if (move.isValidMove()) {
                            move.makeMove();
                            if (!predecessorMap.containsKey(newState)) {
                                predecessorMap.put(newState, currentState);
                                queue.add(newState);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("No solution found for the given puzzle.");
        return new ArrayList<>();
    }

    private static List<State> reconstructPath(State targetState, Map<State, State> predecessorMap) {
        List<State> path = new ArrayList<>();
        State currentState = targetState;
        while (currentState != null) {
            path.add(currentState);
            currentState = predecessorMap.get(currentState);
        }
        Collections.reverse(path);
        return path;
    }
}
