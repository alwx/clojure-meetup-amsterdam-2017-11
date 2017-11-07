(ns meetup-demo.scenes.dashboard
  (:require [clojure.string :as str]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn new-message-view []
  (let [text (rf/subscribe [:new-message/text])]
    (fn []
      (let [text @text]
        [:div.toolbar
         [:input
          {:on-change   #(rf/dispatch [:new-message/set-text (-> % .-target .-value)])
           :value       text
           :placeholder "Type your message here"}]
         (when-not (str/blank? text)
           [:button.button-primary
            {:on-click #(rf/dispatch [:ipfs/send {:text text}])}
            "Send"])]))))

(defn simple-message-view [{:keys [text author-address]}]
  [:div.message
   [:div.text text]
   [:div.user "Posted by " (or author-address "you")]])

(defn ipfs-message-view [{:keys [text author-address ipfs-hash]}]
  (let [ipfs-content (rf/subscribe [:ipfs-content/content ipfs-hash])]
    (fn []
      [:div.message
       [:div.text (or @ipfs-content "...")]
       [:div.user "Posted by " (or author-address "you")]])))

(defn message-view [{:keys [ipfs-hash] :as message}]
  (if (str/blank? ipfs-hash)
    [simple-message-view message]
    [ipfs-message-view message]))

(defn scene-view []
  (let [account (rf/subscribe [:account])
        messages (rf/subscribe [:messages/all])]
    (fn []
      [:div
       [:div.navbar
        [:div.container
         [:div.row
          [:div.account
           @account]]]]

       [:div.container
        [:div.dashboard
         [:div.content
          (for [{:keys [message-key] :as message} (reverse @messages)]
            ^{:key message-key}
            [message-view message])]
         [new-message-view]]]])))