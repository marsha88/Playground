(ns poker.core
  (:require [clojure.core.match :refer [match]]))

(def face->rank {\A 14 \K 13 \Q 12 \J 11 \T 10})
(defn rank [card]
  (let [[^Character rank _] card]
    (if (Character/isDigit rank)
      (Integer/valueOf (str rank))
      (face->rank rank))))

(defn suit [card]
  (let [[_ suit] card]
    (str suit)))

(defn- rank-freq
  "Returns the frequency of each rank in a given hand"
  [hand]
  (->> hand
       (map rank)
       frequencies
       vals))

(defn- max-freq [hand]
  (apply max (rank-freq hand)))

(defn pair? [hand]
  (= 2 (max-freq hand)))

(defn three-of-a-kind? [hand]
  (= 3 (max-freq hand)))

(defn four-of-a-kind? [hand]
  (= 4 (max-freq hand)))

(defn flush? [hand]
  (let [suit-freq (frequencies (map suit hand))]
    (= 5 (apply max (vals suit-freq)))))

(defn full-house? [hand]
  (match (rank-freq hand)
         [2 3] true
         [3 2] true
         :else false))

(defn two-pairs? [hand]
  (let [hand-freqs (rank-freq hand)
        pairs (filter #(= 2 %) hand-freqs)]
    (= 2 (count pairs))))
    

(defn straight? [hand]
  (let [straight-values? (fn [v] (= v (range (apply min v) (+ (apply min v) 5))))
        ace-high (map rank hand)
        ace-low (replace {14 1} ace-high)]
    (or
      (straight-values? (sort ace-high))
      (straight-values? (sort ace-low)))))

(defn straight-flush? [hand]
  (and 
    (flush? hand)
    (straight? hand)))

(defn value [hand]
  (cond 
    (straight-flush? hand)  8
    (four-of-a-kind? hand)  7
    (full-house? hand)      6
    (flush? hand)           5
    (straight? hand)        4
    (three-of-a-kind? hand) 3    
    (two-pairs? hand)       2
    (pair? hand)            1
    :else                   0))
