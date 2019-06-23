(ns ghost-mail.core
  (:use [hickory.core])
  (:require [clj-http.client :as client]
            [hickory.select :as s])
  (:gen-class))

(def base-url "https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/")

(def headers {:X-RapidAPI-Host "privatix-temp-mail-v1.p.rapidapi.com"})

(def email-hash "3eeb8bb533aa950707346a0f54f1be6f")

(defn json-req []
  (println (client/get "https://jsonplaceholder.typicode.com/todos/1" {:as :json})))

(defn email-request []
  (client/get (str base-url email-hash)
              {:headers {:X-RapidAPI-Host "privatix-temp-mail-v1.p.rapidapi.com"
                         :X-RapidAPI-Key "e0ffd881b7msh7fbc6f379154dbfp1ddc80jsnd9761dc94716"}
               :as :json
               :debug true}))

;; Scraping temp-email site for new email address
(defn generate-email []
  (let [parsed-html (-> (client/get "https://temp-mail.org/en/")
                        :body
                        parse
                        as-hickory)
        email-address (-> (s/select (s/id "mail") parsed-html)
                          first
                          :attrs
                          :value)]
    email-address))

(defn -main
  [& args]
  (println (str "Temporary Email Address: " (generate-email))))
