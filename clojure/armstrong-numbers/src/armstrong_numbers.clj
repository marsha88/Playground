(ns armstrong-numbers)

(defn get-digits [n]
  "Given a number n, returns the digits of n in a list"
  (if (= (int n) 0) '(0)
    (loop [i n, digits '()]
      (if (= (int i) 0)
          digits
          (recur (/ i 10) (cons (int (rem i 10)) digits))))))

(defn pow-of [exp n]
  (reduce * (repeat exp n)))

(defn sum [xs] (reduce + xs))

(defn armstrong? [n]
  (let [digits (get-digits n)]
    (= n (sum (map (partial pow-of (count digits)) digits)))))
