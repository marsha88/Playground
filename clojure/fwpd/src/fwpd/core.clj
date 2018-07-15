(ns fwpd.core)
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions { :name identity, :glitter-index str->int })

(defn convert [vamp-key val]
  ((conversions vamp-key) val))

(defn parse [string]
  (map #(clojure.string/split % #",")
    (clojure.string/split string #"\n")))

(defn mapify [rows]
  (map (fn [unmapped-row]
          (reduce (fn [row-map [vamp-key value]]
                        (assoc row-map vamp-key (convert vamp-key value)))
                  {}
                  (map vector vamp-keys unmapped-row)))
        rows))

(def vamp-records (mapify (parse (slurp filename))))
