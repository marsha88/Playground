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

(defmacro unless [pred & exprs]
  `(if (not ~pred)
     (do ~@exprs)))


(defmacro defnlog [fname args & body]
  `(defn ~fname ~args
     (let [now# (System/currentTimeMillis)]
       (println "[" now# "] Call to " (str (var ~fname)))
       ~@body)))

(defmacro comment' [& body])

(defmacro infix [& exprs]
  (let [[lhs op rhs] exprs]
    (list op lhs rhs)))

(defmacro randomly [& exprs]
  (let [len (count exprs)
        index (rand-int len)]
    (nth exprs index)))


(defmacro defwebmethod [name args & exprs]
  `(defn ~name [{:keys ~args}]
     ~@exprs))

;; Object system from closures
(defn new-user
  [login password email]
  (fn [a & args]
    (case a
      :login login
      :email email
      :authenticate (= password (first args))
      nil)))

(declare ^:dynamic this)

(defn new-object [klass]
  (let [state (atom {})]
    (fn self [command & args]
      (case command
        :class klass
        :class-name (klass :name)
        :set! (let [[k v] args]
                (swap! state assoc k v))
        :get (let [[k] args]
               (@state k))
        (if-let [method (klass :method command)]
          (binding [this self]
            (apply method args))
          (throw (RuntimeException.
                   (str "Unable to respond to " command))))))))

(defn find-method [method-name methods]
  (methods method-name))

(defn new-class [class-name methods]
  (fn klass [a & args]
    (case a
      :name (name class-name)
      :new (new-object klass)
      :method (let [[method-name] args]
                (find-method method-name methods)))))

(defn method-spec [sexpr]
  (let [name (keyword (second sexpr))
        body (next sexpr)]
    [name (conj body 'fn)]))

(defn method-specs [sexprs]
  (->> sexprs
       (filter #(= 'method (first %)))
       (mapcat method-spec)
       (apply hash-map)))

(defmacro defclass [class-name & specs]
  (let [fns (or (method-specs specs) {})]
    `(def ~class-name (new-class '~class-name ~fns))))

(defmacro defrec [name fields]
  `(defn ~name [& values#]
     (let [keys# (map keyword '~fields)
           ~fields values#]
       (zipmap keys# values#))))