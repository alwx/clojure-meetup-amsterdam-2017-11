(ns meetup-demo.ipfs
  (:require [re-frame.core :as rf]))

(defn stream->string [ipfs-node stream callback]
  (let [chuncks (atom [])]
    (.on stream "data" (fn [data]
                         (swap! chuncks conj data)))
    (.on stream "end" (fn []
                        (->>
                          (clj->js @chuncks)
                          (.concat (.. ipfs-node -types -Buffer))
                          (.toString)
                          (callback))))))

(defn send-file [ipfs-node {:keys [content] :as opts} file-sent-event error-event]
  (let [content' (new (.. ipfs-node -types -Buffer) content)]
    (.add (.-files ipfs-node)
      (clj->js (assoc opts :content content'))
      (fn [error result]
        (.log js/console "send-file: " (clj->js result))
        (rf/dispatch
          (if error
            (conj error-event (js->clj error :keywordize-keys true))
            (into file-sent-event [(first (js->clj result :keywordize-keys true))
                                   content])))))))

(defn retrieve-file [ipfs-node ipfs-hash file-retrieved-event error-event]
  (.cat (.-files ipfs-node)
    ipfs-hash
    (fn [error result]
      (if error
        (rf/dispatch (into error-event (js->clj error :keywordize-keys true)))
        (stream->string
          ipfs-node
          result
          (fn [content]
            (rf/dispatch (conj file-retrieved-event {:hash    ipfs-hash
                                                     :content content}))))))))