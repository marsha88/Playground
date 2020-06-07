(ns worksheet.cards)

(def suits ["♠" "♥" "♦" "♣"])
(def values ["A" "2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K"])

(defn get-standard-deck []
  (for [v values
        s suits]
      {:value v :suit s}))

(defn pick-card [deck]
  (let [rand-index (rand-int (count deck))]
    (get deck rand-index)))





