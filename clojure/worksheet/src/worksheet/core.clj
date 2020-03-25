(ns worksheet.core
  (:require [clojure.string :as s])
  (:import [java.net URLEncoder])
  (:use [worksheet.foo]))

(defn foo
  "I don't do a whole lot."
  [x]
  (woof))

(def search-engines ["google" "bing"])

(defn create-search-url [search-engine search-string]
  (let [query-string (URLEncoder/encode search-string)]
    (str "http://www." search-engine ".com/search?q=" query-string)))

(defn search [search-string]
  (let [p (promise)]
    (doseq [search-engine search-engines]
      (future
        (let [html (slurp (create-search-url search-engine search-string))]
          (deliver p {:engine search-engine :html html}))))
    @p))


(def random-quote-url "https://www.braveclojure.com/random-quote")

(defn get-raw-quote [] (slurp random-quote-url))

(defn parse-quote [raw-quote]
  (-> raw-quote
      (s/split #"--")
      first
      clojure.string/trim))

(defn get-quote []
  (-> (get-raw-quote) parse-quote))

(defn get-words [quote]
  (-> quote
      s/lower-case
      (s/replace #"[^ a-z]" "")
      (s/split #" ")))

(def merge-with-addition (partial merge-with +))

(defn get-word-counts [quote-words]
  (loop [words quote-words
         word-counts {}]
    (if (empty? words)
      word-counts
      (recur (rest words) (merge-with-addition word-counts {(first words) 1})))))

(defn await-futures [futures]
  (doseq [f futures] @f))

(defn quote-word-count [n]
  (let [word-counts (atom {})
        futures-list (doall
                       (for [_ (range n)]
                        (future
                          (let [quote-word-map (-> (get-quote)
                                                   get-words
                                                   get-word-counts)]
                            (swap! word-counts merge-with-addition quote-word-map)))))]
    (await-futures futures-list)
    @word-counts))

