(ns emailer.core
  (:use [postal.core]))

;; it sends, but goes directly to spam
(defn foo
  "I don't do a whole lot."
  [x]
  (send-message {:from "me@draines.com"
                 :to ["mom@example.com" "dad@example.com"]
                 :cc "bob@example.com"
                 :subject "Hi!"
                 :body "Test."
                 :X-Tra "Something else"}))
