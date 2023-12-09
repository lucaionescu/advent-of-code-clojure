(ns aoc.utils
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split-lines]]))

(defn get-neighbors
  [[x y]]
  (vector [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
          [(dec x) y] [(inc x) y]
          [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]))

(defn char-is-digit?
  [x]
  (<= (int \0) (int x) (int \9)))

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
