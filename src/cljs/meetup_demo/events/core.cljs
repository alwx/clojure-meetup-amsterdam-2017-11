(ns meetup-demo.events.core
  (:require [cljs-web3.eth :as web3-eth]
            [re-frame.core :as rf]
            [meetup-demo.db :as db]
            [meetup-demo.ipfs.core :as ipfs]
            meetup-demo.events.new-message
            meetup-demo.events.messages))

(rf/reg-event-fx
  :log-error
  (fn [_ [_ err]]
    (.error js/console err)))

(rf/reg-event-db
 :initialize-db
 (fn [_]
   db/app-db))

(rf/reg-event-fx
  :log-in
  (fn [{:keys [db]} [_ account]]
    (let [{:keys [abi address]} (:contract db)]
      {:db         (-> db
                     (assoc :account account)
                     (assoc-in [:contract :instance] (web3-eth/contract-at js/web3 abi address)))
       :dispatch-n [[:messages/start-loading]]})))