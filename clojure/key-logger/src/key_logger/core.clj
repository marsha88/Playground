(ns key-logger.core
  (:import
    (org.jnativehook GlobalScreen)
    (org.jnativehook.keyboard NativeKeyListener))
  (:gen-class))

(defn get-key-listener []
  (proxy [NativeKeyListener] []
    (nativeKeyPressed [evt] (println "key pressed"))
    (nativeKeyReleased [evt] (println "key released"))
    (nativeKeyTyped [evt] (println "key typed"))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "running...")
  (try (GlobalScreen/registerNativeHook)
       (GlobalScreen/addNativeKeyListener (get-key-listener))
    (catch Exception e (str "Exception: " (.getMessage e)))))
