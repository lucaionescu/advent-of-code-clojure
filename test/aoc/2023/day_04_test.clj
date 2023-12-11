(ns aoc.2023.day-04-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2023.day-04 :refer [part-1 part-2]]
            [aoc.utils :refer [read-file]]))

(def input (read-file "2023/day_04_test.txt"))

(deftest part-1-test
  (let [solution (part-1 input)
        expected 13]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 input)
        expected 30]
    (is (= solution expected))))
