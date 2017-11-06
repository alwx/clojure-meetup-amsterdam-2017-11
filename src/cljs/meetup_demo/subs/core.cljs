(ns meetup-demo.subs.core
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :account
  (fn [db _]
    (get db :account)))

(rf/reg-sub
  :messages/all
  (fn [db]
    (get db :messages)))

(rf/reg-sub
  :new-message/text
  (fn [db]
    (get-in db [:new-message :text])))