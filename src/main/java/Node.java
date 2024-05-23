
import lombok.Getter;

@Getter
class Node implements Comparable<Node>{
    private final State state;
    private final int fScore; // фактична вартість оптимального шляху від початкового стану до цільового.
    private final int gScore; // вартість найкоротшого шляху від початкового до поточного.

    public Node(State state, int gScore, int heuristicScore) {
        this.state = state;
        this.gScore = gScore;
        this.fScore = heuristicScore + gScore;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fScore, other.fScore);
    }
}
