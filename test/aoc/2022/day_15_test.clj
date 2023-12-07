(ns aoc.2022.day-15-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-15 :refer [parse-input part-1 part-2]]))

(deftest part-1-test
  (let [input (parse-input "2022/day_15_test.txt")
        row 10]
    (is (= (part-1 input row) 26))))

(deftest part-2-test
  (let [input (parse-input "2022/day_15_test.txt")
        rows 20]
    (is (= (part-2 input rows) 56000011))))

