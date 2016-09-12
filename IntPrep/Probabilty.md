# Probability Questions

## Discrete Probability: Dice and Coin Questions
1. When a fair die is cast, probability of getting 3?
	* P(3)/N = 1/6
2. When a fair die is cast, probability of getting an odd number?
	* COUNT(1,3,5)/ COUNT(1,2,3,4,5,6) = 3/6 = 1/2
3. When two fair dies are cast, probability of getting a sum of 6?
	* COUNT((1,5), (5,1), (2,4), (4,2), (3,3)) / (6 * 6) = 5 / 36
4. When two fair dies are cast, probability of getting a sum of 5?
	* COUNT((1,4), (4,1), (2,3), (3,2)) / (6 * 6) = 4 / 36 = 1 / 9
5. When a fair coin is tossed 8 times, probability of 8 heads?
	* 8C0 * (0.5)^8 * (0.5)^0
6. When a fair coin is tossed 8 times, probability of 5 heads?
	* (8 choose 5) * (0.5)^5 * (0.5)^3 
7. When a fair coin is tossed 6 times, probability of at least 4 heads?
	* (6 choose 4) * (0.5)^4 * (0.5)^2 +  (6 choose 5) * (0.5)^5 * (0.5)^1 +  (6 choose 6) * (0.5)^6 * (0.5)^0
8. When a fair coin is tossed 4 times, probability of 3 heads?
	* (4 choose 3) * (0.5)^3 * (0.5)^1
9. When a fair coin is tossed 4 times, probability of at most 2 heads?
	* (4 choose 0) * (0.5)^0 * (0.5)^4 + (4 choose 1) * (0.5)^1 * (0.5)^3 + (4 choose 2) * (0.5)^2 * (0.5)^2 
10. 

## Discrete Probability: Cards
1. In a pack of cards, probability of drawing red suite?
	* (13 * 2) / (13 * 4) = 1/2
2. In a pack of cards, probability of drawing a King?
	* (1 * 4) / 52
3. In a pack of cards, probability of drawing two Kings?
	* 4C2 / 52C2
4. In a pack of cards, probability of drawing a black Jack?
	* 2C2 / 52C2
5. In a pack of cards, probability of drawing an ace or an queen?
	* P(Ace) = 4 / 52, P(Queen) = 4 / 52; P(Ace or Queen) = P(Ace) + P (Queen) - P(Ace n Queen)
	* 4 / 52 + 4 / 52 + 0 = 2 / 13
6. In a pack of cards, probability of second card being an ace if the first card is a king?
	* P(king) = 4 / 52, P(ace) = 4 / 51
	* P (Ace | King) = 4 / 51
7. In a pack of cards, probability of three queens in a row - WITH REPLACEMENT?
	* 4 / 52 * 4 / 52 * 4 / 52 
8. In a pack of cards, probability of getting a royal flush?
	* Royal Flush = {10, Jack, Queen, King, Ace}
	* 4/52 * 4/51 * 4/50 * 4/49 * 4/48
	* Ans: 5/52 * 4/51 * 3/50 * 2/49 * 4/48
9. In a pack of cards, draw 3 cards, probability that all 3 are red?
	* 26/52 * 25/51 * 24/50
10. In a pack of cards, draw 3 cards, probability that none of them is a spade?
	* 39/52 * 38/51 * 37/50
11. In a pack of cards, draw 3 cards, probability that a Club, a Heart and a Diamond is drawn in that order?
	* 13/52 * 13/51 * 13/50
12. In a pack of cards, draw 3 cards, probability that a Club, a Heart and a Diamond is drawn in any order?
	* 6 possible ways to draw the cards
	* 6 * 13/52 * 13/51 * 13/50
13. Are events A and B independent?
	* If P(A and B) = P(A) * P(B) then the events A and B are independent
	* P(Red Ace) = 2 / 52
	* P(Red) = 13/52
	* P(Ace) = 4/52
	* Hence, independent
14. At Ann's 7-years olds party all 20 invited guests are going to get some candy from the fish pond. In 12 of the bags there is an extra chocolate bar.Tina and James are first and second out, what is the probability that they both get a bag with an extra chocolate bar?
	* 12/20 * 11/19 
15. In a deck of cards, probability of drawing a diamond or a 9?
	* P (9) = 4/52
	* P(Diamond) = 13/52
	* P ( 9 or Diamond) = P (9) + P(Diamond) - P (9 n Diamond)
	* 4/52 + 13/52 - 1/52 = 16/52
16. In a deck of cards, what is the probability of each of these Poker hands when 5 cards are dealt?
	* *Single Pair:* (13 choose 1) * (4 choose 2) * (12 choose 3) (4 choose 1)^3 / (52 choose 5) = 0.422569
		* (13 choose 1) Ways of choosing 1 out of 13 numbers; (4 choose 2) Ways of choosing 3 out of the 4 cards
		* (12 choose 3) Ways of choosing 3 out of the remaining 4 numbers; (4 choose 1) ways of picking one out of the four cards
	* *Two Pair:* (13 choose 2) * (4 choose 2) * (4 choose 2) * (11 choose 1) * (4 choose 1) / (52 choose 5) = 0.0475
	* *A Triple:* (13 choose 1) * (4 choose 3) * (12 choose 2) * (4 choose 1)^2 / (52 choose 5) = 0.0211
	* *A Full House (AAABB):* (13 choose 1) * (4 choose 3) (12 choose 1) * (4 choose 3)/(52 choose 5) = 0.001441
	* *Four of A Kind:* (13 choose 1) * (4 choose 4) * (12 choose 1) (4 choose 1) / (52 choose 5) = 0.00024
	* *A Straight:* (4 choose 1)^5 * 10 / (52 choose 5); A Straight: ((4 choose 1)^5 * 10 - (4 choose 1) * 10 - 4 - 4)/ (52 choose 5) 
	* *A Flush:* (4 choose 1) * (13 choose 5) / (52 choose 5); A Flush: ((4 choose 1) * (13 choose 5) - (4 choose 1) * 10 - 4) / (52 choose 5)
	* *A Straight Flush:* ((4 choose 1) * 10 - 4 )/ (52 choose 5)
	* *A Royal Flush:* 4 / (52 choose 5)
17. 

## Other
1. Bobo the amoeba has a 25%, 25%, and 50% chance of producing 0, 1, or 2 offspring, respectively. Each of Bobo’s descendants also have the same probabilities. What is the probability that Bobo’s lineage dies out?
	* WIP 

2. In any 15-minute interval, there is a 20% probability that you will see at least one shooting star. What is the probability that you see at least one shooting star in the period of an hour?
	* 1 - (4 choose 0) (0.25)^0 (0.75)^4 = 0.6835

3. Suppose the average number of lions seen on a 1-day safari is 5. What is the probability that tourists will see fewer than four lions on the next 1-day safari?
	* e^-5 + e^-5 * 5^0 / 0! + e^-5 * 5^1 / 1! + e^-5 * 5^2 / 2! + e^-5 * 5^3 / 3! 

4. How can you generate a random number between 1 - 7 with only a die?
	*  If you get a (6,6), just re-roll the die twice again until you get a non-(6,6).

5. How can you get a fair coin toss if someone hands you a coin that is weighted to come up heads more often than tails?
	* Toss twice - select H when tosses are (H,T); select T when tosses are (T,H)

6. You have an 50-50 mixture of two normal distributions with the same standard deviation. How far apart do the means need to be in order for this distribution to be bimodal?
	* WIP 

7. A certain couple tells you that they have two children, at least one of which is a girl. What is the probability that they have two girls?
	* WIP 1/3

8. You have a group of couples that decide to have children until they have their first girl, after which they stop having children. What is the expected gender ratio of the children that are born? What is the expected number of children each couple will have?
	* WIP 

9. How many ways can you split 12 people into 3 teams of 4?
	* (12 choose 4) * (8 choose 4) * (4 choose 4) / 3! = 5775

10. Your hash function assigns each object to a number between 1:10, each with equal probability. With 10 objects, what is the probability of a hash collision? What is the expected number of hash collisions? What is the expected number of hashes that are unused.
	* WIP  

11. You call 2 UberX’s and 3 Lyfts. If the time that each takes to reach you is IID, what is the probability that all the Lyfts arrive first? What is the probability that all the UberX’s
arrive first?
	* (5 choose 3) * (1/5)^3 * (1/5)^2

12. I write a program should print out all the numbers from 1 to 300, but prints out Fizz instead if the number is divisible by 3, Buzz instead if the number is divisible by 5, and
FizzBuzz if the number is divisible by 3 and 5. What is the total number of numbers that is either Fizzed, Buzzed, or FizzBuzzed?
	* fizzBuzz.py			

13. On a dating site, users can select 5 out of 24 adjectives to describe themselves. A match is declared between two users if they match on at least 4 adjectives. If Alice and Bob randomly pick adjectives, what is the probability that they form a match?
	* Anyone can describe himself/herself in (24 choose 5) ways = 42,504
	* For Bob to be declared a match, he must choose at least 4 adjectives from Alice's list. 
	* ((5 choose 5) + (5 choose 4) * (19 choose 1))/(24 choose 5)

14. A lazy high school senior types up application and envelopes to n different colleges, but puts the applications randomly into the envelopes. What is the expected number of applications that went to the right college
	* Expectation is linear!
	* Let Xi=1 if the ith envelope goes into its envelope and let it equal zero otherwise.  Notice that P(Xi=1)=1/n for all i=1,2,…,ni=1,2,…,n.  
	* So, E(Xi)=1/n for all i=1,2,…,ni=1,2,…,n.
	* Let Y be the total number of correctly stuffed envelopes.
	* Notice that Y=∑ni=1Xi 
	* Then E(Y)=∑ni=1E(Xi)E(Y)=∑i=1nE(Xi) (by linearity).
	* Finally E(Y)=n * 1/n

15. Let’s say you have a very tall father. On average, what would you expect the height of his son to be? Taller, equal, or shorter? What if you had a very short father?
	* Regression to the mean

16. What’s the expected number of coin flips until you get two heads in a row? What’s the expected number of coin flips until you get two tails in a row?
	* Let x be the no. of flips required to get two heads in a row. 
	* In the first toss, if tail appears, then there are x more tosses until two heads appear (1/2 * (x+1))
	* Else if a head appears in the first toss, then we need to evaluate further
		* if tail appears in the second toss, then x more tosses are required until two heads appear (1/2 * (x+2))
		* if a head appears in the second toss, we are done! So, the probability is (1/2 * 2)
	* x =  1/2 * (x + 1) +  1/2 ( 1/2 * (x+2) + 1/2 * 2 ) 
	* x = 6

17. Let’s say we play a game where I keep flipping a coin until I get heads. If the first time I get heads is on the nth coin, then I pay you 2n-1 dollars. How much would you pay me to play this game?
	* Expected no. of tosses to get a H is ?
	* x = 1/2 * (x + 1) + 1/2 * 1
	* x = 2
	* Payout = 2 * 2 -1 = 3; Offer of pay = < 3
18. You have two coins, one of which is fair and comes up heads with a probability 1/2, and the other which is biased and comes up heads with probability 3/4. You randomly pick coin and flip it twice, and get heads both times. What is the probability that you picked the fair coin?
	* H: Get 2 heads
	* F: Fair Coin
	* P (F | H) = P (H | F) * P (F) / P ( H )
	* P ( H | F ) = 1/2 * 1/2
	* P (F) = 1/2
	* P (H) = 1/2 * 1/4 + 1/2 * (3/4 * 3/4)
	* P (F | H ) = 4 / 13
19. You have a 0.1% chance of picking up a coin with both heads, and a 99.9% chance that you pick up a fair coin. You flip your coin and it comes up heads 10 times. What’s the chance that you picked up the fair coin, given the information that you observed?
	* ~1/2
	* 10 Heads: Ten consecutive heads
	* H: Picking coin with two heads
	* F: Picking Fair Coin
	* P (F | 10 Heads) = ?
	* P (F | 10 heads ) = P (10 heads | F) * P (F) / P(10 heads)
	* 1/1024 * 0.999 / ( 1/1024 * 0.999 + 0.001) = ~1/2
20. 