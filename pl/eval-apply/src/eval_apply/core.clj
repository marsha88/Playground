(ns eval-apply.core
  (:refer-clojure :exclude [eval apply true?]))

(declare eval-sexp)
(declare eval)
(declare apply)

(defrecord State [result env])
(defrecord Proc [params body env])

(def bools #{'TRUE 'FALSE})

(defn true? [exp]
  (not= exp 'FALSE))

(def self-evaluating? (some-fn number? string? bools))

(defn eval-if
  [operands env]
  (let [[pred then else] operands]
    (if (true? (eval pred env))
      (eval then env)
      (if (nil? else) 'NIL
                      (eval else env)))))

(defn resolve-symbol [sexp env]
  (let [local-res (get env sexp ::undefined)]
    (if (not= local-res ::undefined)
      local-res
      (if-let [global-res (resolve sexp)]
        global-res
        (throw (Exception. (str "Cannot resolve symbol " sexp)))))))

(defn eval-sexp [sexp env]
  (cond
    (self-evaluating? sexp) (->State sexp env)
    (symbol? sexp) (->State (resolve-symbol sexp env) env)
    (seq? sexp) (let [[op & operands] sexp]
                  (case op
                    def (let [[name exp] operands
                               value (eval exp env)]
                           (->State 'NIL (assoc env name value)))
                    if  (->State (eval-if operands env) env)
                    fn  (let [[params body] operands]
                                 (->State (->Proc params body env) env))
                    let (let [[kv-pairs & body] operands
                              updated-env (apply (partial assoc env) kv-pairs)
                              expr (last (map #(eval % updated-env) body))]
                          (->State expr env))
                    (->State (apply (eval op env)
                                    (map #(eval % env) operands)) env)))
    :else (throw (Exception. (str "Evaluation failed at " sexp)))))


(defn eval
  ([sexp] (eval sexp {}))
  ([sexp env]
    (:result (eval-sexp sexp env))))

(defn compound-procedure? [expr]
  (instance? Proc expr))

(defn apply [proc args]
  (if (compound-procedure? proc)
    (eval (:body proc)
          (merge
            (:env proc)
            (zipmap (:params proc)
                    args)))
    ;; if not compound procedure - resolve as clojure symbol
    (clojure.core/apply proc args)))


(defn eval-program [sexps]
  (let [next-state (fn [last-state sexp]
                     (eval-sexp sexp (:env last-state)))
        initial-state (->State 'NIL {})]
    (:result (reduce next-state initial-state sexps))))
