package pokker.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Checker {
    List<Card> allCards;

    Checker(List<Card> cards) {
        this.allCards = cards;
    }

    public boolean isStraightFlush() {
        if (this.isFlush() && this.isStraight()) {
            return true;
        } else {
            return false;
        }
    }

    public String valueStraightFlush() {
        return "I" + this.valueOfStraight().charAt(1);
    }

    public boolean isFlush() {
        int hearts = 0;
        int spades = 0;
        int diamonds = 0;
        int clubs = 0;
        for (Card card : allCards) {
            if (card.getSuit() == CardSuit.HEARTS) {
                hearts += 1;
            } else if (card.getSuit() == CardSuit.CLUBS) {
                clubs += 1;
            } else if (card.getSuit() == CardSuit.DIAMONDS) {
                diamonds += 1;
            } else {
                spades += 1;
            }
        }
        return diamonds > 4 || clubs > 4 || spades > 4 || hearts > 4;
    }

    public String valueFlush() {
        return "F";
    }


    public boolean isStraight() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (valueList.contains(CardValue.ACE) && valueList.contains(CardValue.TWO) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return true;
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.TWO) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return true;
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return true;
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return true;
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.FIVE)) {
            return true;
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return true;
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return true;
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return true;
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.KING) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return true;
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.KING) && valueList.contains(CardValue.ACE) && valueList.contains(CardValue.TEN)) {
            return true;
        } else {
            return false;
        }
    }

    public String valueOfStraight() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.KING) && valueList.contains(CardValue.ACE) && valueList.contains(CardValue.TEN)) {
            return "EM";
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.KING) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return "EL";
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.QUEEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return "EK";
        } else if (valueList.contains(CardValue.JACK) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return "EJ";
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.TEN)) {
            return "EI";
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.NINE) && valueList.contains(CardValue.FIVE)) {
            return "EH";
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.EIGHT) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return "EG";
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.SEVEN) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return "EF";
        } else if (valueList.contains(CardValue.SIX) && valueList.contains(CardValue.TWO) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return "EE";
        } else if (valueList.contains(CardValue.ACE) && valueList.contains(CardValue.TWO) && valueList.contains(CardValue.THREE) && valueList.contains(CardValue.FOUR) && valueList.contains(CardValue.FIVE)) {
            return "ED";
        } else {
            return null;
        }

    }


    public boolean isFour() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.KING) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.JACK) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TEN) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.NINE) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SIX) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.THREE) == 4) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TWO) == 4) {
            return true;
        } else {
            return false;
        }
    }

    public String valueFour() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 4) {
            return "HM";
        } else if (Collections.frequency(valueList, CardValue.KING) == 4) {
            return "HL";
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 4) {
            return "HK";
        } else if (Collections.frequency(valueList, CardValue.JACK) == 4) {
            return "HJ";
        } else if (Collections.frequency(valueList, CardValue.TEN) == 4) {
            return "HI";
        } else if (Collections.frequency(valueList, CardValue.NINE) == 4) {
            return "HH";
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 4) {
            return "HG";
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 4) {
            return "HF";
        } else if (Collections.frequency(valueList, CardValue.SIX) == 4) {
            return "HE";
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 4) {
            return "HD";
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 4) {
            return "HC";
        } else if (Collections.frequency(valueList, CardValue.THREE) == 4) {
            return "HB";
        } else if (Collections.frequency(valueList, CardValue.TWO) == 4) {
            return "HA";
        } else {
            return null;
        }
    }

    public boolean isTriple() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.KING) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.JACK) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TEN) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.NINE) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SIX) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.THREE) == 3) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TWO) == 3) {
            return true;
        } else {
            return false;
        }
    }

    public String valueTriple() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 3) {
            return "DM";
        } else if (Collections.frequency(valueList, CardValue.KING) == 3) {
            return "DL";
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 3) {
            return "DK";
        } else if (Collections.frequency(valueList, CardValue.JACK) == 3) {
            return "DJ";
        } else if (Collections.frequency(valueList, CardValue.TEN) == 3) {
            return "DI";
        } else if (Collections.frequency(valueList, CardValue.NINE) == 3) {
            return "DH";
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 3) {
            return "DG";
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 3) {
            return "DF";
        } else if (Collections.frequency(valueList, CardValue.SIX) == 3) {
            return "DE";
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 3) {
            return "DD";
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 3) {
            return "DC";
        } else if (Collections.frequency(valueList, CardValue.THREE) == 3) {
            return "DB";
        } else if (Collections.frequency(valueList, CardValue.TWO) == 3) {
            return "DA";
        } else {
            return null;
        }
    }

    public boolean isPair() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.KING) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.JACK) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TEN) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.NINE) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.SIX) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.THREE) == 2) {
            return true;
        } else if (Collections.frequency(valueList, CardValue.TWO) == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTwoPair() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        int counter = 0;
        if (Collections.frequency(valueList, CardValue.ACE) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.KING) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.JACK) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.TEN) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.NINE) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.SIX) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.THREE) == 2) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.TWO) == 2) {
            counter += 1;
        }
        if (counter > 1) {
            return true;
        } else {
            return false;
        }
    }

    public String valueTwoPair() {
        List<CardValue> valueList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 2) {
            list.add("M");
        } else if (Collections.frequency(valueList, CardValue.KING) == 2) {
            list.add("L");
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 2) {
            list.add("K");
        } else if (Collections.frequency(valueList, CardValue.JACK) == 2) {
            list.add("J");
        } else if (Collections.frequency(valueList, CardValue.TEN) == 2) {
            list.add("I");
        } else if (Collections.frequency(valueList, CardValue.NINE) == 2) {
            list.add("H");
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 2) {
            list.add("G");
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 2) {
            list.add("F");
        } else if (Collections.frequency(valueList, CardValue.SIX) == 2) {
            list.add("E");
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 2) {
            list.add("D");
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 2) {
            list.add("C");
        } else if (Collections.frequency(valueList, CardValue.THREE) == 2) {
            list.add("B");
        } else if (Collections.frequency(valueList, CardValue.TWO) == 2) {
            list.add("A");
        }
        return "C" + list.get(0) + list.get(1);
    }

    public boolean isTwoTriple() {
        int counter = 0;
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.KING) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.JACK) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.TEN) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.NINE) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.SIX) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.THREE) == 3) {
            counter += 1;
        } else if (Collections.frequency(valueList, CardValue.TWO) == 3) {
            counter += 1;
        }
        if (counter > 1) {
            return true;
        } else {
            return false;
        }
    }

    public String valueTwoTriple() {
        List<CardValue> valueList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 3) {
            list.add("M");
        } else if (Collections.frequency(valueList, CardValue.KING) == 3) {
            list.add("L");
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 3) {
            list.add("K");
        } else if (Collections.frequency(valueList, CardValue.JACK) == 3) {
            list.add("J");
        } else if (Collections.frequency(valueList, CardValue.TEN) == 3) {
            list.add("I");
        } else if (Collections.frequency(valueList, CardValue.NINE) == 3) {
            list.add("H");
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 3) {
            list.add("G");
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 3) {
            list.add("F");
        } else if (Collections.frequency(valueList, CardValue.SIX) == 3) {
            list.add("E");
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 3) {
            list.add("D");
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 3) {
            list.add("C");
        } else if (Collections.frequency(valueList, CardValue.THREE) == 3) {
            list.add("B");
        } else if (Collections.frequency(valueList, CardValue.TWO) == 3) {
            list.add("A");
        }
        return list.get(0) + list.get(1);
    }

    public boolean isHouse() {
        if ((this.isTriple() && this.isPair()) || this.isTwoTriple()) {
            return true;
        } else {
            return false;
        }
    }

    public String valueHouse() {
        if (this.isTriple() && this.isPair()) {
            return "G" + this.valueTriple().charAt(1) + this.valuePair().charAt(1);
        } else {
            return "M" + this.valueTwoTriple();
        }
    }

    public String valuePair() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 2) {
            return "BM";
        } else if (Collections.frequency(valueList, CardValue.KING) == 2) {
            return "BL";
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 2) {
            return "BK";
        } else if (Collections.frequency(valueList, CardValue.JACK) == 2) {
            return "BJ";
        } else if (Collections.frequency(valueList, CardValue.TEN) == 2) {
            return "BI";
        } else if (Collections.frequency(valueList, CardValue.NINE) == 2) {
            return "BH";
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 2) {
            return "BG";
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 2) {
            return "BF";
        } else if (Collections.frequency(valueList, CardValue.SIX) == 2) {
            return "BE";
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 2) {
            return "BD";
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 2) {
            return "BC";
        } else if (Collections.frequency(valueList, CardValue.THREE) == 2) {
            return "BB";
        } else if (Collections.frequency(valueList, CardValue.TWO) == 2) {
            return "BA";
        } else {
            return null;
        }
    }

    public String valueHigh() {
        List<CardValue> valueList = new ArrayList<>();
        for (Card card : allCards) {
            valueList.add(card.getValue());
        }
        if (Collections.frequency(valueList, CardValue.ACE) == 1) {
            return "AM";
        } else if (Collections.frequency(valueList, CardValue.KING) == 1) {
            return "AL";
        } else if (Collections.frequency(valueList, CardValue.QUEEN) == 1) {
            return "AK";
        } else if (Collections.frequency(valueList, CardValue.JACK) == 1) {
            return "AJ";
        } else if (Collections.frequency(valueList, CardValue.TEN) == 1) {
            return "AI";
        } else if (Collections.frequency(valueList, CardValue.NINE) == 1) {
            return "AH";
        } else if (Collections.frequency(valueList, CardValue.EIGHT) == 1) {
            return "AG";
        } else if (Collections.frequency(valueList, CardValue.SEVEN) == 1) {
            return "AF";
        } else if (Collections.frequency(valueList, CardValue.SIX) == 1) {
            return "AE";
        } else if (Collections.frequency(valueList, CardValue.FIVE) == 1) {
            return "AD";
        } else if (Collections.frequency(valueList, CardValue.FOUR) == 1) {
            return "AC";
        } else if (Collections.frequency(valueList, CardValue.THREE) == 1) {
            return "AB";
        } else if (Collections.frequency(valueList, CardValue.TWO) == 1) {
            return "AA";
        } else {
            return null;
        }
    }

    public String returnHand() {
        if (isStraightFlush()) {
            return valueStraightFlush();
        } else if (isFour()) {
            return valueFour();
        } else if (isHouse()) {
            return valueHouse();
        } else if (isFlush()) {
            return valueFlush();
        } else if (isStraight()) {
            return valueOfStraight();
        } else if (isTriple()) {
            return valueTriple();
        } else if (isTwoPair()) {
            return valueTwoPair();
        } else if (isPair()) {
            return valuePair();
        } else {
            return valueHigh();
        }
    }
}


