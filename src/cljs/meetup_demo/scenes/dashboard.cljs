(ns meetup-demo.scenes.dashboard
  (:require [clojure.string :as str]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn scene-view []
  (let [account (rf/subscribe [:account])
        messages (rf/subscribe [:messages/all])
        new-message-text (rf/subscribe [:new-message/text])]
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
          (for [{:keys [text author-address message-key]} (reverse @messages)]
            ^{:key message-key}
            [:div.message
             [:div.text text]
             [:div.user "Posted by " (or author-address "you")]])]
         [:div.toolbar
          [:input
           {:on-change   #(rf/dispatch [:new-message/set-text (-> % .-target .-value)])
            :value       @new-message-text
            :placeholder "Type your message here"}]
          (when-not (str/blank? @new-message-text)
            [:button.button-primary
             {:on-click #(rf/dispatch [:new-message/send])}
             "Send"])]]]])))