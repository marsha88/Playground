(ns poker.core-test
  (:require [clojure.test :refer :all]
            [poker.core :refer :all]))

(def high-seven                   ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand                    ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand               ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand         ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand          ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand                ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand        ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand       ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand                   ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand              ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand          ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand  ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(deftest poker-fns
  (testing "suit"
    (is (= (suit "2H") "H")))
  (testing "rank"
    (is (= (rank "2H") 2)))
  (testing "pair?"
    (is (pair? pair-hand))
    (is (not (pair? three-of-a-kind-hand)))
    (is (not (pair? high-seven))))
  (testing "two-pairs?"
    (is (two-pairs? two-pairs-hand))
    (is (not (two-pairs? pair-hand)))
    (is (not (two-pairs? four-of-a-kind-hand))))
  (testing "three-of-a-kind?"
    (is (three-of-a-kind? three-of-a-kind-hand)))
  (testing "four-of-a-kind?"
    (is (four-of-a-kind? four-of-a-kind-hand)))
  (testing "straight?"
    (is (straight? straight-hand)))
  (testing "flush?"
    (is (flush? flush-hand)))
  (testing "straight-flush?"
    (is (straight-flush? straight-flush-hand))
    (is (straight-flush? high-ace-straight-flush-hand)))
 )
