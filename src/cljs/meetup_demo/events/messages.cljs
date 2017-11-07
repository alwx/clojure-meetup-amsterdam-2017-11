(ns meetup-demo.events.messages
  (:require [clojure.string :as str]
            [madvas.re-frame.web3-fx]
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

(rf/reg-event-fx
  :messages/on-message-loaded
  (fn [{:keys [db]} [_ {:keys [ipfs-hash] :as message}]]
    (cond->
      {:db (update db :messages conj message)}

      (not (str/blank? ipfs-hash))
      (assoc :dispatch-n [[:ipfs/load ipfs-hash]]))))