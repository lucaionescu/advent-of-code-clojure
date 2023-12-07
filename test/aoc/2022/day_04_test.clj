(ns aoc.2022.day-04-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-04 :refer [part-1 part-2]]))

(def x [[2 4 6 8]
        [2 3 4 5]
        [5 7 7 9]
        [2 8 3 7]
        [6 6 4 6]
        [2 6 4 8]])

(deftest part-1-test
  (let [solution (part-1 x)
        expected 2]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 x)
        expected 4]
    (is (= solution expected))))
