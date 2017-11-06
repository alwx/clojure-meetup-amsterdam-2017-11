(ns meetup-demo.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [meetup-demo.scenes.dashboard :as dashboard]
            [meetup-demo.scenes.welcome :as welcome]
            [meetup-demo.events.core]
            [meetup-demo.subs.core]
            [meetup-demo.ipfs.core]))

(defn root-scene []
  (let [account (rf/subscribe [:account])]
    (fn []
      (if @account
        [dashboard/scene-view]
        [welcome/scene-view]))))

(defn app-root []
  (r/render [root-scene] (.getElementById js/document "app")))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (app-root))

(init)