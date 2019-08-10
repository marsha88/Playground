(defn tmap [transform]
  (fn [append]
    (fn [acc cur]
      (append acc (transform cur)))))

(defn tfilter [predicate]
  (fn [append]
    (fn [acc cur]
      (if (predicate cur)
        (append acc cur)
        acc))))

(defn greet [{name :name}]
  (str "Hello, I'm " name))

(defn bigDragon [{size :size}]
  (> size 10))

(def pipeline (comp (tfilter bigDragon) (tmap greet)))

(def dragons '({:name "randall" :size 10}
               {:name "calvin" :size 12}
               {:name "fito" :size 20}
               {:name "butch" :size 8}))


(def bigDragonGreetings
  (reduce (pipeline conj) '() dragons))
