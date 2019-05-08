(ns clojure-by-example.core
  (:gen-class))

(def ident (fn [x] x))

(defn fact 
  [n]
  (if (<= n 0)
    1
    (loop [itr n acc 1]
      (if (<= itr 1)
          acc
          (recur (dec itr) (* acc itr))))))

(defn fizzbuzz
  []
  (loop [i 1]
    (if (<= i 100)
      (let [tri (= 0 (rem i 3))
            fiv (= 0 (rem i 5))]
            (cond
              (and tri fiv) (println "FizzBuzz")
              tri (println "Fizz") 
              fiv (println "Buzz")
              :else (println i))
              (recur (inc i))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (fizzbuzz))


