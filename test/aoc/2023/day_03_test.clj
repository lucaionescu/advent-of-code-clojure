(ns aoc.2023.day-03-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2023.day-03 :refer [get-numbers part-1 part-2]]
            [aoc.utils :refer [read-file]]))

(def input (read-file "2023/day_03_test.txt"))

(deftest get-numbers-test
  (let [numbers (get-numbers input)]
    (is (= (count numbers) 10))))

(deftest part-1-test
  (let [solution (part-1 input)
        expected 4361]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 input)
        expected 467835]
    (is (= solution expected))))
