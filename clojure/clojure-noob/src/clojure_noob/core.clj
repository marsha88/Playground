(ns clojure-noob.core
 (:gen-class))

(defn -main
 "I don't do a whole lot ... yet."
 [& args]
 (println (hash-map :a 1 :b 2))
 (println (get {:firstname "clayton" :lastname "marshall"} :lastname "none"))
 (println (get {:firstname "clayton"} :lastname "none"))
 (println (conj [1 2 3] 4))
 (println (conj '(1 2 3) 0))
 (println (nth '(1 2 3) 2))
 (println "Nested map" {:a {:b 2} :b {:c 3}})
 (println (hash-set 1 1 2 2))
 (println #{ 1 2 3 4 })
 (println (conj #{1 2} 2))
 (def mySet #{1 2})
 (def newSet (when (not (contains? mySet 2))
      (conj mySet 2)) )
  (if (nil? newSet) (println "nil, mate") (println newSet))
  (println "Creating a set from vector!")
  (println (set [1 2 3 4 4 5 5]))
  (println ((or + -) 1 2 3))

)
