
suite = {
    'club':0
    , 'diamond':1
    , 'heart': 2
    , 'spade':3
}

class Card :
    def __init__(self, in_value, in_suit):
        self.value = in_value
        self.suit = in_suit


class Deck:
    def __init__(self, in_cards):
        self.cards = []

        for card in in_cards:
            self.cards.append(card)

    def getShuffledCards(self):
        return self.cards.shuffle()

    def getCards(self):
        temp = self.cards.copy()
        return temp