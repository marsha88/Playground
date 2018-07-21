(ns bob)

(defn response-for [s]
  (let [empty (empty? (clojure.string/trim s))
        question (clojure.string/ends-with? s "?")
        exclamation (and (not empty) (re-find #"[a-zA-Z]" s) (= (clojure.string/upper-case s) s))
        exclamatory-question (and question exclamation)]
    (cond
      exclamatory-question "Calm down, I know what I'm doing!"
      question  "Sure."
      exclamation "Whoa, chill out!"
      empty "Fine. Be that way!"
      :else "Whatever.")))
