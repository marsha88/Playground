(ns run-length-encoding
  (:require [clojure.string :as s]))

(defn run-length-encode
  "Encodes a string with run-length-encoding"
  ([plain-text] (run-length-encode plain-text ""))
  ([plain-text encoding]
    (if (= 0 (count plain-text)) encoding
        (let [char (first plain-text)
              char-list (take-while #(= % char) plain-text)
              len (count char-list)]
          (if (= 1 len)
              (recur (drop len plain-text) (str encoding char))
              (recur (drop len plain-text) (str encoding len char)))))))

(defn run-length-decode
  "Decodes a run-length-encoded string"
  [cipher-text]
  )
