(ns run-length-encoding)

(defn encode [{count :count current :current encoding :encoding} l]
  (cond
    (nil? current) { :count 1 :current l :encoding encoding }
    (= current l)  { :count (inc count) :current l :encoding encoding }
    :else          (if (= count 1) { :count 1 :current l :encoding (str encoding current) }
                                   { :count 1 :current l :encoding (str encoding count current) })))

;; TODO: this doesn't handle the final stream
(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (let [{encoding :encoding} (reduce encode {:count 0 :current nil :encoding ""} (seq plain-text))]
       encoding))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  )

(defn tdest [{count :count current :current encoding :encoding} l]
  (str count current encoding l))
