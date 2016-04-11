package pokker.lib;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table<PlayerT extends Player> {
    @Expose
    private final List<PlayerT> players = new ArrayList<>();
    @Expose
    private final int tableSize;
    @Expose
    private final int bigBlind;
    private final int smallBlind;
    private final Deck deck = new Deck();
    private List<Card> cardsOnTable = new ArrayList<>();
    private int largestBet;
    private int pot;
    private Dealer dealer;
    private BettingRound bettingRound = BettingRound.PREFLOP;
    private List<TableEventListener> eventListeners = new ArrayList<>();

    public Table(int tableSize, int bigBlind) {
        this.tableSize = tableSize;
        this.bigBlind = bigBlind;
        this.smallBlind = bigBlind / 2;
        dealer = new Dealer(this);
    }

    public void playerJoin(PlayerT player) {
        players.add(player);
    }

    public void gameStart() {
        roundStart();
    }

    public void listen(TableEventListener listener) {
        eventListeners.add(listener);
    }

    private void roundStart() {
        if (players.size() < 1) {
            return;
        }
        dealer.shuffleDeck();
        dealer.drawCardsToPlayers();


        // small blind
        players.get(1).setStreetBet(smallBlind);

        // big blind
        players.get(2).setStreetBet(bigBlind);
        largestBet = bigBlind;

        // Street starts with player next to the big blind acting first
        bettingRoundStart(players.get(2));
    }


    private void bettingRoundStart(Player lastPlayerOfBettingRound) {
        // Deal next card/cards when necessary
        dealer.dealNextCards();

        // notify all listeners that a new round started
        for (TableEventListener listener : eventListeners) {
            listener.bettingRoundStarted(bettingRound, cardsOnTable);
        }

        // Assign the first player to act
        int i = players.indexOf(lastPlayerOfBettingRound) + 1;

        Player actingPlayer = players.get(i % players.size());
        while (actingPlayer != lastPlayerOfBettingRound) {

            // kui bet == 0, siis check/fold; kui placeBet > largestBet, siis raise, kui placeBet == largestBet, siis call
            int bet = dealer.askPlayerToAct(actingPlayer);  // kontrolli üle, et bet oli õige. (serveri jaoks)

            if (bet > largestBet) {
                lastPlayerOfBettingRound = actingPlayer;
                largestBet = bet;
            }

            i++;
            actingPlayer = players.get(i % players.size());

        }

        bettingRoundEnd();
    }

    public void setCardsOnTable(List<Card> cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    private void bettingRoundStart() {
        bettingRoundStart(players.get(0));
    }

    private void bettingRoundEnd() {
        for (Player player : players) {
            pot += player.getStreetBet();
            player.resetStreetBet();
            largestBet = 0;
        }

        // notify all listeners that the round ended
        for (TableEventListener eventListener : eventListeners) {
            eventListener.bettingRoundEnded(bettingRound, pot);
        }

        if (bettingRound == BettingRound.RIVER) {
            roundEnd();
        } else {
            bettingRound = bettingRound.next();
            bettingRoundStart();
        }
    }

    private void roundEnd() {
        // Create bestHands list
        List<BestHand> bestHands = new ArrayList<>();
        for (Player player : players) {
            List<Card> playerAndTableCards = new ArrayList(cardsOnTable);
            playerAndTableCards.add(player.getCards()[0]);
            playerAndTableCards.add(player.getCards()[1]);
            Checker checker = new Checker(playerAndTableCards);
            String playerResult = checker.returnHand();//returns code for the hand ("BA" etc)
            bestHands.add(new BestHand(playerResult, player));

        }

        // Sort bestHands and determine the noOfWinners
        Collections.sort(bestHands);
        int noOfWinners = 0;
        String bestValue = bestHands.get(0).getValue();
        for (BestHand playerHand : bestHands) {
            if (playerHand.getValue().equals(bestValue)) {
                noOfWinners = bestHands.indexOf(playerHand) + 1;
            }
        }
        //calculates no of winners
        int winningSum = pot / noOfWinners;
        System.out.println(winningSum);


        for (int i = 0; i < noOfWinners; i++) {
            bestHands.get(i).getPlayer().recieveMoney(winningSum);
        }

        //first of the list becomes last
        Collections.rotate(players, -1);

        //kicks cashless people
        for (Player player : players) {
            if (player.getMoney() < bigBlind) {
                players.remove(player);
            }
        }

        System.out.println("#########-------ROUND ENDED--------#########");

        dealer.clearTableFromCards();

        bettingRound = BettingRound.PREFLOP;
        roundStart();

    }

    public List<PlayerT> getPlayers() {
        return players;
    }

    public int getTableSize() {
        return tableSize;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public int getLargestBet() {
        return largestBet;
    }

    public int getPot() {
        return pot;
    }

    public void addCardToTable(Card card) {
        this.cardsOnTable.add(card);
    }

    public BettingRound getBettingRound() {
        return bettingRound;
    }
}