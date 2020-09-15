(ns anagram
  (:require [clojure.string :as s]))

(defn- anagram-for? [word]
  (let [lw (s/lower-case word)]
    (fn [prospect]
      (let [lp (s/lower-case prospect)]
        (if (not= lw lp)
          (= (sort lw) (sort lp)))))))

(defn anagrams-for [word prospect-list]
  (filter (anagram-for? word) prospect-list))

