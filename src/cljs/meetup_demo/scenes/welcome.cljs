(ns meetup-demo.scenes.welcome
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [cljs-web3.eth :as web3-eth]))

(defn account-view [account]
  [:div {:on-click #(rf/dispatch [:log-in account])}
   [:h4 "Unknown account"]
   [:small
    account]])

(defn scene-view []
  [:div.centered-root
   [:div.container
    (if (exists? js/web3)
      (let [accounts (web3-eth/accounts js/web3)]
        [:div
         [:h1 "Choose your account:"]
         [:div.accounts
          (for [account accounts]
            ^{:key (str "account-" account)}
            [account-view account])]])
      [:div
       [:h1 "Not connected"]
       "You can use Metamask, Mist Browser or Status.im."])]])