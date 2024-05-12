
public class Main {
    public static void main(String[] args) {
        State initialState = State.getInitialState();
        State targetState = State.getTargetState();
        var statesToTarget = BFS.bfs(initialState, targetState);
        statesToTarget.forEach(State::printBoard);
    }
}
