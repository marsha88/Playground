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

(defn -main
  [& args]
  (get-coord { :lat 12 :lng -98 }))
