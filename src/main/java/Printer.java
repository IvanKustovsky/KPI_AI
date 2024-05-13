public class Printer {
    public static void printState(State state) {
        System.out.println("---------------------------");
        var entrySet = state.getCells().entrySet();
        for (var entry : entrySet) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getStackAsString());
        }
        System.out.println("---------------------------");
    }
}
