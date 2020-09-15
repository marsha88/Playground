(ns worksheet.lisp2
  (:refer-clojure :exclude [eval]))


(defprotocol Evalable
  (eval [form] "Evaluates the provided form"))

(extend-type String
  Evalable
  (eval [form] form))

(extend-type Boolean
  Evalable
  (eval [form] form))

(extend-type Number
  Evalable
  (eval [form] form))

(extend-type clojure.lang.Keyword
  Evalable
  (eval [form] form))

