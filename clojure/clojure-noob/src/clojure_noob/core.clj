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

(defn mapper
  "implementing map with reduce"
  [f ls]
  (reverse (reduce (fn [acc e] (cons (f e) acc)) '() ls)))

(defn filterer
  "implementing filter with reduce"
  [f ls]
  (reduce (fn [acc e]
                (if (f e) (cons e acc) acc)) '() ls))

(def data-base {
  0 "clayton"
  1 "matt"
  2 "tanner"
  3 "max" })

(defn data-base-seek [key]
  (Thread/sleep 500)
  (data-base key))

(def randos (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(defn my-conj [target & items]
  (into target items))

(defn my-into [target additions]
  (apply conj target additions))

(defn complement' [f]
  (fn [& args] (not (apply f args))))

(defn fact' [n]
    (if (<= n 1) count
        (loop [i n count 1] ;; count maintains the total factorial
          (if (<= i 1) count
              (recur (dec i) (*' i count))))))

(defn fizzbuzz []
  (loop [i 1]
    (if (<= i 100)
        (do
            (let [three (= 0 (rem i 3)), five (= 0 (rem i 5))]
                (if (and three five)
                    (println "FizzBuzz")
                    (if three
                        (println "Fizz")
                        (if five
                            (println "Buzz")
                            (println i)))))
            (recur (inc i))))))

(defn map-rows [rows keys]
  )

(defn csv-parse
  [filename & rows]
  (let [contents (slurp filename)]
    (map-rows (map (fn [row] (clojure.string/split row #",")) (clojure.string/split contents #"\n")))))

(defn -main
  [& args]
  (get-coord { :lat 12 :lng -98 }))
