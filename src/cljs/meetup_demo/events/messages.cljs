(ns meetup-demo.events.messages
  (:require [madvas.re-frame.web3-fx]
            [re-frame.core :as rf]))

(rf/reg-event-fx
  :messages/start-loading
  (fn [{:keys [db]}]
    (let [{:keys [instance]} (:contract db)]
      {:web3-fx.contract/events
       {:db-path  [:contract :events]
        :events   [{:instance instance
                    :event-id :on-message-added-event
                    :event-name :on-message-added
                    :event-filter-opts {}
                    :blockchain-filter-opts {:from-block 0}
                    :on-success [:messages/on-message-loaded]
                    :on-error [:log-error]}]}})))

(rf/reg-event-db
  :messages/on-message-loaded
  (fn [db [_ message]]
    (update db :messages conj message)))