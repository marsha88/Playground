(ns run-length-encoding)

(defn run-length-encode [plain-text]
  (->> plain-text
       (partition-by identity)
       (mapcat (juxt count first))
       (remove #{1})
       (apply str)))

(defn- expand [[_ n letter]]
  (if (nil? n)
    letter
    (repeat (Integer/parseInt n) letter)))

(defn run-length-decode [text]
  (->> text
       (re-seq #"(\d+)?(\D)")
       (mapcat expand)
       (apply str)))