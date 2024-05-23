
public class Main {
    public static void main(String[] args) {
        State initialState = State.getInitialState();
        State targetState = State.getTargetState();

        long startTime = System.currentTimeMillis();

        //var statesToTarget = BFS.bfs(initialState, targetState);
        var statesToTarget = AStar.aStar(initialState, targetState);

        statesToTarget.forEach(Printer::printState);
        System.out.println("Minimum amount of steps to achieve target: " + statesToTarget.size());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time to execute: " + totalTime + "ms");
    }
}
