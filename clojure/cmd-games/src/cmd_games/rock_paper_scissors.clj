(ns cmd-games.rock-paper-scissors)

(defn- get-guess
  []
  (println "Play your hand: (r)ock, (p)aper, (s)cissors")
  (let [guess (read-line)]
    (if (contains? #{"r", "p", "s"} guess) guess)))

(defn- winner
  [guess1 guess2]
  (let [guesses [guess1 guess2]]
    (cond
      (= guess1 guess2) 0
      (= guesses ["r", "p"]) 2
      (= guesses ["p", "r"]) 1
      (= guesses ["r", "s"]) 1
      (= guesses ["s", "r"]) 2
      (= guesses ["p", "s"]) 2
      (= guesses ["s", "p"]) 1)))

(defn- play-hand
  []
  (let [computer-guess (rand-nth ["r", "p", "s"])
        player-guess (get-guess)
        winner (winner player-guess computer-guess)]
    (println "The computer guessed: " computer-guess)
    (println "You guessed: " player-guess)
    (cond
      (= player-guess nil) (println "You guess was invalid")
      (= winner 0) (println "Game tied")
      (= winner 1) (println "You won!")
      (= winner 2) (println "Computer won :(")
      :else (println "Error: winner calculation failed"))))

(defn rock-paper-scissors
  []
  (loop []
    (play-hand)
    (recur)))