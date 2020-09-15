(ns ^:figwheel-hooks hello-world.app)

(enable-console-print!)

(def canvas (.getElementById js/document "game-board"))
(def ctx (.getContext canvas "2d"))

(def speed 5)
(def direction (atom {:x 0 :y 0}))
(def coord (atom {:x (/ (.-width canvas) 2) :y (/ (.-height canvas) 2)}))

(defn clear-canvas []
  (let [w (.-width canvas)
        h (.-height canvas)]
    (.clearRect ctx 0 0 w h)))

(defn draw-circle []
  (let [{x :x y :y} @coord]
    (.beginPath ctx)
    (.arc ctx x y 40 0 (* 2 js/Math.PI))
    (.stroke ctx)))

(defn draw []
 (let [dir @direction]
  (clear-canvas)
  (draw-circle)
  (swap! coord (fn [{:keys [x y]}] {:x (+ (dir :x) x) :y (+ (dir :y) y)}))))


(.addEventListener js/document "keydown" (fn [evt]
                                            (case (.-keyCode evt)
                                              37 (swap! direction (fn [{:keys [x y]}] {:x (* speed -1) :y y}))
                                              38 (swap! direction (fn [{:keys [x y]}] {:x x :y (* speed -1)}))
                                              39 (swap! direction (fn [{:keys [x y]}] {:x (* speed 1) :y y}))
                                              40 (swap! direction (fn [{:keys [x y]}] {:x x :y (* speed 1)}))
                                              nil)))

(.addEventListener js/document "keyup" (fn [evt]
                                          (case (.-keyCode evt)
                                            37 (swap! direction (fn [{:keys [x y]}] {:x 0 :y 0}))
                                            38 (swap! direction (fn [{:keys [x y]}] {:x 0 :y 0}))
                                            39 (swap! direction (fn [{:keys [x y]}] {:x 0 :y 0}))
                                            40 (swap! direction (fn [{:keys [x y]}] {:x 0 :y 0}))
                                            nil)))
(js/setInterval draw 10)

