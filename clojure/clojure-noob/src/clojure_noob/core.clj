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

  (defn greeting "This function likely performs very little" [name]
    (str "hello, " name)
  )

  (println (greeting "clayton"))

  (defn noParams []
    "I take zero parameters"
  )
  (println (noParams))

 (defn first-arity
   ([first] first)
   ([first second] (+ first second))
 )
 (println (first-arity 1))
 (println (first-arity 1 2))

 (defn default-values
   "Arity overloading allows nice support for default values."
   ([name weapon] (str name " chooses " weapon))
   ([name] (default-values name "pistol"))
 )

 (println (default-values "clayton"))
 (println (default-values "clayton" "monkey-wrench"))

;; Using rest parameters
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
    [& whippersnappers]
  (map codger-communication whippersnappers))

(println (codger "hello" "world"))

 (defn restParams
   ([name & more]
     (restParams (str name " and " more))
   )
   ([name]
    (str "Hello, " name)
   )
 )

 (println (restParams "clayton"))
 (println (restParams "clayton" "casey" "cat" "dog"))

 (defn createObj [firstName lastName]
   {:firstName firstName
    :lastName lastName}
 )
 (println (createObj "clayton" "marshall"))
)
