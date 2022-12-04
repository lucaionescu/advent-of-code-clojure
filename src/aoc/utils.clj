(ns aoc.utils
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split-lines]]))

(defn read-file
  ([file]
   (->> file
        resource
        slurp
        split-lines)))