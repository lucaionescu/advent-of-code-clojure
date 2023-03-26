(ns aoc.utils
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split-lines]]))

(defn read-file
  [file]
  (->> file
       resource
       slurp
       split-lines))

(defn sum
  [x]
  (reduce + x))

(defn manhattan
  [x y]
  (sum (map #(abs (- %1 %2)) x y)))
