(ns meetup-demo.subs.core
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :get-account
  (fn [db _]
    (get db :account)))