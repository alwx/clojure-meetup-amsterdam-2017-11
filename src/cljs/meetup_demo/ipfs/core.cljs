(ns meetup-demo.ipfs.core
  (:require [cljsjs.ipfs]))

(def node (new js/Ipfs (clj->js {:repo "amsterdam-clojure-meetup-ipfs"})))

(.log js/console node)