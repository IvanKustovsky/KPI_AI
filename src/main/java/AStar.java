import java.util.*;

public class AStar {

    public static List<State> aStar(State initialState, State targetState) {
        Queue<Node> openSet = new PriorityQueue<>();
        Map<State, State> predecessorMap = new HashMap<>();
        Map<State, Integer> gScore = new HashMap<>();
        gScore.put(initialState, 0);
        int statesCounter = 0;

        openSet.add(new Node(initialState,0, heuristic(initialState, targetState)));

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll(); // Обирається Node з найменшим значенням fScore
            State currentState = currentNode.getState();
            statesCounter++;

            if (currentState.equals(targetState)) {
                System.out.println("Number of all visited states to achieve target with A* algorithm is " + statesCounter);
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
                            int tentativeGScore = gScore.get(currentState) + 1;
                            if (!gScore.containsKey(newState)) {
                                predecessorMap.put(newState, currentState);
                                gScore.put(newState, tentativeGScore);
                                int heuristic = heuristic(newState, targetState);
                                openSet.add(new Node(newState, tentativeGScore, heuristic));
                            }
                        }
                    }
                }
            }
        }

        System.out.println("No solution found for the given puzzle.");
        return new ArrayList<>();
    }

    // Евристична функція обчислює кількість шашок, які знаходяться не у своїй цільовій клітинці. (hScore)
    private static int heuristic(State state, State targetState) {
        int misplacedCheckers = 0;
        for (char c = 'A'; c <= 'E'; c++) {
            Cell stateCell = state.cellOf(c);
            Cell targetCell = targetState.cellOf(c);
            if (!stateCell.equals(targetCell)) {
                List<Integer> stateStack = stateCell.getStack();
                List<Integer> targetStack = targetCell.getStack();
                for (Integer checker : stateStack) {
                    if (!targetStack.contains(checker)) {
                        misplacedCheckers++;
                    }
                }
                for (Integer checker : targetStack) {
                    if (!stateStack.contains(checker)) {
                        misplacedCheckers++;
                    }
                }
            }
        }
        return misplacedCheckers;
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
