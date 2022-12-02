(ns aoc.day-02-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.day-02 :refer [part-1 part-2]]))

(deftest part-1-test
  (let [x [["A" "Y"]
           ["B" "X"]
           ["C" "Z"]]
        solution (part-1 x)
        expected 15]
    (is (= solution expected))))

(deftest part-2-test
  (let [x [["A" "Y"]
           ["B" "X"]
           ["C" "Z"]]
        solution (part-2 x)
        expected 12]
    (is (= solution expected))))