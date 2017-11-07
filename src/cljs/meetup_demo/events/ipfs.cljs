(ns meetup-demo.events.ipfs
  (:require [madvas.re-frame.web3-fx]
            [re-frame.core :as rf]
            [meetup-demo.ipfs :as ipfs]))

;; https://ipfs.io/ipfs/<HASH>

(rf/reg-fx
  ::send
  (fn [[ipfs-node {:keys [text]}]]
    (ipfs/send-file
      ipfs-node
      {:path    "message.txt"
       :content text}
      [:ipfs/file-sent]
      [:log-error])))

(rf/reg-event-fx
  :ipfs/send
  (fn [{:keys [db]} [_ message]]
    (let [ipfs-node (get-in db [:ipfs :node])]
      {::send [ipfs-node message]})))


(rf/reg-event-fx
  :ipfs/file-sent
  (fn [{:keys [db]} [_ {:keys [hash]} content]]
    {:db         (assoc-in db [:ipfs-content hash] content)
     :dispatch-n [[:new-message/send {:ipfs-hash hash}]]}))

(rf/reg-fx
  ::retrieve
  (fn [[ipfs-node hash]]
    (ipfs/retrieve-file
      ipfs-node
      hash
      [:ipfs/file-loaded]
      [:log-error])))

(rf/reg-event-fx
  :ipfs/load
  (fn [{:keys [db]} [_ hash]]
    (let [ipfs-node (get-in db [:ipfs :node])]
      {::retrieve [ipfs-node hash]})))

(rf/reg-event-db
  :ipfs/file-loaded
  (fn [db [_ {:keys [content hash]}]]
    (assoc-in db [:ipfs-content hash] content)))