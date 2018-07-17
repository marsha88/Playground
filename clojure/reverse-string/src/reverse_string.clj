(ns reverse-string)

(defn flip [f]
  (fn [y x] (f x y)))

(defn reverse-string' [s]
  (reduce (flip str) s))
