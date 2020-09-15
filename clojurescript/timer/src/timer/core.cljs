(ns ^:figwheel-hooks timer.core
  (:require
   [goog.dom :as gdom]
   [goog.object :as gob]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-component [name]
  [:div
   [:h3 (str "Hello, " name)]])

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn lister [& items]
  [:ul 
   (for [item items]
     [:li {:key item} item])])

(defn grocery-list []
  [:div
   [lister "oranges" "cereal" "waffles" "oatmeal"]])

(defn counter []
  (let [click-count (atom 0)]
    (fn []
      [:div
       [:code "current count: " @click-count]
       [:input {:type "button" :value "Click me!"
                :on-click #(swap! click-count inc)}]])))

(defn fetch-number [number callback]
  (-> (js/fetch (str "http://numbersapi.com/" number))
                           (.then #(.text %))
                           (.then #(callback %))
                           (.catch #(js/console.log "Error fetching data"))))

(defn search-numbers-api []
  (let [quote (atom "")]
    (fn []
      [:div
       [:p @quote]
       [:input#number-input {:type "number"}]
       [:br]
       [:input {:type "button" 
                :value "Search"
                :on-click (fn []
                            (let [number (-> (js/document.getElementById "number-input") 
                                             (.-value))]
                              (fetch-number number (fn [q] (reset! quote q)))))}]])))
                                

(defn app []
  [:div {:style {:display "flex" 
                 :flex 1 
                 :align-items "center" 
                 :justify-content "center"
                 :flex-direction "column"}}
   [hello-component "clayton"]
   [simple-component]
   [grocery-list]
   [counter]
   [search-numbers-api]])


(defn mount [el]
  (rdom/render [app] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
