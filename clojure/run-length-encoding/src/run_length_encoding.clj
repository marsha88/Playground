(ns run-length-encoding
  (:require [clojure.string :as string]))

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

;; find better solution than this.
(defn char-num?
  "Returns true if given character is numeric"
  [char]
  (let [num (int char)]
    (and (>= num (int \0)) (<= num (int \9)))))

(defn run-length-decode
  "Decodes a run-length-encoded string"
  ([cipher-text] (run-length-decode cipher-text ""))
  ([cipher-text decoding]
    (if (= 0 (count cipher-text)) decoding
        (let [num (string/join (take-while char-num? cipher-text))
              rem-cipher (drop (count num) cipher-text)]
              (if (empty? num)
                  (recur (drop 1 rem-cipher) (str decoding (first rem-cipher)))
                  (recur (drop 1 rem-cipher) (str decoding (string/join (repeat (read-string num) (first rem-cipher))))))))))
