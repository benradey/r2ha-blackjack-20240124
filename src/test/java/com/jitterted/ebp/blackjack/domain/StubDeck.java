package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StubDeck implements Deck {
    private static final Suit DUMMY_SUIT = Suit.HEARTS;
    private final ListIterator<Card> iterator;

    public StubDeck(Rank... ranks) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : ranks) {
            cards.add(new Card(DUMMY_SUIT, rank));
        }
        this.iterator = cards.listIterator();
    }

    public StubDeck(List<Card> cards) {
        this.iterator = cards.listIterator();
    }

    public static Deck playerHitsAndGoesBust() {
        return new StubDeck(Rank.TEN, Rank.EIGHT,
                            Rank.QUEEN, Rank.JACK,
                            Rank.THREE);
    }

    public static Deck playerHitsAndDoesNotGoBust() {
        return new StubDeck(Rank.SIX, Rank.EIGHT,
                            Rank.QUEEN, Rank.JACK,
                            Rank.THREE);
    }

    public static Deck playerStandsAndBeatsDealer() {
        return new StubDeck(Rank.TEN, Rank.EIGHT,
                            Rank.QUEEN, Rank.JACK);
    }

    public static Deck playerPushesDealer() {
        return new StubDeck(Rank.TEN, Rank.QUEEN,
                            Rank.NINE, Rank.NINE);
    }

    public static Deck playerDealtBlackjackDealerDoesNotHaveBlackjack() {
        return new StubDeck(Rank.JACK, Rank.NINE,
                            Rank.ACE, Rank.TEN);
    }

    public static Deck playerNotDealtBlackjack() {
        return new StubDeck(Rank.EIGHT, Rank.NINE,
                            Rank.THREE, Rank.EIGHT);
    }

    @Override
    public Card draw() {
        return iterator.next();
    }

    @Override
    public int size() {
        // "crash test": blow up if someone calls this, since we don't expect it to be used during our tests
        throw new UnsupportedOperationException();
    }
}
