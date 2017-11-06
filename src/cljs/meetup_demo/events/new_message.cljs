(ns meetup-demo.events.new-message
  (:require [madvas.re-frame.web3-fx]
            [re-frame.core :as rf]))

(rf/reg-event-db
  :new-message/set-text
  (fn [db [_ text]]
    (assoc-in db [:new-message :text] text)))

(rf/reg-event-fx
  :new-message/send
  (fn [{:keys [db]}]
    (let [{:keys [text]} (:new-message db)
          {:keys [instance]} (:contract db)]
      {:web3-fx.contract/state-fns
       {:web3    js/web3
        :db-path [:contract :send-message]
        :fns     [{:instance      instance
                   :method        :add-message
                   :args          [text ""]
                   :tx-opts       {:from (:account db)
                                   :gas  1000000}
                   :on-success    [:new-message/confirmed]
                   :on-error      [:log-error]
                   :on-tx-receipt [:new-message/transaction-receipt-loaded]}]}})))

(rf/reg-event-db
  :new-message/confirmed
  (fn [db [_ transaction-hash]]
    (assoc-in db [:new-message :sending?] true)))

(rf/reg-event-db
  :new-message/transaction-receipt-loaded
  (fn [db [_ {:keys [gas-used] :as transaction-receipt}]]
    (.log js/console transaction-receipt)
    (assoc-in db [:new-message :sending?] false)))