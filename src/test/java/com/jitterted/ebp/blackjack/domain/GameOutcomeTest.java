package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerHitsAndGoesBust());

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerStandsAndBeatsDealer());

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    void playerDealtHandWithSameValueAsDealerThenPlayerPushesDealer() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerPushesDealer());

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_PUSHES_DEALER);
    }

    @Test
    void playerDealtBlackjackUponInitialDealAndDealerNotDealtBlackjackThenPlayerWinsBlackjack() {
        Game game = new Game(new StubDeck(Rank.JACK, Rank.NINE,
                                          Rank.ACE, Rank.TEN));

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }

    // Retargeted: move closer to the behavior we're testing
    // because we're now testing "is this hand Blackjack" at a DISTANCE
    @Test
    void playerWithHandValueOf21And3CardsIsNotBlackjack() {
        Game game = createGameAndDoInitialDeal(new StubDeck(Rank.JACK, Rank.NINE,
                                                            Rank.EIGHT, Rank.TEN,
                                                            Rank.THREE));

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

// encapsulated setup code:

    private static Game createGameAndDoInitialDeal(Deck deck) {
        Game game = new Game(deck);
        game.initialDeal();
        return game;
    }

}