(ns worksheet.lisp
  (:refer-clojure :exclude [eval]))

(def special-forms '#{quote fn let if do})

(declare eval)

(defn create-fn [locals fn-def]
  (assert (= (count fn-def) 2))
  (let [[arg-names body] fn-def]
    (fn [& args]
      (let [new-locals (merge locals (zipmap arg-names args))]
        (eval new-locals body)))))

(defn process-kv-pairs [locals [k v & args]]
  (println locals)
  (println args)(eval '(let [a 1 b 2]
         (+ a b)))
  (cond
    (and (nil? k) (nil? v)) locals
    (or (nil? k) (nil? v)) (throw (Exception. "Error: must have even number of key value pairs in let expression"))
    :else (let [evaluated-v (eval locals v)
                new-locals (merge locals k evaluated-v)]
            (recur new-locals args))))

(defn eval-let [locals [kvs body]]
  (assert
    (even? (count kvs))
    "Error: must have even number of key value pairs in let expression")
  (eval (process-kv-pairs locals kvs) body))

(defn process-special-form
  [locals [sym & args]]
  (condp = sym
    'quote (first args)
    'fn (create-fn locals args)
    'let (eval-let locals args)
    'if (let [[pred then else] args]
          (if (eval pred)
            (eval then)
            (eval else)))
    'do (last (map (partial eval locals) args))))

(defn eval
  ([form] (eval {} form))
  ([locals form]
    (cond
      (boolean? form) form
      (integer? form) form
      (string? form) form
      (symbol? form) (let [v (get locals form ::fail)]
                       (if (= v ::fail)
                         (if-let [v (resolve form)]
                           v
                           (assert false (str "Can't resolve symbol " form)))))
      (seq? form) (if (contains? special-forms (first form))
                    (process-special-form locals form)
                    (let [f (eval locals (first form))]
                      (if (:macro (meta f))
                        (let [args (rest form)]
                          (eval locals (apply f nil nil args)))
                        (let [args (map (partial eval locals) (rest form))]
                          (apply f args)))))
      (keyword? form) form
      (vector? form) (mapv (partial eval locals) form)
      :else (throw (Exception. (str "Can't evaluate form of " form))))))