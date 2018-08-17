(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as s])
  (:refer-clojure :exclude [min max]))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks
            (map
              (fn [k] (apply comparison-fn (map k maps)))
              ks))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))
(def max (comparator-over-maps clojure.core/max [:lat :lng]))

(defn translate-to-00
  [locations]
  (let [mincoords (min locations)]
    (map (fn [coord] (merge-with - coord mincoords)) locations)))

(defn scale
  [width height locations]
  (let [maxcoords (max locations)
        ratio {:lat (/ height (:lat maxcoords))
               :lng (/height (:lng maxcoords))}]
        (map (fn [coord] (merge-with * coord ratio)) locations)))

(defn latlng->point
  "Convert lat/lng map to comma-separated string"
  [{lng :lng lat :lat}]
  (str lng "," lat))

(defn points
  [locations]
  (s/join " " (map latlng->point locations)))
