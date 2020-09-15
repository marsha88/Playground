(ns word-count)

(defn word-count [s]
  (->> (clojure.string/lower-case s)
       (re-seq #"\w+")
       frequencies))
