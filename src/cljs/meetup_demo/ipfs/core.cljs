(ns meetup-demo.ipfs.core
  (:require [cljsjs.ipfs]))

(def ipfs js/Ipfs)

(defn create-ipfs [account]
  (new ipfs (clj->js {:repo         (str "amsterdam-clojure-meetup-ipfs/" account)
                      :EXPERIMENTAL {:pubsub true}})))