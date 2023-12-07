(ns aoc.2022.day-08-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-08 :refer [part-1 part-2 count-trees]]))

(def input [[3 0 3 7 3]
            [2 5 5 1 2]
            [6 5 3 3 2]
            [3 3 5 4 9]
            [3 5 3 9 0]])

(deftest count-trees-test
  (is (= (count-trees [1 2 3 5 2 2] 3) 3)))

(deftest part-1-test
  (is (= (part-1 input) 21)))

(deftest part-2-test
  (is (= (part-2 input) 8)))
