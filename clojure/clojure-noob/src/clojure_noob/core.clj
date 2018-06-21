(ns clojure-noob.core
  (:gen-class))

(defn speak
  ([] "default saying")
  ([saying] (str "hello, I'm " saying)))

(defn nums
  [& nums]
  (map inc nums))

(defn head' [[head & tail]] head)

(defn get-coord
  [{ :keys [lng lat] }]
  (println (str "latitude = " lat " longitude = " lng)))

;; Clojure for the Brave and True Exercises

(def a-hash-map (hash-map :first "Clayton" :last "Marshall"))
(def a-vector (vector 1 2 "3" 4 { :greeting "hello" }))

(defn greet
  "function greets a user"
  [name]
  (str "Hello, " name ". It's nice to meet you."))

(def add100 (partial + 100))

(defn dec-maker
  [subtrahend]
  (fn [minuend] (- minuend subtrahend)))

(defn mapset
  [f v]
  (into #{} (map f v)))

(defn into'
  "reimplementation of the into function"
  [s1 s2]
  (if (empty? s2) s1
    (into' (conj s1 (first s2)) (rest s2))))

(defn -main
  [& args]
  (get-coord { :lat 12 :lng -98 }))
