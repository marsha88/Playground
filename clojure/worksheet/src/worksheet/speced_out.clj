(ns worksheet.speced-out
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as gen]
            [expound.alpha :as exp]))

;; define a spec
;; name must be a full qualified keyword
(s/def ::some-int int?)

;; validate
(s/valid? ::some-int 42)
(s/valid? ::some-int "42")

;; validation with an error string
(s/explain ::some-int 42)
(s/explain ::some-int "42")

;; more human readable output can be achieved from expound library
(exp/expound-str ::some-int 42)
(exp/expound-str ::some-int "42")

;; primitive way to get ALL explain data
;; expound parses this data for us, so just us that.
(s/explain-data ::some-int "42")

(s/def ::some-string string?)

;; combining spec predicates
(s/def ::string-or-int (s/or :int ::some-int
                             :string ::some-string))

(s/valid? ::string-or-int 42)
(s/valid? ::string-or-int "42")

;; conforming - validates and parses the data we provide
(s/conform ::some-string "42")
(s/conform ::string-or-int 42)
(s/invalid? (s/conform ::string-or-int true))

(s/def ::pos-int (s/and pos? int?))
(s/valid? ::pos-int 42)
(s/valid? ::pos-int -5)


;; Data generation that conforms to a spec

;; create a generator
(s/gen ::some-int)

;; creating a sample, given generator
(gen/sample (s/gen ::some-int))
(gen/sample (s/gen ::some-int) 100)
(gen/sample (s/gen ::string-or-int))


;; A warning - don't writing predicate functions that only return single values
;; combined predicates are run in order to achieve sample sets, so if your
;; spec is (s/and int? (fn [x] (= x 9))) then the sample code will first generate n
;; ints and then filter those with the second predicate you provided - this will often
;; result in a sample that cannot find output. example below

(def nine? #(= % 9))

(s/def ::bad-idea (s/and int? nine?))

(gen/sample (s/gen ::bad-idea) 100) ;; often results in "couldn't satisfy predicate error"

;; if you do want a predicate with limited values you're still in luck
;; sets can be used as predicates!

(s/def ::nine #{9})

(gen/sample (s/gen ::nine))

;; this works because sets are not opaque - we cannot possibly know all the outputs
;; of a function are, but we can with a set.


(s/exercise ::string-or-int) ;; outputs tuples containing values and the conformed values


;; Regex matching data

(s/def ::int-float-seq (s/cat :int int?
                              :float float?))

(s/valid? ::int-float-seq [2 4.5])
(s/conform ::int-float-seq [42 4.4])

(s/def ::fn (s/cat :args (s/* int?)
                   :command simple-keyword?))

(s/conform ::fn [1 2 3 :woof])
(s/conform ::fn [1 2 :woof])
(s/conform ::fn [:woof])

;; optional args and subargs
(s/def ::fn1 (s/cat :args (s/? int?)  ;;optional
                    :sub-args (s/cat :sym simple-symbol?
                                     :floats float?)
                    :command simple-keyword?))

(s/conform ::fn1 [1 'foo 3.5 :woof])
(s/conform ::fn1 ['bar 2.1 :woof])
(s/conform ::fn1 [:woof])

;; forcing a subitem (foo 1 [2 3] :bar)
;; to avoid sub cats getting spliced together, you must explicitly create
;; a spec

(s/def ::fn2 (s/cat :args (s/? int?)  ;;optional
                    :sub-args (s/spec (s/cat :sym simple-symbol?
                                             :floats float?))
                    :command simple-keyword?))

(s/conform ::fn2 [1 ['foo 3.5] :woof])
(s/conform ::fn1 ['bar 2.1 :woof])
(s/conform ::fn1 [:woof])


;; specing maps

(s/def :customer/id int?)
(s/def :customer/name string?)

(s/def :customer/record (s/keys :req [:customer/id]
                                :opt [:customer/name]))
(s/valid? :customer/record {:customer/id 1
                            :customer/name "bill"})
(s/valid? :customer/record {:customer/id 1})

;; using unqualified keywords
(s/def :unq/person
  (s/keys :req-un [::first-name ::last-name ::email]
          :opt-un [::phone]))

(defrecord Person [first-name last-name email phone])

(s/explain :unq/person (->Person "clayton" "marshall" "c@gmail.com" nil))














