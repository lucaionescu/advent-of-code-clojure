(ns aoc.day-11-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.day-11 :refer [parse-input part-1 part-2]]))

(def parsed_input (parse-input "day_11_test.txt"))

(deftest part-1-test
  (is (= (part-1 parsed_input) 10605)))

(deftest part-2-test
  (is (= (part-2 parsed_input) 2713310158)))
