(ns worksheet.protos)

;; multi-method dispatch
(defmulti speak (fn [animal] (:type animal)))

(defmethod speak :dog [animal]
  (println "bark bark"))

(defmethod speak :cat [animal]
  (println "meeeow"))


;; protocols

(defprotocol TypeIntroduction
  "I introduce all types"
  (introduce [x]))

;; extending a type with our protocol
(extend-type java.lang.String
  TypeIntroduction
  (introduce [s] (println (str "Aha, " s, " is a string - the most wordy of types"))))


;; using extend-protocol for more concise use on multiple types
(extend-protocol TypeIntroduction
  java.lang.Number
  (introduce [x] (println (str "well...it's not much, but I guess we'll keep " x " around.")))
  java.lang.Object ;; equivalent to default implementation since everything inherits from Object.
  (introduce [x] (println "welp. it's an object")))
