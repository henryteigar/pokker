package pokker.client;

public class PlayerAI extends Player {
    PlayerAI(String name) {
        super(name);
    }

    @Override
    public int act(int largestBet) {
        if (getStreetBet() < largestBet) {
            System.out.println("He chose to call.");
            return largestBet;
        } else {
            System.out.println("He chose to check.");
            return 0;
        }
    }
}