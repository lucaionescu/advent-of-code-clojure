(ns aoc.2022.day-12-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-12 :refer [find-position part-1 part-2]]))

(def rows ["Sabqponm"
           "abcryxxl"
           "accszExk"
           "acctuvwj"
           "abdefghi"])

(def end_pos (find-position rows \E))

(deftest part-1-test
  (is (= (part-1 rows end_pos) 31)))

(deftest part-2-test
  (is (= (part-2 rows end_pos) 29)))