(ns claytn.webscraper
  (:require [hickory.core :refer [parse as-hickory]]
            [hickory.select :as s]))

(def hn-url "https://news.ycombinator.com/")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def raw-html (slurp hn-url))
  (def parsed-html (-> raw-html
                       parse
                       as-hickory))
  (def post-titles (map (fn [tag] (first (get tag :content)))
                        (-> (s/select (s/class "storylink") parsed-html))))
  (def formatted-titles (map-indexed
                          (fn [index post-title]
                            (str (inc index) ": " post-title))
                          post-titles))

    (println
    (clojure.string/join "\n" formatted-titles)))
