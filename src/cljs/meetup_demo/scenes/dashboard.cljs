(ns meetup-demo.scenes.dashboard
  (:require [reagent.core :as r]
            [re-frame.core :as rf]))

(defn scene-view []
  (let [account (rf/subscribe [:get-account])]
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
          #_[:div.item
           "Message list is here"]]
         [:div.toolbar
          [:input.u-full-width {:on-change   #(rf/dispatch [:new-message/set-text (-> % .-target .-value)])
                                :placeholder "Type your message here"}]
          [:button {:on-click #(rf/dispatch [:new-message/send])
                    :value "Send"}]]]]])))