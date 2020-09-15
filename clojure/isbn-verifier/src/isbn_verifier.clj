(ns isbn-verifier)

(defn- isbn-digit [d]
  (if (= \X d)
    10
    (- (int d) (int \0))))

(defn- check-sum [x]
  (if (zero? x)
    false
    (zero? (mod x 11))))

(defn isbn? [isbn]
  (->> (clojure.string/replace isbn "-" "")
       (re-matches #"\d{9}[\d|X]")
       (map isbn-digit)
       (map * (range 10 0 -1))
       (apply +)
       check-sum))
