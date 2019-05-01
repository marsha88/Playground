(ns connect-four.core
  (:gen-class))
(def rows 4)
(def cols 4)
(def board
  [["[]" "[]" "[]" "[]"]
   ["[]" "[]" "[]" "[]"]
   ["[]" "[]" "[]" "[]"]
   ["[]" "[]" "[]" "[]"]])

;; connect four game
(defn print-row 
  "prints a seq and appends a newline"
  [row]
  (doseq [x row]
    (print x))
    (println))

(defn print-game-board
  "prints the connect four board using the state"
  []
  (doseq [x board]
    (print-row x)))

(defn -main
  [& args]
  (print-game-board))
