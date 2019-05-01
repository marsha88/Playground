(ns cljweb.core
    (:require ))

(enable-console-print!)

(println "hey it reloaded!")
;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(def ctx (js/canvas.getContext "2d"))

(defn tick
  []
  ())

(js/setInterval 
    tick
    1000)
