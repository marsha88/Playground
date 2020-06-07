(ns worksheet.async
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout take! put!]]))

;; these are the same as <!! and >!! (blocking take and put)
(defn takep [c]
  (let [p (promise)]
    (take! c (fn [v] (deliver p v)))
    @p))

(defn putp [c v]
  (let [p (promise)]
    (put! c v (fn [] (deliver p nil)))
    @p))




(defn vending-machine
  []
  (let [in (chan)
        out (chan)]
    (go (<! in)
        (>! out "item"))
    [in out]))