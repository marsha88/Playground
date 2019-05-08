(ns cmd-games.core
  (:require [cmd-games.rock-paper-scissors :as cmd]))

(defn select-game
  []
  (println "Select Game:")
  (println "1) Rock-Paper-Scissors")
  (println "...")
  (let [game-selection (read-line)]
    (cond
      (= game-selection "1") (cmd/rock-paper-scissors)
      :else (println "nope."))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (select-game))
