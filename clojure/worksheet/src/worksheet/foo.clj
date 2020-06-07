(ns worksheet.foo)

(defn woof []
  (println "woof"))

(defmacro db [expr]
  `(let [x# ~expr]
     (println (str (quote ~expr) " => " x#))))
