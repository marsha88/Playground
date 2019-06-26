(ns ghost-mail.core
  (:use [hickory.core])
  (:require
   [clojure.core.reducers :as r]
   [clj-http.client :as client]
   [hickory.select :as s]
   [digest])
  (:import
   (org.apache.http.impl.client HttpClientBuilder))
  (:gen-class))

(def base-url "https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/")

(def headers {"X-RapidAPI-Host" "privatix-temp-mail-v1.p.rapidapi.com"
              "X-RapidAPI-Key" "adbc8ccf14msh80799d1612132b9p16c604jsndfa6cd074d91"})

(defn cookie-disabler [^HttpClientBuilder builder
                       request]
  (when (:disable-cookies request)
    (.disableCookieManagement builder)))

(defn email-request [email-hash]
  "Given an md5 hash of a provided email - makes request for all messages in inbox"
  (:body (client/get (str base-url email-hash "/")
                     {:headers headers
                      :http-builder-fns [cookie-disabler]
                      :disable-cookies true
                      :cookie-policy :standard
                      :as :json})))

(defn format-email [{error :error
                     from :mail_from
                     subject :mail_subject
                     message :mail_text}]
  (println error)
  (if error
    "Inbox Empty"
    (str "From: " from "\nSubject: " subject "\n\n" message "\n\n")))

(defn format-inbox [emails]
  (r/fold str (map format-email emails)))

(defn print-inbox [email]
  (let [api-resp (-> email digest/md5 email-request)]
    (if (:error api-resp)
      (println "\n\n\nInbox Empty\n\n\n")
      (println (format-inbox api-resp)))))

(defn generate-email []
  "Scrapes the temp-email site for new email address - returns the email as a string"
  (let [parsed-html (-> (client/get "https://temp-mail.org/en/" {:cookie-policy :standard})
                        :body
                        parse
                        as-hickory)
        email-address (-> (s/select (s/id "mail") parsed-html)
                          first
                          :attrs
                          :value)]
    email-address))

(defn print-new-email [email]
  (println (str "Temporary Email: " email)))

(defn print-usage []
  "Usage:\nGenerating a temporary email: ghost-app\n
   Opening your temporary email inbox: ghost-app <email>\n")

(defn -main
  [& args]
  (let [len (count args)]
    (cond
      (= len 0) (print-new-email (generate-email))
      (= len 1) (print-inbox (first args))
      :else (print-usage))))
