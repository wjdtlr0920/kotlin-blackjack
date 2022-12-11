package blackjack

import blackjack.domain.Deck
import blackjack.domain.member.Dealer
import blackjack.domain.member.Player
import blackjack.domain.member.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val usersNames = InputView.inputUsersNames()
    val deck = Deck.init()

    val dealer = Dealer.init(deck)
    val players = Players.init(usersNames, deck)
    ResultView.printDrawResults(dealer, players)

    for (player in players.items) {
        player.drawCardUntilWant(deck)
    }
    dealer.drawCardLimited(deck)

    ResultView.printPlayerResults(dealer)
    players.items.forEach { ResultView.printPlayerResults(it) }

    dealer.gameResult(players)
    val gameResult = dealer.gameResult(players)
    ResultView.printGamResult(gameResult)
}

private fun Player.drawCardUntilWant(deck: Deck) {
    while (this.ableMoreDrawCard() && InputView.checkWantDrawMoreCard(this)) {
        this.addCard(deck.draw())
        ResultView.printCardScoreDescription(this)
    }
}

private fun Dealer.drawCardLimited(deck: Deck) {
    while (this.ableMoreDrawCard()) {
        this.addCard(deck.draw())
        ResultView.printDealerDrawCardDescription()
    }
}