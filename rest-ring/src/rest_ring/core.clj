(ns rest-ring.core
  (:require [ring.adapter.jetty :as jetty]
            [clojure.pprint :as pp])
  (:gen-class))

(defn handler [request]
  (pp/pprint request)
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello - this is a Clojure ring application"})

(defn -main
  [& args]
  (jetty/run-jetty handler {:port 3000}))
